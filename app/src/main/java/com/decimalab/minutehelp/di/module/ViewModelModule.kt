package com.decimalab.minutehelp.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.decimalab.minutehelp.factory.AppViewModelFactory
import com.decimalab.minutehelp.ui.login.LoginViewModel
import com.decimalab.minutehelp.ui.register.RegisterViewModel
import com.decimalab.minutehelp.di.util.ViewModelKey
import com.decimalab.minutehelp.ui.OneMinuteHelpViewModel
import com.decimalab.minutehelp.ui.forgotpassword.ForgotPasswordViewModel
import com.decimalab.minutehelp.ui.home.HomeViewModel
import com.decimalab.minutehelp.ui.profile.settings.address.AddressViewModel
import com.decimalab.minutehelp.ui.profile.settings.basic.BasicViewModel
import com.decimalab.minutehelp.ui.profile.settings.information.InformationViewModel
import com.decimalab.minutehelp.ui.profile.settings.password.PasswordViewModel
import com.decimalab.minutehelp.ui.verifycode.VerifyCodeViewModel

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(loginViewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RegisterViewModel::class)
    abstract fun bindRegisterViewModel(registerViewModel: RegisterViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ForgotPasswordViewModel::class)
    abstract fun bindForgotPassViewModel(forgotPasswordViewModel: ForgotPasswordViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BasicViewModel::class)
    abstract fun bindBasicViewModel(forgotPasswordViewModel: BasicViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(InformationViewModel::class)
    abstract fun bindInformationViewModel(forgotPasswordViewModel: InformationViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AddressViewModel::class)
    abstract fun bindAddressViewModel(forgotPasswordViewModel: AddressViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PasswordViewModel::class)
    abstract fun bindPasswordViewModel(forgotPasswordViewModel: PasswordViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(VerifyCodeViewModel::class)
    abstract fun bindVerifyCodeViewModel(forgotPasswordViewModel: VerifyCodeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(OneMinuteHelpViewModel::class)
    abstract fun bindOneMinuteHelpViewModel(oneMinuteHelpViewModel: OneMinuteHelpViewModel): ViewModel


    @Binds
    abstract fun bindViewModelFactory(factory: AppViewModelFactory): ViewModelProvider.Factory

}