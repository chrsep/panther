package com.cakrasuryainti.panther.domain

import com.cakrasuryainti.panther.db.model.JobDesc
import com.cakrasuryainti.panther.db.model.PanelReport
import com.cakrasuryainti.panther.db.model.ReportImage
import com.cakrasuryainti.panther.db.model.Status
import com.itextpdf.io.image.ImageDataFactory
import com.itextpdf.kernel.colors.DeviceRgb
import com.itextpdf.kernel.geom.PageSize
import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.layout.Document
import com.itextpdf.layout.element.*
import com.itextpdf.layout.property.TextAlignment
import com.itextpdf.layout.property.VerticalAlignment
import java.io.IOException
import java.io.OutputStream
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

val RED = DeviceRgb(255, 26, 26)
val GREEN = DeviceRgb(17, 255, 0)
val BLUE = DeviceRgb(57, 210, 255)

fun generatePanelReport(
    report: PanelReport,
    reportImages: List<ReportImage>,
    outputStream: OutputStream,
    logoPath: ByteArray
) {
    val writer = PdfWriter(outputStream)
    val pdf = PdfDocument(writer)
    val document = Document(pdf)
    pdf.addNewPage(PageSize.A4)

    try {
        // Title
        val title = Paragraph("FORM CHECKLIST \n PREVENTIVE MAINTENANCE \n LV PANEL").apply {
            setTextAlignment(TextAlignment.CENTER)
            setMarginBottom(32f)
        }
        document.add(title)
        val logo = ImageDataFactory.create(logoPath)
        document.add(Image(logo, 50f, 730f, 100f))

        val metaTable =
            createMetaTable(
                report.customer,
                report.location,
                report.panelName,
                report.dateTime,
                report.model,
                report.jobDesc,
                report.serialNumber
            )
        document.add(metaTable)


        val mainReportTable = createMainReportTable(report)
        document.add(mainReportTable)

        document.add(AreaBreak())

        val cleanlinessTable = createCleanlinessTable(report)
        cleanlinessTable.setMarginBottom(16f)
        document.add(cleanlinessTable)

        val notesTable = createNotesTable(report)
        notesTable.setMarginBottom(32f)
        document.add(notesTable)

        val signatureTable = createSignatureTable()
        document.add(signatureTable)

        if (reportImages.isNotEmpty()) {
            document.add(AreaBreak())

            val imageTable = createImageTable(reportImages)
            document.add(imageTable)
        }
    } catch (ioe: IOException) {
        System.err.println(ioe)
    } finally {
        document.close()
    }
}

private fun createImageTable(images: List<ReportImage>): Table {
    val table = Table(floatArrayOf(300f, 300f))
    images.forEach { image ->
        val imageData = ImageDataFactory.create(image.filePath)
        table.addCell(
            Cell().add(Image(imageData).setAutoScaleWidth(true)).add(Paragraph(image.description))
        )
    }
    return table
}

private fun createSignatureTable(): Table {
    val table = Table(floatArrayOf(150f, 150f, 150f, 150f))
    table.addCell(Cell(1, 2).add(smallParagraph("PT. CAKRA SURYA INTI").center()))
    table.addCell(Cell(1, 2))
    table.addCell(Cell(1, 2).setHeight(86f))
    table.addCell(Cell(1, 2).setHeight(86f))
    table.addCell(Cell(1, 2).add(smallParagraph("Nama:")))
    table.addCell(Cell(1, 2).add(smallParagraph("Nama:")))
    return table
}

private fun createNotesTable(report: PanelReport): Table {
    val table = Table(floatArrayOf(150f, 150f, 150f, 150f))
    table.addCell(
        Cell(1, 4)
            .add(smallParagraph("CATATAN DAN REKOMENDASI:").setMarginBottom(8f))
            .add(smallParagraph(report.notesAndRecommendation))
    ).setMinHeight(450f)
    return table
}

