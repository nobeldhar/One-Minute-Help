package com.decimalab.minutehelp.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.decimalab.minutehelp.factory.AppViewModelFactory
import com.decimalab.minutehelp.ui.login.LoginViewModel
import com.decimalab.minutehelp.ui.register.RegisterViewModel
import com.decimalab.minutehelp.di.util.ViewModelKey
import com.decimalab.minutehelp.ui.OneMinuteHelpViewModel
import com.decimalab.minutehelp.ui.comments.CommentsViewModel
import com.decimalab.minutehelp.ui.forgotpassword.ForgotPasswordViewModel
import com.decimalab.minutehelp.ui.dashboard.DashboardViewModel
import com.decimalab.minutehelp.ui.profile.ProfileViewModel
import com.decimalab.minutehelp.ui.profile.addhistory.AddHistoryViewModel
import com.decimalab.minutehelp.ui.profile.createpost.CreatePostViewModel
import com.decimalab.minutehelp.ui.profile.donatehistory.DonateHistoryViewModel
import com.decimalab.minutehelp.ui.profile.group.GroupViewModel
import com.decimalab.minutehelp.ui.profile.settings.address.AddressViewModel
import com.decimalab.minutehelp.ui.profile.settings.basic.BasicViewModel
import com.decimalab.minutehelp.ui.profile.settings.information.InformationViewModel
import com.decimalab.minutehelp.ui.profile.settings.password.PasswordViewModel
import com.decimalab.minutehelp.ui.profile.timeline.TimelineViewModel
import com.decimalab.minutehelp.ui.reply.ReplyViewModel
import com.decimalab.minutehelp.ui.updatecomment.UpdateComViewModel
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
    @ViewModelKey(DashboardViewModel::class)
    abstract fun bindHomeViewModel(homeViewModel: DashboardViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(OneMinuteHelpViewModel::class)
    abstract fun bindOneMinuteHelpViewModel(oneMinuteHelpViewModel: OneMinuteHelpViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CreatePostViewModel::class)
    abstract fun bindCreatePostViewModel(createPostViewModel: CreatePostViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AddHistoryViewModel::class)
    abstract fun bindAddHistoryViewModel(addHistoryViewModel: AddHistoryViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TimelineViewModel::class)
    abstract fun bindTimelineViewModel(timelineViewModel: TimelineViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DonateHistoryViewModel::class)
    abstract fun bindDonateHistoryViewModel(donateHistoryViewModel: DonateHistoryViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun bindProfileViewModel(profileViewModel: ProfileViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(GroupViewModel::class)
    abstract fun bindAddGroupViewModel(groupViewModel: GroupViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CommentsViewModel::class)
    abstract fun bindCommentsViewModel(commentsViewModel: CommentsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ReplyViewModel::class)
    abstract fun bindReplyViewModel(replyViewModel: ReplyViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(UpdateComViewModel::class)
    abstract fun bindUpdateComViewModel(updateComViewModel: UpdateComViewModel): ViewModel


    @Binds
    abstract fun bindViewModelFactory(factory: AppViewModelFactory): ViewModelProvider.Factory

}