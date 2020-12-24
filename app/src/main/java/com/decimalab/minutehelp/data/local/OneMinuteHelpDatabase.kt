package com.decimalab.minutehelp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.decimalab.minutehelp.data.local.converters.ProfileConverter
import com.decimalab.minutehelp.data.local.daos.AddressDao
import com.decimalab.minutehelp.data.local.daos.ProfileDao
import com.decimalab.minutehelp.data.local.entities.City
import com.decimalab.minutehelp.data.local.entities.District
import com.decimalab.minutehelp.data.local.entities.TimeLinePost
import com.decimalab.minutehelp.data.local.entities.Upazilla


@Database(entities = [District::class, Upazilla::class, City::class, TimeLinePost::class], version = 1, exportSchema = false)
@TypeConverters(value = [(ProfileConverter::class)])
abstract class OneMinuteHelpDatabase : RoomDatabase() {

    abstract fun addressDao(): AddressDao
    abstract fun profileDao(): ProfileDao
}