private fun createCleanlinessTable(report: PanelReport): Table {
    val table = Table(
        floatArrayOf(
            27.5f,
            27.5f,
            27.5f,
            27.5f,
            27.5f,
            27.5f,
            50f,
            50f,
            50f,
            45f,
            45f,
            45f,
            50f,
            50f,
            50f
        )
    )
    table.addCell(
        Cell(8, 1)
            .alignMiddle()
            .setBackgroundColor(BLUE)
            .add(
                smallParagraph("KEBERSIHAN").center().setRotationAngle(1.57f)
            )
    )
    addChecklistRow(table, 1, "Luar panel", report.luarPanel, report.keteranganLuarPanel)
    addChecklistRow(table, 2, "Dalam panel", report.dalamPanel, report.keteranganDalamPanel)
    addChecklistRow(
        table,
        3,
        "Jalur kabel power",
        report.jalurKabelPower,
        report.keteranganJalurKabelPower
    )
    addChecklistRow(
        table,
        4,
        "Kondisi ruangan",
        report.kondisiRuangan,
        report.keteranganKondisiRuangan
    )
    addChecklistRow(
        table,
        5,
        "Ruangan / Lingkungan",
        report.lingkungan,
        report.keteranganLingkungan
    )
    addChecklistRow(table, 6, "Penerangan", report.penerangan, report.keteranganPenerangan)
    addChecklistRow(table, 7, "Fan Ruangan", report.fanRuangan, report.keteranganFanRuangan)
    return table
}

