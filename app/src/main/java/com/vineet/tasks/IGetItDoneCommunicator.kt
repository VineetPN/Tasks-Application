package com.vineet.tasks

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface IGetItDoneDao {
    @Insert
    fun CreateTaskDatabase(task : Task)

    @Query("SELECT * FROM task")
    fun GetAllDetails() : List<Task>

    @Update
    fun UpdateTask(task: Task)

    @Delete
    fun DeleteTask(task: Task)

}