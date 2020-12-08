package com.decimalab.minutehelp.ui

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.ui.*
import com.decimalab.minutehelp.R
import com.decimalab.minutehelp.databinding.NavHeaderMainBinding
import com.decimalab.minutehelp.factory.AppViewModelFactory
import com.decimalab.minutehelp.ui.login.LoginViewModel
import com.decimalab.minutehelp.ui.verifycode.VerifyCodeFragment
import com.decimalab.minutehelp.utils.Resource
import com.decimalab.minutehelp.utils.SharedPrefsHelper
import com.decimalab.minutehelp.utils.ViewUtils
import dagger.android.support.DaggerAppCompatActivity
import org.jetbrains.anko.textColor
import javax.inject.Inject

class OneMinuteHelpActivity : DaggerAppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
    NavController.OnDestinationChangedListener {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var progressBar: ProgressBar
    private lateinit var navView: NavigationView
    @Inject
    lateinit var viewModelFactory: AppViewModelFactory
    @Inject
    lateinit var prefsHelper: SharedPrefsHelper
    private val viewModel by viewModels<OneMinuteHelpViewModel> { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_one_minute_help)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)


        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)
        navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.nav_home,
                    R.id.nav_gallery,
                    R.id.nav_slideshow
            ), drawerLayout
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
        val headerMainBinding = DataBindingUtil
            .inflate<NavHeaderMainBinding>(layoutInflater,
                R.layout.nav_header_main,
                navView,
                false)

        navView.addHeaderView(headerMainBinding.root)

        prefsHelper.getUser()?.let {
            headerMainBinding.headerUserName.text = it.name
            headerMainBinding.headerUserName.setTextColor(Color.WHITE)
            Log.d(TAG, "onCreate: profile name set")
        }

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
        when(item.itemId){
            R.id.nav_logout-> logout()
            R.id.nav_profile->navController.navigate(R.id.nav_profile)
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

    fun onViewProfileClicked(){
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
        when(destination.id){
            R.id.nav_splash->drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            R.id.nav_login->drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            R.id.nav_register->drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            R.id.nav_create_group->drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            else -> drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
        }
    }
}