private fun createMainReportTable(report: PanelReport): Table {
    val table = Table(
        floatArrayOf(
            27.5f,
            27.5f,
            27.5f,
            27.5f,
            27.5f,
            27.5f,
            50f,
            50f,
            50f,
            45f,
            45f,
            45f,
            50f,
            50f,
            50f
        )
    )
    // ====================== NEW LINE ===============================================
    table.addCell(
        Cell(1, 6)
            .add(Paragraph("PARAMETER").center())
            .setBackgroundColor(RED)
            .alignMiddle()
    )
    table.addCell(
        Cell(1, 3)
            .add(Paragraph("PEMBACAAN").center())
            .setBackgroundColor(RED)
            .alignMiddle()
    )
    table.addCell(
        Cell(1, 3)
            .add(Paragraph("PARAMETER NORMAL").center())
            .setBackgroundColor(RED)
            .alignMiddle()
    )
    table.addCell(
        Cell(1, 3)
            .add(Paragraph("KETERANGAN").center())
            .setBackgroundColor(RED)
            .alignMiddle()
    )

    // ====================== NEW LINE ===============================================
    table.addCell(
        Cell(1, 6)
            .setBackgroundColor(GREEN)
            .add(smallParagraph("TEGANGAN PHASE TO PHASE"))
    )

    table.addCell(
        Cell(1, 3).add(smallParagraph("(VAC)").center())
    )


    table.addCell(
        Cell(5, 3)
            .add(
                Paragraph("3 × 380 V / 220 V + N\n3 × 400 V / 230 V + N\n3 × 415 V / 240 V + N")
                    .setFontSize(10f)
                    .center()
            )
            .alignMiddle()
    )
    table.addCell(Cell(5, 3).add(smallParagraph(report.keteranganPhase)))

    // ====================== NEW LINE ===============================================
    table.addCell(Cell(1, 2).add(smallParagraph("R - S").center()))
    table.addCell(Cell(1, 2).add(smallParagraph("S - T").center()))
    table.addCell(Cell(1, 2).add(smallParagraph("T - R").center()))

    table.addCell(Cell().add(smallParagraph(report.teganganPhaseToPhaseRS.toString()).center()))
    table.addCell(Cell().add(smallParagraph(report.teganganPhaseToPhaseST.toString()).center()))
    table.addCell(Cell().add(smallParagraph(report.teganganPhaseToPhaseTR.toString()).center()))

    // ====================== NEW LINE ===============================================
    table.addCell(
        Cell(1, 6)
            .setBackgroundColor(GREEN)
            .add(smallParagraph("TEGANGAN PHASE TO NEUTRAL"))
    )
    table.addCell(
        Cell(1, 3).add(
            smallParagraph("(VOLT)").center()
        )
    )

    // ====================== NEW LINE ===============================================
    table.addCell(Cell(1, 2).add(smallParagraph("R - N").center()))
    table.addCell(Cell(1, 2).add(smallParagraph("S - N").center()))
    table.addCell(Cell(1, 2).add(smallParagraph("T - N").center()))

    table.addCell(Cell().add(smallParagraph(report.teganganPhaseToNeutralRN.toString()).center()))
    table.addCell(Cell().add(smallParagraph(report.teganganPhaseToNeutralSN.toString()).center()))
    table.addCell(Cell().add(smallParagraph(report.teganganPhaseToNeutralTN.toString()).center()))

    // ====================== NEW LINE ===============================================
    table.addCell(Cell(1, 2))
    table.addCell(Cell(1, 2).add(smallParagraph("G - N").center()))
    table.addCell(Cell(1, 2))

    table.addCell("")
    table.addCell(Cell().add(smallParagraph(report.teganganPhaseToNeutralGN.toString()).center()))
    table.addCell("")

    // ====================== NEW LINE ===============================================
    table.addCell(
        Cell(1, 6)
            .setBackgroundColor(GREEN)
            .add(smallParagraph("ARUS / CURRENT"))
    )

    table.addCell(Cell(1, 3).add(smallParagraph("(AMPERE)").center()))
    table.addCell(Cell(1, 3))
    table.addCell(Cell(1, 3))

    // ====================== NEW LINE ===============================================
    table.addCell(Cell(1, 2).add(smallParagraph("R").center()))
    table.addCell(Cell(1, 2).add(smallParagraph("S").center()))
    table.addCell(Cell(1, 2).add(smallParagraph("T").center()))

    table.addCell(Cell().add(smallParagraph(report.arusR.toString()).center()))
    table.addCell(Cell().add(smallParagraph(report.arusS.toString()).center()))
    table.addCell(Cell().add(smallParagraph(report.arusT.toString()).center()))

    table.addCell(Cell(1, 3))
    table.addCell(Cell(1, 3).add(smallParagraph(report.keteranganArus)))

    // ====================== NEW LINE ===============================================
    table.addCell(
        Cell(1, 6)
            .setBackgroundColor(GREEN)
            .add(smallParagraph("FREKUENSI (HZ)"))
    )
    table.addCell(Cell(1, 3).add(smallParagraph(report.frekuensi.toString()).center()))
    table.addCell(Cell(1, 3).add(smallParagraph("50.00HZ").center()))
    table.addCell(Cell(1, 3).add(smallParagraph(report.keteranganFrekuensi)))

    // ====================== NEW LINE ===============================================
    table.addCell(
        Cell(1, 6)
            .setBackgroundColor(GREEN)
            .add(smallParagraph("POWER FACTOR (PF)"))
    )
    table.addCell(Cell(1, 3).add(smallParagraph(report.powerFactor.toString()).center()))
    table.addCell(Cell(1, 3).add(smallParagraph("0,8-1,0").center()))
    table.addCell(Cell(1, 3).add(smallParagraph(report.keteranganPowerFactor)))

    // ====================== NEW LINE ===============================================
    table.addCell(
        Cell(1, 6)
            .setBackgroundColor(GREEN)
            .add(smallParagraph("KONDISI PERANGKAT"))
    )
    table.addCell(
        Cell(1, 3)
            .add(smallParagraph(report.kondisiPerangkat))
    )
    table.addCell(Cell(1, 3))
    table.addCell(Cell(1, 3).add(smallParagraph(report.keteranganKondisiPerangkat)))

    // ====================== VISUAL CHECK SECTION ================================
    table.addCell(
        Cell(15, 1)
            .alignMiddle()
            .setBackgroundColor(BLUE)
            .add(
                smallParagraph("PENGECEKAN VISUAL").center().setRotationAngle(1.57f)
            )
    )
    addChecklistRow(table, 1, "Relay/timer control", report.relay, report.keteranganRelay)
    addChecklistRow(table, 2, "Kabel Instalasi", report.kabel, report.keteranganRelay)
    addChecklistRow(table, 3, "ACB/MCCB/MCB (input)", report.MCBInput, report.keteranganMCBInput)
    addChecklistRow(table, 4, "ACB/MCCB/MCB (output)", report.MCBOutput, report.keteranganMCBOutput)
    addChecklistRow(
        table,
        5,
        "Lampu indikator panel",
        report.lampuIndikatorPanel,
        report.keteranganLampuIndikatorPanel
    )
    addChecklistRow(table, 6, "Fuse", report.fuse, report.keteranganFuse)
    addChecklistRow(
        table,
        7,
        "Terminal power",
        report.terminalPower,
        report.keteranganTerminalPower
    )
    addChecklistRow(table, 8, "Amper meter", report.ampereMeter, report.keteranganAmpereMeter)
    addChecklistRow(table, 9, "Volt meter", report.voltMeter, report.keteranganVoltMeter)
    addChecklistRow(
        table,
        10,
        "Modul control status",
        report.modulControlStatus,
        report.keteranganModulControlStatus
    )
    addChecklistRow(table, 11, "Timer (hour counter)", report.timer, report.keteranganTimer)
    addChecklistRow(table, 12, "Push button ON", report.pushButtonOn, report.keteranganPushButtonOn)
    addChecklistRow(
        table,
        13,
        "Push button OFF",
        report.pushButtonOff,
        report.keteranganPushButtonOff
    )
    addChecklistRow(table, 14, "Selector MOA", report.selectorMOA, report.keteranganSelectorMOA)
    addChecklistRow(
        table,
        15,
        "Status Indikator",
        report.statusIndikator,
        report.keteranganStatusIndikator
    )
    return table
}

