package com.stevdzo.accesscontrolapp.domain.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.stevdzo.accesscontrolapp.common.logic.Converters
import com.stevdzo.accesscontrolapp.domain.model.Employee
import java.time.LocalDateTime

@Entity(tableName = "event",
    foreignKeys = [
        ForeignKey(
            entity = Employee::class,
            parentColumns = ["employeeId"],
            childColumns = ["employeeId"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class Event(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "eventId")
    val eventId: Long = 0L,

    @ColumnInfo(name = "employeeId", index = true)
    val employeeId: Long,

    @ColumnInfo(name = "date")
    val date: LocalDateTime = LocalDateTime.now()
)