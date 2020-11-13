package com.decimalab.minutehelp.di.component

import android.app.Application
import com.decimalab.minutehelp.OneMinuteHelpApp
import com.decimalab.minutehelp.di.module.ActivityModule
import com.decimalab.minutehelp.di.module.AppModule
import com.nobel.dhar.offer.di.module.ViewModelFactoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityModule::class
    ]
)

interface AppComponent : AndroidInjector<OneMinuteHelpApp> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}