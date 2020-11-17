package com.cakrasuryainti.panther.db

import androidx.room.Dao
import androidx.room.Insert
import com.cakrasuryainti.panther.db.model.PanelReport

@Dao
interface PanelReportDao {
    @Insert
    fun insertPanelReport(vararg reports: PanelReport)
}