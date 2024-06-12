package com.vineet.tasks

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [Task::class], version = 1)
abstract class GetItDoneDatabase : RoomDatabase(){

    abstract fun GetTaskDao() : IGetItDoneDao

    companion object {
        @Volatile
        private var DATABASE_INSTANCE : GetItDoneDatabase? = null

        fun GetDatabase(context: Context) : GetItDoneDatabase {

            return DATABASE_INSTANCE ?: synchronized(this){
                Room.databaseBuilder(context,
                    GetItDoneDatabase::class.java,
                    "Get-It-Done-Database").build()
            }
        }
    }
}