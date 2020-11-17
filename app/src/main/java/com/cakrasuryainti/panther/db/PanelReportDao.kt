package com.cakrasuryainti.panther.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.cakrasuryainti.panther.db.model.PanelReport
import kotlinx.coroutines.flow.Flow

@Dao
interface PanelReportDao {
    @Insert
    suspend fun insertPanelReport(vararg reports: PanelReport)

    @Query("select * from PanelReport where not finished ")
    fun findLatestUnfinishedReport(): Flow<PanelReport?>

    @Query("select * from PanelReport where id = :id")
    fun findReportById(id: String): Flow<PanelReport>
}