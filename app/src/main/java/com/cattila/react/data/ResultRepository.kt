package com.cattila.react.data

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData

class ResultRepository(application: Application) {

    private var resultDao: ResultDao

    private var allResults: LiveData<List<Result>>

    init {
        val database: ResultDatabase = ResultDatabase.getInstance(
            application.applicationContext
        )!!
        resultDao = database.resultDao()
        allResults = resultDao.getAllResults()
    }

    fun insert(result: Result) {
        val insertResultAsyncTask = InsertResultAsyncTask(resultDao).execute(result)
    }

    fun update(result: Result) {
        val updateResultAsyncTask = UpdateResultAsyncTask(resultDao).execute(result)
    }


    fun delete(result: Result) {
        val deleteResultAsyncTask = DeleteResultAsyncTask(resultDao).execute(result)
    }

    fun deleteAllResults() {
        val deleteAllResultsAsyncTask = DeleteAllResultsAsyncTask(
            resultDao
        ).execute()
    }

    fun getAllResults(): LiveData<List<Result>> {
        return allResults
    }

    companion object {
        private class InsertResultAsyncTask(resultDao: ResultDao) : AsyncTask<Result, Unit, Unit>() {
            val resultDao = resultDao

            override fun doInBackground(vararg p0: Result?) {
                resultDao.insert(p0[0]!!)
            }
        }

        private class UpdateResultAsyncTask(resultDao: ResultDao) : AsyncTask<Result, Unit, Unit>() {
            val resultDao = resultDao

            override fun doInBackground(vararg p0: Result?) {
                resultDao.update(p0[0]!!)
            }
        }

        private class DeleteResultAsyncTask(resultDao: ResultDao) : AsyncTask<Result, Unit, Unit>() {
            val resultDao = resultDao

            override fun doInBackground(vararg p0: Result?) {
                resultDao.delete(p0[0]!!)
            }
        }

        private class DeleteAllResultsAsyncTask(resultDao: ResultDao) : AsyncTask<Unit, Unit, Unit>() {
            val resultDao = resultDao

            override fun doInBackground(vararg p0: Unit?) {
                resultDao.deleteAllResults()
            }
        }
    }
}