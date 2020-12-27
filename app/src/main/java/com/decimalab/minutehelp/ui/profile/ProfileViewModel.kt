package com.decimalab.minutehelp.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.decimalab.minutehelp.data.remote.responses.AuthResponse
import com.decimalab.minutehelp.data.remote.responses.ImageResponse
import com.decimalab.minutehelp.data.repository.ProfileRepository
import com.decimalab.minutehelp.utils.Resource
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.http.Multipart
import java.io.File
import javax.inject.Inject


class
ProfileViewModel
@Inject constructor(
    val profileRepository: ProfileRepository
): ViewModel() {
    fun uploadImage(file: File): LiveData<Resource<AuthResponse>>? {
         var part: MultipartBody.Part? = null
        file?.let {
            val fileReqBody  = it.asRequestBody("image/*".toMediaTypeOrNull())
            part = MultipartBody.Part.createFormData("image", file!!.name, fileReqBody)
        }
        return part?.let { profileRepository.uploadProfileImage(it) }
    }

    fun getProfileImage(): LiveData<Resource<ImageResponse>> {
        return profileRepository.getProfileImage()
    }
}