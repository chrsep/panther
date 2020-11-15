package com.cakrasuryainti.panther

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import java.time.Instant

@Entity
data class PanelReport(
    @PrimaryKey
    val id: String,
    val pekerjaan: String,
    val panelName: String,
    val model: String,
    val serialNumber: String,
    val location: String,
    val jobDesc: JobDesc,
    val dateTime: Instant,

    // visual checks
    val relay: Status,
    val kabel: Status,
    val MCBInput: Status,
    val MCBOutput: Status,
    val lampuIndikatorPanel: Status,
    val fuse: Status,
    val terminalPower: Status,
    val ampereMeter: Status,
    val voltMeter: Status,
    val modulControlStatus: Status,
    val timer: Status,
    val pushButtonOn: Status,
    val pushButtonOff: Status,
    val selectorMOA: Status,
    val statusIndikator: Status,

    // Kebersihan
    val luarPanel: Status,
    val dalamPanel: Status,
    val jalurKabelPower: Status,
    val kondisiRuangan: Status,
    val lingkungan: Status,
    val penerangan: Status,
    val fanRuangan: Status,

    val notesAndRecommendation: String,
)

enum class JobDesc {
    Maintenance,
    Survey,
    Repair,
}

enum class Status {
    Ok,
    NotOk,
    NotAvailable,
}

data class PanelReportWithImages(
    @Embedded val report: PanelReport,
    @Relation(
          parentColumn = "id",
          entityColumn = "reportId"
    )
    val playlists: List<ReportImage>
)

@Entity
data class ReportImage(
    @PrimaryKey
    val id: String,
    val file: String,
    val note: String,
    val reportId: String
)