package com.cattila.react.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ResultDao {

    @Insert
    fun insert(result: Result)

    @Update
    fun update(result: Result)

    @Delete
    fun delete(result: Result)

    @Query("DELETE FROM result")
    fun deleteAllResults()

    @Query("SELECT * FROM result")
    fun getAllResults(): LiveData<List<Result>>

    @Query("SELECT * FROM result ORDER BY elapsed DESC LIMIT 10")
    fun getTopTen(): LiveData<List<Result>>
}