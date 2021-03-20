package com.decimalab.minutehelp.ui

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout.DrawerListener
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.ui.*
import coil.load
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import com.avatarfirst.avatargenlib.AvatarConstants
import com.avatarfirst.avatargenlib.AvatarGenerator
import com.decimalab.minutehelp.R
import com.decimalab.minutehelp.databinding.NavHeaderMainBinding
import com.decimalab.minutehelp.factory.AppViewModelFactory
import com.decimalab.minutehelp.utils.Resource
import com.decimalab.minutehelp.utils.SharedPrefsHelper
import com.decimalab.minutehelp.utils.ViewUtils
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class OneMinuteHelpActivity : DaggerAppCompatActivity(),
    NavigationView.OnNavigationItemSelectedListener,
    NavController.OnDestinationChangedListener, DrawerListener {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var progressBar: ProgressBar
    private lateinit var navView: NavigationView
    private lateinit var toolbar: Toolbar
    private lateinit var headerMainBinding: NavHeaderMainBinding

    @Inject
    lateinit var viewModelFactory: AppViewModelFactory

    @Inject
    lateinit var prefsHelper: SharedPrefsHelper
    private val viewModel by viewModels<OneMinuteHelpViewModel> { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_one_minute_help)
        toolbar = findViewById(R.id.main_toolbar)
        setSupportActionBar(toolbar)
        //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        drawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.addDrawerListener(this)

        navView = findViewById(R.id.nav_view)
        //navView.background.alpha = 200
        navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.nav_dashboard), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        navView.setNavigationItemSelectedListener(this)
        progressBar = findViewById(R.id.pb_main)

        navController.addOnDestinationChangedListener(this)
        setNavViewHeader()
        observe()
    }

    private fun setNavViewHeader() {
        headerMainBinding = DataBindingUtil
            .inflate<NavHeaderMainBinding>(
                layoutInflater,
                R.layout.nav_header_main,
                navView,
                false
            )

        navView.addHeaderView(headerMainBinding.root)
        headerMainBinding.activity = this
    }

    private fun observe() {
        viewModel.getLogoutResult.observe(this, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    progressVisibility(View.GONE)
                    val response = it.data
                    if (response != null) {
                        if (response.code == 200 && response.status) {
                            val message = response.messages.toString()
                            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                            prefsHelper.clearAllData()
                            navController.navigate(R.id.action_global_nav_login)
                        } else {
                            val message = response.messages.toString()
                            Log.d(Companion.TAG, "onViewCreated: failed $message")
                            ViewUtils.toastFailedWithMessage(this, this, message)
                        }
                    }

                }
                Resource.Status.ERROR -> {
                    progressVisibility(View.GONE)
                    Log.d(TAG, "onActivityCreated: error " + it.isNetworkError)
                    it.isNetworkError?.let { it ->
                        if (it) {
                            ViewUtils.toastNoInternet(this, this)
                        }
                    }
                }
                Resource.Status.LOADING ->
                    progressVisibility(View.VISIBLE)

            }
        })
    }

    private fun progressVisibility(visibility: Int) {
        progressBar.visibility = visibility
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.one_minute_help, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_logout -> logout()
            R.id.nav_profile -> navController.navigate(R.id.nav_profile)
        }
        Log.d(TAG, "onNavigationItemSelected: outside")
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun logout() {
        Log.d(TAG, "logout: inside")
        viewModel.logoutUser()
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    fun onViewProfileClicked() {
        drawerLayout.closeDrawer(GravityCompat.START)
        navController.navigate(R.id.nav_profile)
    }

    companion object {
        private const val TAG = "OneMinuteHelpActivity"
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        when (destination.id) {
            R.id.nav_splash,
            R.id.nav_login,
            R.id.nav_register,
            R.id.nav_create_group,
            R.id.nav_forgot_password -> {
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
                toolbar.visibility = View.GONE
            }
            else -> {
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
                toolbar.visibility = View.VISIBLE
            }
        }
    }

    override fun onResume() {
        super.onResume()

    }

    override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
    }

    override fun onDrawerOpened(drawerView: View) {
        setHeaderElements()
    }

    private fun setHeaderElements() {

        val nameText = navView.getHeaderView(0).findViewById<TextView>(R.id.header_user_name)
        val imageView = navView.getHeaderView(0).findViewById<ImageView>(R.id.imageView)


        prefsHelper.getUser()?.let {
            nameText.text = it.name
            setNavHeadImage(imageView, it.image, it.name)
            nameText.setTextColor(Color.WHITE)
            Log.d(TAG, "onCreate: profile name set")
        }
    }

    private fun setNavHeadImage(imageView: ImageView, image: String?, name: String) {
        /*if (!image.isNullOrBlank()) {
            imageView.load("http://oneminutehelp.com/uploads/images/$image") {
                crossfade(true)
                crossfade(300)
                placeholder(name?.let { it1 ->
                    AvatarGenerator.avatarImage(
                        imageView.context, 200, AvatarConstants.CIRCLE,
                        it1
                    )
                })
                transformations(RoundedCornersTransformation(radius = 10f))
            }
            Log.d("TAG", "loadImage: url:$image name:$name")
        } else {
            imageView.load(name.let {
                AvatarGenerator.avatarImage(
                    headerMainBinding.imageView.context,
                    200,
                    AvatarConstants.RECTANGLE,
                    it
                )
            }) {
                crossfade(true)
                crossfade(300)
                transformations(RoundedCornersTransformation(radius = 10f))
            }
            Log.d("TAG", "loadImage: url:$image name:$name")
        }*/

        imageView.load("http://oneminutehelp.com/uploads/images/$image") {
            crossfade(true)
            crossfade(300)
            placeholder(R.drawable.pro_pic_placeholder)
            transformations(CircleCropTransformation())
        }

    }

    override fun onDrawerClosed(drawerView: View) {
    }

    override fun onDrawerStateChanged(newState: Int) {
    }
}