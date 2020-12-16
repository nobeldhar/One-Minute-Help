package com.decimalab.minutehelp.utils

import android.content.SharedPreferences
import com.decimalab.minutehelp.data.remote.requests.SettingsRequest
import com.decimalab.minutehelp.data.remote.responses.AuthResponse
import com.decimalab.minutehelp.data.remote.responses.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPrefsHelper @Inject constructor(private val sharedPreferences: SharedPreferences) {



    private val SHARED_PREFS_NAME = "one_minute_help_shared_pref"
    private val USER_ID = "one_minute_help_user_id"
    private val USER_NAME = "one_minute_help_user_name"
    private val USER_EMAIL = "one_minute_help_user_email"
    private val USER_PHONE = "one_minute_help_user_phone"
    private val USER_IS_VERIFIED = "one_minute_help_is_versified"
    private val USER_KEY_ACCESS_TOKEN = "one_minute_help_access_token"
    private val USER_POSTCODE = "one_minute_help_postcode"
    private val USER_CITY_ID = "one_minute_help_city_id"
    private val USER_THANA_ID = "one_minute_help_thana_id"
    private val USER_DISTRICT_ID = "one_minute_help_district_id"
    private val USER_GENDER_ID = "one_minute_help_gender_id"
    private val USER_BLOOD_ID = "one_minute_help_blood_id"
    private val USER_DATEOFBIRTH = "one_minute_help_date_of_birth"

    private fun delete(key: String?) {
        if (sharedPreferences.contains(key)) {
            getEditor().remove(key).commit()
        }
    }

    private fun save(key: String?, value: Any?) {
        val editor = getEditor()
        if (value is Boolean) {
            editor.putBoolean(key, (value as Boolean?)!!)
        } else if (value is Int) {
            editor.putInt(key, (value as Int?)!!)
        } else if (value is Float) {
            editor.putFloat(key, (value as Float?)!!)
        } else if (value is Long) {
            editor.putLong(key, (value as Long?)!!)
        } else if (value is String) {
            editor.putString(key, value as String?)
        } else if (value is Enum<*>) {
            editor.putString(key, value.toString())
        } else if (value != null) {
            throw RuntimeException("Attempting to save non-supported preference")
        }
        editor.commit()
    }

    operator fun <T> get(key: String?): T? {
        return sharedPreferences.all[key] as T?
    }

    operator fun <T> get(key: String?, defValue: T): T {
        val returnValue = sharedPreferences.all[key] as T?
        return returnValue ?: defValue
    }

    private fun has(key: String?): Boolean {
        return sharedPreferences.contains(key)
    }


    /*fun saveQbUser(qbUser: QBUser) {
        save(USER_ID, qbUser.getId())
        save(USER_LOGIN, qbUser.getLogin())
        save(USER_PASSWORD, qbUser.getPassword())
        save(USER_FULL_NAME, qbUser.getFullName())
        save(USER_TAGS, qbUser.getTags().getItemsAsString())
        save(USER_KEY_ACCESS_TOKEN)
    }*/

    fun removeQbUser() {
        delete(USER_ID)
        delete(USER_NAME)
        delete(USER_EMAIL)
        delete(USER_PHONE)
        delete(USER_IS_VERIFIED)
        delete(USER_KEY_ACCESS_TOKEN)
    }


    /**
     * Function to fetch auth token
     */
    fun fetchAuthToken(): String? {
        return sharedPreferences.getString(USER_KEY_ACCESS_TOKEN, null)
    }

    fun getUser(): User? {
        return if (hasUser()) {
            val id = get<Int>(USER_ID)!!
            val name = get<String>(USER_NAME)!!
            val email = get<String>(USER_EMAIL)!!
            val phone = get<String>(USER_PHONE)!!
            val isVerified = get<Int>(USER_IS_VERIFIED)!!

            val user = User(id, name, email, phone, isVerified)
            user
        } else {
            null
        }
    }

    fun hasUser(): Boolean {
        return has(USER_ID) && has(USER_NAME)
    }

    fun clearAllData() {
        val editor = getEditor()
        editor.clear().commit()
    }

    private fun getEditor(): SharedPreferences.Editor {
        return sharedPreferences.edit()
    }

    fun saveUser(authResponse: AuthResponse) {
        val user = authResponse.data
        save(USER_ID, user.id)
        save(USER_NAME, user.name)
        save(USER_EMAIL, user.email)
        save(USER_PHONE, user.phone)
        save(USER_IS_VERIFIED, user.isVerified)
        authResponse.accessToken?.let {
            save(USER_KEY_ACCESS_TOKEN, it)
        }
        save(USER_DISTRICT_ID, authResponse.data.info.districtId)
        save(USER_THANA_ID, authResponse.data.info.thanaId)
        save(USER_CITY_ID, authResponse.data.info.cityId)
        save(USER_POSTCODE, authResponse.data.info.postcode)
        save(USER_DATEOFBIRTH, authResponse.data.info.postcode)
        save(USER_BLOOD_ID, authResponse.data.info.postcode)
        save(USER_GENDER_ID, authResponse.data.info.postcode)


    }

    fun saveAuthToken(token: String) {
        save(USER_KEY_ACCESS_TOKEN, token)
    }

    fun updateAddress(settingsRequest: SettingsRequest) {
        save(USER_DISTRICT_ID, settingsRequest.district_id)
        save(USER_THANA_ID, settingsRequest.thana_id)
        save(USER_CITY_ID, settingsRequest.city_id)
        save(USER_POSTCODE, settingsRequest.postcode)
    }

    fun hasAddress(): Boolean {
        return has(USER_DISTRICT_ID) && has(USER_THANA_ID)
    }

    fun getAddress(): SettingsRequest? {
        return if (hasAddress()) {
            val district_id = get<Int>(USER_DISTRICT_ID)!!
            val thana_id = get<Int>(USER_THANA_ID)!!
            val city_id = get<Int>(USER_CITY_ID)!!
            val postcode = get<String>(USER_POSTCODE)!!

            val settingsRequest = SettingsRequest(district_id = district_id,
                    thana_id = thana_id,
                    city_id = city_id,
                    postcode = postcode)
            settingsRequest
        } else {
            null
        }
    }
}