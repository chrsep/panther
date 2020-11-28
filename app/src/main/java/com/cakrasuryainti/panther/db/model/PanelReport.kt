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
    val customer: String = "",
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
    val keteranganPhase: String = "",

    val arusR: Float = 0f,
    val arusS: Float = 0f,
    val arusT: Float = 0f,
    val keteranganArus: String = "",

    val frekuensi: Float = 0f,
    val keteranganFrekuensi: String = "",

    val powerFactor: Float = 0f,
    val keteranganPowerFactor: String = "",

    val kondisiPerangkat: String = "",
    val keteranganKondisiPerangkat: String = "",

    // visual checks
    val relay: Status = Status.NotAvailable,
    val keteranganRelay: String = "",
    val kabel: Status = Status.NotAvailable,
    val keteranganKabel: String = "",
    val MCBInput: Status = Status.NotAvailable,
    val keteranganMCBInput: String = "",
    val MCBOutput: Status = Status.NotAvailable,
    val keteranganMCBOutput: String = "",
    val lampuIndikatorPanel: Status = Status.NotAvailable,
    val keteranganLampuIndikatorPanel: String = "",
    val fuse: Status = Status.NotAvailable,
    val keteranganFuse: String = "",
    val terminalPower: Status = Status.NotAvailable,
    val keteranganTerminalPower: String = "",
    val ampereMeter: Status = Status.NotAvailable,
    val keteranganAmpereMeter: String = "",
    val voltMeter: Status = Status.NotAvailable,
    val keteranganVoltMeter: String = "",
    val modulControlStatus: Status = Status.NotAvailable,
    val keteranganModulControlStatus: String = "",
    val timer: Status = Status.NotAvailable,
    val keteranganTimer: String = "",
    val pushButtonOn: Status = Status.NotAvailable,
    val keteranganPushButtonOn: String = "",
    val pushButtonOff: Status = Status.NotAvailable,
    val keteranganPushButtonOff: String = "",
    val selectorMOA: Status = Status.NotAvailable,
    val keteranganSelectorMOA: String = "",
    val statusIndikator: Status = Status.NotAvailable,
    val keteranganStatusIndikator: String = "",

    // Kebersihan
    val luarPanel: Status = Status.NotAvailable,
    val keteranganLuarPanel: String = "",
    val dalamPanel: Status = Status.NotAvailable,
    val keteranganDalamPanel: String = "",
    val jalurKabelPower: Status = Status.NotAvailable,
    val keteranganJalurKabelPower: String = "",
    val kondisiRuangan: Status = Status.NotAvailable,
    val keteranganKondisiRuangan: String = "",
    val lingkungan: Status = Status.NotAvailable,
    val keteranganLingkungan: String = "",
    val penerangan: Status = Status.NotAvailable,
    val keteranganPenerangan: String = "",
    val fanRuangan: Status = Status.NotAvailable,
    val keteranganFanRuangan: String = "",

    val notesAndRecommendation: String = "",

    val finished: Boolean = false,
    val pdfFilePath: String = ""
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

public enum class JobDesc {
    Cleaning,
    Maintenance,
    Repair,
    Modification
}

public enum class Status {
    Ok,
    NotOk,
    NotAvailable,
}


