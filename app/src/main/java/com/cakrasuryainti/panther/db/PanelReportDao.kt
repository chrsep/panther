package com.cakrasuryainti.panther.db

import androidx.room.*
import com.cakrasuryainti.panther.db.model.PanelReport
import com.cakrasuryainti.panther.db.model.PanelReportWithImages
import com.cakrasuryainti.panther.db.model.ReportImage
import kotlinx.coroutines.flow.Flow

@Dao
interface PanelReportDao {
    @Insert
    suspend fun insertPanelReports(vararg reports: PanelReport)

    @Query("select * from PanelReport where not finished")
    fun findLatestUnfinishedReportWithImages(): Flow<PanelReportWithImages?>

    @Query("select * from PanelReport where id = :id")
    fun findReportById(id: String): Flow<PanelReport>

    @Update
    suspend fun updateReports(vararg reports: PanelReport): Int

    @Insert
    suspend fun saveImages(images: List<ReportImage>)

    @Update
    suspend fun updateImage(image: ReportImage)

    @Delete
    suspend fun deleteImage(image: ReportImage)

    @Query("select * from PanelReport where finished order by dateTime ")
    fun findAllPanelReport(): Flow<List<PanelReport>>
}