package com.decimalab.minutehelp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.decimalab.minutehelp.data.local.daos.AddressDao
import com.decimalab.minutehelp.data.local.entities.City
import com.decimalab.minutehelp.data.local.entities.District
import com.decimalab.minutehelp.data.local.entities.Upazilla


@Database(entities = [District::class, Upazilla::class, City::class], version = 1, exportSchema = false)
abstract class OneMinuteHelpDatabase : RoomDatabase() {

    abstract fun addressDao(): AddressDao
}