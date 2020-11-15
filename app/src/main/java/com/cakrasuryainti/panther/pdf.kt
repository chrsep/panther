package com.cakrasuryainti.panther

import com.itextpdf.kernel.colors.DeviceRgb
import com.itextpdf.kernel.geom.PageSize
import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.layout.Document
import com.itextpdf.layout.element.Cell
import com.itextpdf.layout.element.Paragraph
import com.itextpdf.layout.element.Table
import com.itextpdf.layout.property.TextAlignment
import com.itextpdf.layout.property.VerticalAlignment
import java.io.FileOutputStream
import java.io.IOException
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

val RED = DeviceRgb(255, 26, 26)
val GREEN = DeviceRgb(17, 255, 0)

fun generatePanelReport(report: PanelReport, outputPath: String) {
    val writer = PdfWriter(FileOutputStream(outputPath))
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


        val metaTable =
            createMetaTable(
                report.location,
                report.panelName,
                report.dateTime,
                report.model,
                report.jobDesc,
                report.serialNumber
            )
        document.add(metaTable)

        val table = Table(floatArrayOf(55f, 55f, 55f, 50f, 50f, 50f, 45f, 45f, 45f, 50f, 50f, 50f))
        // ====================== NEW LINE ===============================================
        table.addCell(
            Cell(1, 3)
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
            Cell(1, 3)
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
        table.addCell(Cell(5, 3))

        // ====================== NEW LINE ===============================================
        table.addCell(Cell().add(smallParagraph("R - S").center()))
        table.addCell(Cell().add(smallParagraph("S - T").center()))
        table.addCell(Cell().add(smallParagraph("T - R").center()))

        table.addCell("")
        table.addCell("")
        table.addCell("")

        // ====================== NEW LINE ===============================================
        table.addCell(
            Cell(1, 3)
                .setBackgroundColor(GREEN)
                .add(smallParagraph("TEGANGAN PHASE TO NEUTRAL"))
        )
        table.addCell(
            Cell(1, 3).add(
                smallParagraph("(VOLT)").center()
            )
        )

        // ====================== NEW LINE ===============================================
        table.addCell(Cell().add(smallParagraph("R - N").center()))
        table.addCell(Cell().add(smallParagraph("S - N").center()))
        table.addCell(Cell().add(smallParagraph("T - N").center()))

        table.addCell("")
        table.addCell("")
        table.addCell("")

        // ====================== NEW LINE ===============================================
        table.addCell("")
        table.addCell(Cell().add(smallParagraph("G - N").center()))
        table.addCell("")

        table.addCell("")
        table.addCell("")
        table.addCell("")

        // ====================== NEW LINE ===============================================
        table.addCell(
            Cell(1, 3)
                .setBackgroundColor(GREEN)
                .add(smallParagraph("ARUS / CURRENT"))
        )

        table.addCell(Cell(1, 3).add(smallParagraph("(AMPERE)").center()))
        table.addCell(Cell(1, 3))
        table.addCell(Cell(1, 3))

        // ====================== NEW LINE ===============================================
        table.addCell(Cell().add(smallParagraph("R").center()))
        table.addCell(Cell().add(smallParagraph("S").center()))
        table.addCell(Cell().add(smallParagraph("T").center()))

        table.addCell("")
        table.addCell("")
        table.addCell("")

        table.addCell(Cell(1, 3))
        table.addCell(Cell(1, 3))

        // ====================== NEW LINE ===============================================
        table.addCell(
            Cell(1, 3)
                .setBackgroundColor(GREEN)
                .add(smallParagraph("FREKUENSI (HZ)"))
        )
        table.addCell(Cell(1, 3))
        table.addCell(Cell(1, 3).add(smallParagraph("50.00HZ").center()))
        table.addCell(Cell(1, 3))

        // ====================== NEW LINE ===============================================
        table.addCell(
            Cell(1, 3)
                .setBackgroundColor(GREEN)
                .add(smallParagraph("POWER FACTOR (PF)"))
        )
        table.addCell(Cell(1, 3))
        table.addCell(Cell(1, 3).add(smallParagraph("0,8-1,0").center()))
        table.addCell(Cell(1, 3))

        // ====================== NEW LINE ===============================================
        table.addCell(
            Cell(1, 3)
                .setBackgroundColor(GREEN)
                .add(smallParagraph("KONDISI PERANGKAT"))
        )
        table.addCell(Cell(1, 3))
        table.addCell(Cell(1, 3))
        table.addCell(Cell(1, 3))

        document.add(table)

    } catch (ioe: IOException) {
        System.err.println(ioe)
    } finally {
        document.close()
    }
}

fun smallParagraph(text: String): Paragraph {
    return Paragraph(text).setFontSize(8f)
}

fun Paragraph.center(): Paragraph {
    return setTextAlignment(TextAlignment.CENTER)
}

fun Cell.alignMiddle(): Cell {
    return setVerticalAlignment(VerticalAlignment.MIDDLE)
}

fun createMetaTable(
    location: String,
    panelName: String,
    date: Instant,
    model: String,
    jobDesc: JobDesc,
    serialNumber: String
): Table {
    val table = Table(floatArrayOf(150f, 150f, 150f, 150f))
    table.setMarginBottom(16f)

    // Customer
    table.addCell(
        Cell()
            .add(Paragraph("CUSTOMER"))
            .setBackgroundColor(RED)
    )
    table.addCell("")
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