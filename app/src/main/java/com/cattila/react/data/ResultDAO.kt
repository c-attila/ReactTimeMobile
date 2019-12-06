package com.cattila.react.data

import androidx.room.*

@Dao
interface ResultDAO {

    @Insert
    fun insert(result: Result)

    @Update
    fun update(result: Result)

    @Delete
    fun delete(result: Result)

    @Query("DELETE FROM result")
    fun delteAllResults()

    @Query("SELECT * FROM result")
    fun getAllResults(): List<Result>

    @Query("SELECT * FROM result ORDER BY elapsed DESC LIMIT 10")
    fun getTopTen(): List<Result>
}