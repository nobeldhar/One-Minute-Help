package com.decimalab.minutehelp.ui.reply

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.decimalab.minutehelp.data.local.entities.Comment
import com.decimalab.minutehelp.data.remote.requests.AuthRequest
import com.decimalab.minutehelp.data.repository.DashboardRepository
import javax.inject.Inject

class
ReplyViewModel
@Inject constructor(
    val dashboardRepository: DashboardRepository
): ViewModel() {
    var comment: Comment? = null
    var child: Comment.Child? = null
    var postId: Int? = null

    var name:String? = null
    var text:String? = null
    var image:String? = null

    var reply:String? = null
    var isMain: Boolean? = null

    val _reply = MutableLiveData<Boolean>()
    val getRegisterResult = Transformations.switchMap(_reply) {
        postId?.let { it1 ->
            reply?.let { it2 ->
                dashboardRepository.commentOnPost(
                    it2,
                    it1, comment?.id, child?.id)
            }
        }
    }

    fun onReplyClicked(){
        reply?.let {
            isMain?.let { ismain ->
                if (ismain){
                    comment?.let { comment ->
                        postId?.let { postId ->
                            _reply.value = true
                        }
                    }
                }else{
                    comment?.let { comment ->
                        child?.let { child ->
                            postId?.let { postId ->
                                _reply.value = false
                            }
                        }
                    }
                }
            }
        }
    }
}