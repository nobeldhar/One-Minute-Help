package com.decimalab.minutehelp.di.module

import com.decimalab.minutehelp.OneMinuteHelpActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector
@Suppress("unused")
@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [FragmentBuilderModule::class])
    abstract fun contributeOneMinuteHelpActivity(): OneMinuteHelpActivity
}