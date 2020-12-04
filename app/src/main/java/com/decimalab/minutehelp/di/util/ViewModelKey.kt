package com.decimalab.minutehelp.di.util

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey (val value: KClass<out ViewModel>)