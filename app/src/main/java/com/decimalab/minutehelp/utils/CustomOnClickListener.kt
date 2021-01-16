package com.decimalab.minutehelp.utils

import com.decimalab.minutehelp.data.local.entities.Comment
import com.decimalab.minutehelp.data.local.entities.TimeLinePost

interface CustomOnClickListener {

    fun onSettingsOptionClicked(option: String){}
    fun onMainCommentReply(comment: Comment){}
    fun onChildCommentReply(comment: Comment, child: Comment.Child){}
    fun onInterestedClicked(timeLinePost: TimeLinePost){}
    fun onCommentsClicked(timeLinePost: TimeLinePost){}
    fun onShareClicked(timeLinePost: TimeLinePost){}
}