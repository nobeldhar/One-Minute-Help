package com.decimalab.minutehelp.data.local.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.decimalab.minutehelp.data.local.entities.Post
@Dao
interface DashboardDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPosts(items: List<Post>)

    @Query("SELECT * from post_table ORDER BY id DESC")
    fun getPosts(): LiveData<List<Post>>
}