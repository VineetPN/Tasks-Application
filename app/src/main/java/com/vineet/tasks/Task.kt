package com.vineet.tasks

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "task_ID") val taskID : Int = 0 ,
    val title: String,
    val description: String? = null,
    @ColumnInfo(name = "is_starred") val  isStarred: Boolean = false
) {

}
