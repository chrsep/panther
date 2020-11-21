package com.cakrasuryainti.panther.db.model

import androidx.room.Embedded
import androidx.room.PrimaryKey
import androidx.room.Relation
import java.time.Instant

typealias GeneratorChecks = String

data class GeneratorReport(
    val id: String,
    val customer: String,
    val generatorId: String,
    // TODO: Verify what this is.
    val period: String,
    val runningHours: GeneratorChecks,
    val generatorClean: GeneratorChecks,
    val notesAndRecommendation: String
)

data class GeneratorChecksWithImages(
    @Embedded val report: PanelReport,
    @Relation(
        parentColumn = "id",
        entityColumn = "reportId"
    )
    val images: List<ReportImage>
)

data class GeneratorReportImages(
    @PrimaryKey
    val id: String,
    val filePath: String,
    val description: String = "",
    val reportId: String,
    val createdAt: Instant = Instant.now()
)