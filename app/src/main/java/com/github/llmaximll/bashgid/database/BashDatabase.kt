package com.github.llmaximll.bashgid.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.github.llmaximll.bashgid.dataclasses.DatabaseClass

@Database(entities = [ DatabaseClass::class ], version = 1, exportSchema = false)
abstract class BashDatabase : RoomDatabase() {
    abstract fun bashDao(): BashDao
}