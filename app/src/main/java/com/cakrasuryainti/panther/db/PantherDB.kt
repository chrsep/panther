package com.cakrasuryainti.panther.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.cakrasuryainti.panther.db.model.JobDesc
import com.cakrasuryainti.panther.db.model.PanelReport
import com.cakrasuryainti.panther.db.model.ReportImage
import com.cakrasuryainti.panther.db.model.Status
import java.time.Instant

@Database(entities = [PanelReport::class, ReportImage::class], version = 4)
@TypeConverters(JobDescConverter::class)
abstract class PantherDB : RoomDatabase() {
    abstract fun panelReportDao(): PanelReportDao
}

class JobDescConverter {
    @TypeConverter
    fun fromJobDesc(value: String?): JobDesc? {
        return value?.let { JobDesc.valueOf(it) }
    }

    @TypeConverter
    fun toJobDesc(jobDesc: JobDesc?): String? {
        return jobDesc?.name
    }

    @TypeConverter
    fun fromStatus(value: String?): Status? {
        return value?.let { Status.valueOf(it) }
    }

    @TypeConverter
    fun toStatus(status: Status?): String? {
        return status?.name
    }

    @TypeConverter
    fun fromInstant(value: String?): Instant {
        return Instant.parse(value)
    }

    @TypeConverter
    fun toInstant(dateTime: Instant?): String? {
        return dateTime?.toString()
    }
}