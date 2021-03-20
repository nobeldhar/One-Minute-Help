package com.decimalab.minutehelp.utils

import android.widget.TextView
import com.decimalab.minutehelp.data.local.entities.Comment
import com.decimalab.minutehelp.data.local.entities.Group
import com.decimalab.minutehelp.data.local.entities.Post
import com.decimalab.minutehelp.data.local.entities.TimeLinePost

interface CustomOnClickListener {

    fun onSettingsOptionClicked(option: String){}
    fun onMainCommentReply(comment: Comment){}
    fun onChildCommentReply(comment: Comment, child: Comment.Child){}
    fun onMainCommentUpdate(comment: Comment){}
    fun onChildCommentUpdate( child: Comment.Child){}
    fun onInterestedClicked( post: Post){}
    fun onCommentsClicked(post: Post){}
    fun onShareClicked(post: Post){}
    fun onTMInterestedClicked(timeLinePost: TimeLinePost){}
    fun onTMCommentsClicked(timeLinePost: TimeLinePost){}
    fun onTMShareClicked(timeLinePost: TimeLinePost){}
    fun onGroupClicked(group: Group){}
}