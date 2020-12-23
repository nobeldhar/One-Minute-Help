package com.decimalab.minutehelp.di.module

import com.decimalab.minutehelp.ui.profile.createpost.CreatePostFragment
import com.decimalab.minutehelp.ui.forgotpassword.ForgotPasswordFragment
import com.decimalab.minutehelp.ui.gallery.GalleryFragment
import com.decimalab.minutehelp.ui.home.HomeFragment
import com.decimalab.minutehelp.ui.login.LoginFragment
import com.decimalab.minutehelp.ui.profile.ProfileFragment
import com.decimalab.minutehelp.ui.profile.addhistory.AddHistoryFragment
import com.decimalab.minutehelp.ui.register.RegisterFragment
import com.decimalab.minutehelp.ui.profile.settings.address.AddressFragment
import com.decimalab.minutehelp.ui.profile.settings.basic.BasicFragment
import com.decimalab.minutehelp.ui.profile.settings.information.InformationFragment
import com.decimalab.minutehelp.ui.profile.settings.password.PasswordFragment
import com.decimalab.minutehelp.ui.slideshow.SlideshowFragment
import com.decimalab.minutehelp.ui.splash.SplashFragment
import com.decimalab.minutehelp.ui.verifycode.VerifyCodeFragment
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

    @ContributesAndroidInjector
    abstract fun contributeVerifyFragment(): VerifyCodeFragment

    @ContributesAndroidInjector
    abstract fun contributeBasicFragment(): BasicFragment

    @ContributesAndroidInjector
    abstract fun contributeInformationFragment(): InformationFragment

    @ContributesAndroidInjector
    abstract fun contributeAddressFragment(): AddressFragment

    @ContributesAndroidInjector
    abstract fun contributePasswordFragment(): PasswordFragment

    @ContributesAndroidInjector
    abstract fun contributeSplashFragment(): SplashFragment

    @ContributesAndroidInjector
    abstract fun contributeProfileFragment(): ProfileFragment

    @ContributesAndroidInjector
    abstract fun contributeCreatePostFragment(): CreatePostFragment

    @ContributesAndroidInjector
    abstract fun contributeAddHistoryFragment(): AddHistoryFragment

}