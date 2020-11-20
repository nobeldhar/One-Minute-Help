package com.decimalab.minutehelp.di.module

import com.decimalab.minutehelp.ui.forgotpassword.ForgotPasswordFragment
import com.decimalab.minutehelp.ui.gallery.GalleryFragment
import com.decimalab.minutehelp.ui.home.HomeFragment
import com.decimalab.minutehelp.ui.login.LoginFragment
import com.decimalab.minutehelp.ui.register.RegisterFragment
import com.decimalab.minutehelp.ui.slideshow.SlideshowFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeLoginFragment(): LoginFragment

    @ContributesAndroidInjector
    abstract fun contributeRegFragment(): RegisterFragment

    @ContributesAndroidInjector
    abstract fun contributeGalFragment(): GalleryFragment

    @ContributesAndroidInjector
    abstract fun contributeSlideFragment(): SlideshowFragment

    @ContributesAndroidInjector
    abstract fun contributeForgotPassFragment(): ForgotPasswordFragment

    @ContributesAndroidInjector
    abstract fun contributeHOmeFragment(): HomeFragment


}