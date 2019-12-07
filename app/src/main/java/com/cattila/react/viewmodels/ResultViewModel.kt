package com.cattila.react.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.cattila.react.data.ResultRepository
import com.cattila.react.data.Result

class ResultViewModel(application: Application) : AndroidViewModel(application) {
    private var repository: ResultRepository =
        ResultRepository(application)
    private var allResults: LiveData<List<Result>> = repository.getAllResults()

    fun insert(result: Result) {
        repository.insert(result)
    }

    fun update(result: Result) {
        repository.update(result)
    }

    fun delete(result: Result) {
        repository.delete(result)
    }

    fun deleteAllResults() {
        repository.deleteAllResults()
    }

    fun getAllResults(): LiveData<List<Result>> {
        return allResults
    }
}