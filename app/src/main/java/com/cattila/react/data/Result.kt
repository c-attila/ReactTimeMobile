package com.cattila.react.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey
import org.threeten.bp.LocalDateTime

@Entity(tableName = "result")
data class Result(
    var playerName: String?,
    var elapsed: Long,
    var date: LocalDateTime
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
