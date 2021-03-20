package com.decimalab.minutehelp.data.local.converters

import androidx.room.TypeConverter
import com.decimalab.minutehelp.data.local.entities.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ProfileConverter {
    @TypeConverter
    fun fromStringToBlood(value: String): Blood? {
        val listType = object : TypeToken<Blood?>() {}.type
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

    @TypeConverter
    fun fromStringToUser(value: String): TimeLinePost.User? {
        val listType = object : TypeToken<TimeLinePost.User>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromUserToString(user: TimeLinePost.User): String {
        val gson = Gson()
        return gson.toJson(user)
    }

    @TypeConverter
    fun fromStringToTimelinePost(value: String): TimeLinePost? {
        val listType = object : TypeToken<TimeLinePost>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromTimelinePostToString(timeLinePost: TimeLinePost): String {
        val gson = Gson()
        return gson.toJson(timeLinePost)
    }

    @TypeConverter
    fun fromStringToUserList(value: String): List<TimeLinePost.User>? {
        val listType = object : TypeToken<List<TimeLinePost.User>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromUserListToString(users: List<TimeLinePost.User>): String {
        val gson = Gson()
        return gson.toJson(users)
    }

    @TypeConverter
    fun fromStringToLikeList(value: String): List<TimeLinePost.Like>? {
        val listType = object : TypeToken<List<TimeLinePost.Like>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromLikeListToString(likes: List<TimeLinePost.Like>): String {
        val gson = Gson()
        return gson.toJson(likes)
    }

    @TypeConverter
    fun fromStringToTimelinePostList(value: String):List<TimeLinePost>? {
        val listType = object : TypeToken<List<TimeLinePost>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromTimelinePostListToString(timeLinePosts: List<TimeLinePost>): String {
        val gson = Gson()
        return gson.toJson(timeLinePosts)
    }

    @TypeConverter
    fun fromStringToUserInfo(value: String): TimeLinePost.User.Info? {
        val listType = object : TypeToken<TimeLinePost.User.Info?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromUserInfoToString(info: TimeLinePost.User.Info?): String {
        val gson = Gson()
        return gson.toJson(info)
    }

    @TypeConverter
    fun fromStringToChild(value: String): Comment.Child? {
        val listType = object : TypeToken<Comment.Child>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromChildInfoToString(child: Comment.Child): String {
        val gson = Gson()
        return gson.toJson(child)
    }

    @TypeConverter
    fun fromStringToRole(value: String): TimeLinePost.User.Role? {
        val listType = object : TypeToken<TimeLinePost.User.Role>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromRoleToString(role: TimeLinePost.User.Role): String {
        val gson = Gson()
        return gson.toJson(role)
    }

    @TypeConverter
    fun fromStringToHistory(value: String): History? {
        val listType = object : TypeToken<History>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromHistoryToString(history: History): String {
        val gson = Gson()
        return gson.toJson(history)
    }

    @TypeConverter
    fun fromStringToGroup(value: String): Group? {
        val listType = object : TypeToken<Group?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromGroupToString(group: Group?): String {
        val gson = Gson()
        return gson.toJson(group)
    }

}