package com.decimalab.minutehelp.ui.updatecomment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.decimalab.minutehelp.data.local.entities.Comment
import com.decimalab.minutehelp.data.repository.DashboardRepository
import javax.inject.Inject

class
UpdateComViewModel
@Inject constructor(
    val dashboardRepository: DashboardRepository
): ViewModel() {
    var commentId: Int? = null

    var name:String? = null
    var text:String? = null
    var image:String? = null

    var isMain: Boolean = true

    val _update = MutableLiveData<Boolean>()
    val _delete = MutableLiveData<Boolean>()

    val getUpdateResult = Transformations.switchMap(_update) {
        commentId?.let { it1 -> text?.let { it2 -> dashboardRepository.updateComment(it1, it2) } }
    }

    val getDeleteResult = Transformations.switchMap(_delete) {
        commentId?.let { it1 -> dashboardRepository.deleteComment(it1) }
    }

    fun onUpdateClicked(){
        commentId?.let { commentId->
            text?.let { text->
                _update.value = true
            }
        }
    }

    fun onDeleteClicked(){
        commentId?.let { commentId->
            _delete.value = true
        }
    }
}