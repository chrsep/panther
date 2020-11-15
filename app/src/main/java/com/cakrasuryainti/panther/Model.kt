package com.cakrasuryainti.panther

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant

@Entity
data class PanelReport(
    @PrimaryKey
    val id: String,
    val work: String,
    val panelName: String,
    val model: String,
    val serialNumber: String,
    val location: String,
    val jobDesc: JobDesc,
    val dateTime: Instant,

    // visual checks
    val Relay: Status,
    val Kabel: Status,
    val MCBInput: Status,
    val MCBOutput: Status,
    val LampuIndikatorPanel: Status,
    val Fuse: Status,
    val TerminalPower: Status,
    val AmpereMeter: Status,
    val VoltMeter: Status,
    val ModulControlStatus: Status,
    val Timer: Status,
    val PushButtonOn: Status,
    val PushButtonOff: Status,
    val SelectorMOA: Status,
    val StatusIndikator: Status,

    // Kebersihan
    val LuarPanel: Status,
    val DalamPanel: Status,
    val JalurKabelPower: Status,
    val KondisiRuangan: Status,
    val Lingkungan: Status,
    val Penerangan: Status,
    val FanRuangan: Status,

    val Notes: String
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

@Entity
data class ReportImage(
    @PrimaryKey
    val id: String,
    val file: String,
    val note: String
)