package com.cakrasuryainti.panther

import java.time.ZonedDateTime

data class PanelReport(
    val id: String,
    val work: String,
    val panelName: String,
    val type: String,
    val serialNumber: String,
    val location: String,
    val jobDesc: JobDesc,
    val time: ZonedDateTime,

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

data class ReportImage(
    val id: String,
    val file: String,
    val note: String
)