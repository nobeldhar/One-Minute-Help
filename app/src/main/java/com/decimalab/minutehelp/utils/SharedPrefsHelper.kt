package com.decimalab.minutehelp.utils

import android.content.SharedPreferences
import com.decimalab.minutehelp.data.remote.requests.SettingsRequest
import com.decimalab.minutehelp.data.remote.responses.AuthResponse
import com.decimalab.minutehelp.data.remote.responses.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPrefsHelper @Inject constructor(private val sharedPreferences: SharedPreferences) {


    private val USER_IMAGE: String = "one_minute_help_image"
    private val SHARED_PREFS_NAME = "one_minute_help_shared_pref"
    private val USER_ID = "one_minute_help_user_id"
    private val USER_NAME = "one_minute_help_user_name"
    private val USER_EMAIL = "one_minute_help_user_email"
    private val USER_PHONE = "one_minute_help_user_phone"
    private val USER_IS_VERIFIED = "one_minute_help_is_versified"
    private val USER_KEY_ACCESS_TOKEN = "one_minute_help_access_token"
    private val USER_POSTCODE = "one_minute_help_postcode"
    private val USER_CITY = "one_minute_help_city_id"
    private val USER_THANA = "one_minute_help_thana_id"
    private val USER_DISTRICT = "one_minute_help_district_id"
    private val USER_GENDER = "one_minute_help_gender_id"
    private val USER_BLOOD = "one_minute_help_blood_id"
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
            var user = User(id, name, email, phone, isVerified)
            get<String>(USER_IMAGE)?.let {
                user = User(id, name, email, phone, isVerified, it)
            }
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
        save(USER_DISTRICT, authResponse.data.info?.district?.name)
        save(USER_THANA, authResponse.data.info?.thana?.name)
        save(USER_CITY, authResponse.data.info?.city?.name)
        save(USER_POSTCODE, authResponse.data.info?.postcode)
        save(USER_DATEOFBIRTH, authResponse.data.info?.dateOfBirth)
        save(USER_BLOOD, authResponse.data.info?.blood?.blood)
        save(USER_GENDER, authResponse.data.info?.gender?.name)
        save(USER_IMAGE, authResponse.data.info?.image)


    }

    fun saveAuthToken(token: String) {
        save(USER_KEY_ACCESS_TOKEN, token)
    }

    fun updateAddress(settingsRequest: SettingsRequest) {
        save(USER_DISTRICT, settingsRequest.district)
        save(USER_THANA, settingsRequest.thana)
        save(USER_CITY, settingsRequest.city)
        save(USER_POSTCODE, settingsRequest.postcode)
    }

    fun updateInfo(settingsRequest: SettingsRequest) {
        save(USER_BLOOD, settingsRequest.blood)
        save(USER_GENDER, settingsRequest.gender)
        save(USER_DATEOFBIRTH, settingsRequest.date_of_birth)
    }

    fun hasAddress(): Boolean {
        return has(USER_DISTRICT) && has(USER_THANA)
    }

    fun hasInfo(): Boolean {
        return has(USER_BLOOD) && has(USER_DATEOFBIRTH)
    }

    fun getAddress(): SettingsRequest? {
        return if (hasAddress()) {
            get<String>(USER_DISTRICT)?.let { it1 ->
                get<String>(USER_THANA)?.let { it2 ->
                    get<String>(USER_CITY)?.let { it3 ->
                        get<String>(USER_POSTCODE)?.let { it4 ->
                            val settingsRequest = SettingsRequest(
                                district = it1,
                                thana = it2,
                                city = it3,
                                postcode = it4
                            )
                            settingsRequest
                        }
                    }
                }
            }
        } else {
            null
        }
    }

    fun getInfo(): SettingsRequest? {
        return if (hasAddress()) {
            get<String>(USER_BLOOD)?.let { it1 ->
                get<String>(USER_DATEOFBIRTH)?.let { it2 ->
                    get<String>(USER_GENDER)?.let { it3 ->
                        val settingsRequest = SettingsRequest(
                            blood = it1,
                            date_of_birth = it2,
                            gender = it3,
                        )
                        settingsRequest
                    }
                }
            }
        } else {
            null
        }
    }
}