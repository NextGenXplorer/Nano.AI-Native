package com.nano.ai.datahub.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nano.ai.datahub.model.DataSetModel

@Database(entities = [DataSetModel::class], version = 1, exportSchema = false)
abstract class DataHubDatabase : RoomDatabase() {
    abstract fun dataHubDAO(): DataHubDAO
}
