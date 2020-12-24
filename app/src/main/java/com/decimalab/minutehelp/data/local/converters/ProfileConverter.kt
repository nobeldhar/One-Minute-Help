package com.decimalab.minutehelp.data.local.converters

import androidx.room.TypeConverter
import com.decimalab.minutehelp.data.local.entities.Blood
import com.decimalab.minutehelp.data.local.entities.City
import com.decimalab.minutehelp.data.local.entities.District
import com.decimalab.minutehelp.data.local.entities.Upazilla
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ProfileConverter {
    @TypeConverter
    fun fromStringToBlood(value: String): Blood? {
        val listType = object : TypeToken<Blood>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromBloodToString(blood: Blood?): String {
        val gson = Gson()
        return gson.toJson(blood)
    }

    @TypeConverter
    fun fromStringToDistrict(value: String): District? {
        val listType = object : TypeToken<District>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromDistrictToString(district: District?): String {
        val gson = Gson()
        return gson.toJson(district)
    }

    @TypeConverter
    fun fromStringToUpazilla(value: String): Upazilla? {
        val listType = object : TypeToken<Upazilla>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromUpazillaToString(upazilla: Upazilla?): String {
        val gson = Gson()
        return gson.toJson(upazilla)
    }

    @TypeConverter
    fun fromStringToCity(value: String): City? {
        val listType = object : TypeToken<City>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromCityToString(city: City?): String {
        val gson = Gson()
        return gson.toJson(city)
    }
}