package com.stevdzo.accesscontrolapp.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "administrator")
data class Administrator(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="administratorId")
    val administratorId: Long = 0L,

    @ColumnInfo(name="username")
    val username: String,

    @ColumnInfo(name="password")
    val password: String
)