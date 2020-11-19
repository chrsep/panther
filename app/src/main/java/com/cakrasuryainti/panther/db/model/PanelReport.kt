package com.cakrasuryainti.panther.db.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import java.time.Instant

@Entity
data class PanelReport(
    @PrimaryKey
    val id: String,
    val pekerjaan: String = "",
    val panelName: String = "",
    val model: String = "",
    val serialNumber: String = "",
    val location: String = "",
    val jobDesc: JobDesc = JobDesc.Maintenance,
    val dateTime: Instant = Instant.now(),

    val teganganPhaseToPhaseRS: Float = 0f,
    val teganganPhaseToPhaseST: Float = 0f,
    val teganganPhaseToPhaseTR: Float = 0f,

    val teganganPhaseToNeutralRN: Float = 0f,
    val teganganPhaseToNeutralSN: Float = 0f,
    val teganganPhaseToNeutralTN: Float = 0f,
    val teganganPhaseToNeutralGN: Float = 0f,

    val arusR: Float = 0f,
    val arusS: Float = 0f,
    val arusT: Float = 0f,

    val frekuensi: Float = 0f,
    val powerFactor: Float = 0f,

    // visual checks
    val relay: Status = Status.NotAvailable,
    val kabel: Status = Status.NotAvailable,
    val MCBInput: Status = Status.NotAvailable,
    val MCBOutput: Status = Status.NotAvailable,
    val lampuIndikatorPanel: Status = Status.NotAvailable,
    val fuse: Status = Status.NotAvailable,
    val terminalPower: Status = Status.NotAvailable,
    val ampereMeter: Status = Status.NotAvailable,
    val voltMeter: Status = Status.NotAvailable,
    val modulControlStatus: Status = Status.NotAvailable,
    val timer: Status = Status.NotAvailable,
    val pushButtonOn: Status = Status.NotAvailable,
    val pushButtonOff: Status = Status.NotAvailable,
    val selectorMOA: Status = Status.NotAvailable,
    val statusIndikator: Status = Status.NotAvailable,

    // Kebersihan
    val luarPanel: Status = Status.NotAvailable,
    val dalamPanel: Status = Status.NotAvailable,
    val jalurKabelPower: Status = Status.NotAvailable,
    val kondisiRuangan: Status = Status.NotAvailable,
    val lingkungan: Status = Status.NotAvailable,
    val penerangan: Status = Status.NotAvailable,
    val fanRuangan: Status = Status.NotAvailable,

    val notesAndRecommendation: String = "",

    val finished: Boolean = false
)

data class PanelReportWithImages(
    @Embedded val report: PanelReport,
    @Relation(
        parentColumn = "id",
        entityColumn = "reportId"
    )
    val images: List<ReportImage>
)

@Entity
data class ReportImage(
    @PrimaryKey
    val id: String,
    val filePath: String,
    val reportId: String,
    val description: String = "",
    val createdAt: Instant = Instant.now()
)

enum class JobDesc {
    Cleaning,
    Maintenance,
    Repair,
    Modification
}

enum class Status {
    Ok,
    NotOk,
    NotAvailable,
}


