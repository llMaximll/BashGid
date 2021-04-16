package com.github.llmaximll.bashgid

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.github.llmaximll.bashgid.database.BashDatabase
import com.github.llmaximll.bashgid.dataclasses.DatabaseClass
import java.util.concurrent.Executors

private const val DATABASE_NAME = "bash-database"

class BashRepository private constructor(context: Context) {

    private val database: BashDatabase = Room.databaseBuilder(
        context.applicationContext,
        BashDatabase::class.java,
        DATABASE_NAME
    ).build()

    private val bashDao = database.bashDao()
    private val executor = Executors.newSingleThreadExecutor()

    fun getBashDatabases(): LiveData<List<DatabaseClass>> = bashDao.getBashDatabases()

    fun getBashDatabase(titleObject: String): LiveData<DatabaseClass> = bashDao.getBashDatabase(titleObject)

    fun updateBashDatabase(databaseClass: DatabaseClass) {
        executor.execute {
            bashDao.updateBashDatabase(databaseClass)
        }
    }

    fun addBashDatabase(databaseClass: DatabaseClass) {
        executor.execute {
            bashDao.addBashDatabase(databaseClass)
        }
    }

    companion object {
        private var INSTANCE: BashRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = BashRepository(context)
            }
        }

        fun get(): BashRepository {
            return checkNotNull(INSTANCE) {
                "BashRepository must be initialized"
            }
        }
    }
}