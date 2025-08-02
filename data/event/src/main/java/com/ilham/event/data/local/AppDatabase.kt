package com.ilham.event.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ilham.event.data.local.dao.UserDao
import com.ilham.event.data.local.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao

}