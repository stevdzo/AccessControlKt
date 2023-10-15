package com.stevdzo.accesscontrolapp.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "employee")
data class Employee(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="employeeId")
    val employeeId: Long = 0L,

    @ColumnInfo(name="name")
    val name: String,

    @ColumnInfo(name="surname")
    val surname: String,

    @ColumnInfo(name="email")
    val email: String,

    @ColumnInfo(name="phone")
    val phone: String,

    @ColumnInfo(name="code")
    val code: String = "000"
)