package com.cakrasuryainti.panther.repository

import com.cakrasuryainti.panther.db.PanelReportDao
import com.cakrasuryainti.panther.db.model.PanelReport
import com.cakrasuryainti.panther.db.model.PanelReportWithImages
import com.cakrasuryainti.panther.db.model.ReportImage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import java.util.*
import javax.inject.Inject

class PanelReportRepository @Inject constructor(private val dao: PanelReportDao) {
    fun getCurrentReport(): Flow<PanelReportWithImages?> {
        val latestUnfinishedReport = dao.findLatestUnfinishedReportWithImages()
        return latestUnfinishedReport.filter {
            if (it == null) {
                createNewEmptyReport()
                return@filter false
            }
            return@filter true
        }
    }

    private suspend fun createNewEmptyReport() {
        val emptyReport = PanelReport(UUID.randomUUID().toString())
        dao.insertPanelReports(emptyReport)
    }

    suspend fun updateReport(report: PanelReport): Int {
        return dao.updateReports(report)
    }

    suspend fun insertNewReport(report: PanelReport) {
        return dao.insertPanelReports(report)
    }

    suspend fun saveImages(images: List<ReportImage>) {
        dao.saveImages(images)
    }

    suspend fun updateImage(image: ReportImage) {
        dao.updateImage(image)
    }
}