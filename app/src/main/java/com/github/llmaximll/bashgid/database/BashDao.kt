package com.github.llmaximll.bashgid.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.github.llmaximll.bashgid.dataclasses.DatabaseClass

@Dao
interface BashDao {

    @Query("SELECT * FROM databaseclass")
    fun getBashDatabases(): LiveData<List<DatabaseClass>>

    @Query("SELECT * FROM databaseclass WHERE titleObject=(:titleObject)")
    fun getBashDatabase(titleObject: String): LiveData<DatabaseClass>

    @Update
    fun updateBashDatabase(databaseClass: DatabaseClass)

    @Insert
    fun addBashDatabase(databaseClass: DatabaseClass)
}