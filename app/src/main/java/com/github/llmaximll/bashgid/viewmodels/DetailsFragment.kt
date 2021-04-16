package com.github.llmaximll.bashgid.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.github.llmaximll.bashgid.BashRepository
import com.github.llmaximll.bashgid.dataclasses.DatabaseClass

class DetailsFragment : ViewModel() {

    private val bashRepository = BashRepository.get()
    val bashListLiveData = bashRepository.getBashDatabases()
    private val bashTitleLiveData = MutableLiveData<String>()

    val bashLiveData: LiveData<DatabaseClass> =
        Transformations.switchMap(bashTitleLiveData) { bashTitle ->
            bashRepository.getBashDatabase(bashTitle)
        }

    fun loadDatabaseClass(bashTitle: String) {
        bashTitleLiveData.value = bashTitle
    }

    fun addDatabaseClass(databaseClass: DatabaseClass) {
        bashRepository.addBashDatabase(databaseClass)
    }

    fun saveDatabaseClass(databaseClass: DatabaseClass) {
        bashRepository.updateBashDatabase(databaseClass)
    }
}