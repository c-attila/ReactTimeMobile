package com.cattila.react.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey
import java.time.ZonedDateTime

@Entity(tableName = "result")
data class Result(
    var elapsed: Long,
    var date: ZonedDateTime
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}