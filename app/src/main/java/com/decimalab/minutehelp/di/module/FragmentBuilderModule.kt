package com.decimalab.minutehelp.di.module

import com.decimalab.minutehelp.ui.comments.CommentsFragment
import com.decimalab.minutehelp.ui.profile.createpost.CreatePostFragment
import com.decimalab.minutehelp.ui.forgotpassword.ForgotPasswordFragment
import com.decimalab.minutehelp.ui.gallery.GalleryFragment
import com.decimalab.minutehelp.ui.dashboard.DashboardFragment
import com.decimalab.minutehelp.ui.dashboard.allposts.AllPostsFragment
import com.decimalab.minutehelp.ui.dashboard.group.GroupFragment
import com.decimalab.minutehelp.ui.dashboard.members.MembersFragment
import com.decimalab.minutehelp.ui.dashboard.topdoner.TopDonorsFragment
import com.decimalab.minutehelp.ui.groupdetails.GroupDetailsFragment
import com.decimalab.minutehelp.ui.groupdetails.groupcreatepost.GroupCreatePostFragment
import com.decimalab.minutehelp.ui.groupdetails.members.GroupMembersFragment
import com.decimalab.minutehelp.ui.groupdetails.settings.GroupSettingsFragment
import com.decimalab.minutehelp.ui.groupdetails.settings.groupaddress.GroupAddreeFragment
import com.decimalab.minutehelp.ui.groupdetails.settings.groupbasic.GroupBasicFragment
import com.decimalab.minutehelp.ui.groupdetails.settings.groupinfo.GroupInfoFragment
import com.decimalab.minutehelp.ui.groupdetails.settings.grouppermission.GroupPermissionFragment
import com.decimalab.minutehelp.ui.groupdetails.timeline.GroupTimelineFragment
import com.decimalab.minutehelp.ui.login.LoginFragment
import com.decimalab.minutehelp.ui.profile.ProfileFragment
import com.decimalab.minutehelp.ui.profile.addhistory.AddHistoryFragment
import com.decimalab.minutehelp.ui.profile.donatehistory.DonateHistoryFragment
import com.decimalab.minutehelp.ui.register.RegisterFragment
import com.decimalab.minutehelp.ui.profile.settings.address.AddressFragment
import com.decimalab.minutehelp.ui.profile.settings.basic.BasicFragment
import com.decimalab.minutehelp.ui.profile.settings.information.InformationFragment
import com.decimalab.minutehelp.ui.profile.settings.password.PasswordFragment
import com.decimalab.minutehelp.ui.profile.timeline.TimelineFragment
import com.decimalab.minutehelp.ui.reply.ReplyFragment
import com.decimalab.minutehelp.ui.slideshow.SlideshowFragment
import com.decimalab.minutehelp.ui.splash.SplashFragment
import com.decimalab.minutehelp.ui.updatecomment.UpdateComFragment
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
    abstract fun contributeHOmeFragment(): DashboardFragment

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

    @ContributesAndroidInjector
    abstract fun contributeTimelineFragment(): TimelineFragment

    @ContributesAndroidInjector
    abstract fun contributeDonateHistoryFragment(): DonateHistoryFragment


    @ContributesAndroidInjector
    abstract fun contributeCommentsFragment(): CommentsFragment

    @ContributesAndroidInjector
    abstract fun contributeReplyFragment(): ReplyFragment

    @ContributesAndroidInjector
    abstract fun contributeUpdateComFragment(): UpdateComFragment

    @ContributesAndroidInjector
    abstract fun contributeAllPostsFragment(): AllPostsFragment


    @ContributesAndroidInjector
    abstract fun contributeMembersFragment(): MembersFragment

    @ContributesAndroidInjector
    abstract fun contributeTopDonorsFragment(): TopDonorsFragment

    @ContributesAndroidInjector
    abstract fun contributeGroupFragment(): GroupFragment

    @ContributesAndroidInjector
    abstract fun contributeGroupMembersFragment(): GroupMembersFragment

    @ContributesAndroidInjector
    abstract fun contributeGroupSettingsFragment(): GroupSettingsFragment

    @ContributesAndroidInjector
    abstract fun contributeGroupTimelineFragment(): GroupTimelineFragment

    @ContributesAndroidInjector
    abstract fun contributeGroupDetailsFragment(): GroupDetailsFragment

    @ContributesAndroidInjector
    abstract fun contributeGroupBasicFragment(): GroupBasicFragment

    @ContributesAndroidInjector
    abstract fun contributeGroupInfoFragment(): GroupInfoFragment

    @ContributesAndroidInjector
    abstract fun contributeGroupAddreeFragment(): GroupAddreeFragment

    @ContributesAndroidInjector
    abstract fun contributeGroupCreatePostFragment(): GroupCreatePostFragment

    @ContributesAndroidInjector
    abstract fun contributeGroupPermissionFragment(): GroupPermissionFragment

}