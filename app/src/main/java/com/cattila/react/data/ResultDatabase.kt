package com.cattila.react.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Result::class], version = 2)
@TypeConverters(Converters::class)
abstract class ResultDatabase : RoomDatabase() {

    abstract fun resultDao(): ResultDao

    companion object {
        private var instance: ResultDatabase? = null

        fun getInstance(context: Context): ResultDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    ResultDatabase::class.java, "database"
                ).build()
            }
            return instance
        }

        fun destroyInstance() {
            instance = null
        }

    }
}