private fun addChecklistRow(
    table: Table,
    rowNumber: Int,
    name: String,
    status: Status,
    keterangan: String
) {
    table.addCell(
        Cell()
            .add(smallParagraph(rowNumber.toString()).center())
            .alignMiddle()
    )
    table.addCell(Cell(1, 4).add(smallParagraph(name)))
    table.addCell(
        Cell(1, 3).add(
            smallParagraph(
                when (status) {
                    Status.Ok -> "OK"
                    Status.NotOk -> "NOK"
                    Status.NotAvailable -> "N/A"

                }
            ).center()
        ).alignMiddle()
    )
    table.addCell(Cell(1, 3))
    table.addCell(Cell(1, 3).add(smallParagraph(keterangan)))
}


private fun createMetaTable(
    pekerjaan: String,
    location: String,
    panelName: String,
    date: Instant,
    model: String,
    jobDesc: JobDesc,
    serialNumber: String
): Table {
    val table = Table(floatArrayOf(110f, 150f, 150f, 150f))
    table.setMarginBottom(16f)

    // Customer
    table.addCell(
        Cell()
            .add(Paragraph("CUSTOMER"))
            .setBackgroundColor(RED)
    )
    table.addCell(pekerjaan)
    table.addCell(
        Cell()
            .add(Paragraph("LOCATION"))
            .setBackgroundColor(RED)
    )
    table.addCell(location)

    // Panel
    table.addCell(
        Cell()
            .add(Paragraph("PANEL NAME"))
            .setBackgroundColor(RED)
    )
    table.addCell(panelName)

    // Date
    val formattedDate =
        date.atZone(ZoneId.systemDefault())
            .format(DateTimeFormatter.ofPattern("dd MMM yyyy, HH:MM"))
    table.addCell(
        Cell()
            .add(Paragraph("DATE"))
            .setBackgroundColor(RED)
    )
    table.addCell(formattedDate)

    // Model
    table.addCell(
        Cell()
            .add(Paragraph("MODEL / TYPE"))
            .setBackgroundColor(RED)
    )
    table.addCell(model)

    // Job Desc
    table.addCell(
        Cell(2, 1)
            .add(Paragraph("JOB DESCRIPTION"))
            .setBackgroundColor(RED)
    )
    table.addCell(
        Cell(2, 1)
            .add(Paragraph(jobDesc.name))
    )

    // Serial Number
    table.addCell(
        Cell()
            .add(Paragraph("SERIAL NUMBER"))
            .setBackgroundColor(RED)
    )
    table.addCell(serialNumber)
    return table
}

private fun smallParagraph(text: String): Paragraph {
    return Paragraph(text).setFontSize(9f)
}

private fun Paragraph.center(): Paragraph {
    return setTextAlignment(TextAlignment.CENTER)
}

private fun Cell.alignMiddle(): Cell {
    return setVerticalAlignment(VerticalAlignment.MIDDLE)
}
