package com.decimalab.minutehelp.di.module

import com.decimalab.minutehelp.ui.login.LoginFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeLoginFragment(): LoginFragment


}