package com.cakrasuryainti.panther

import android.os.Build
import androidx.annotation.RequiresApi
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
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

fun generatePanelReport(report: PanelReport, outputPath: String) {
    val writer = PdfWriter(FileOutputStream(outputPath))
    val pdf = PdfDocument(writer)
    val document = Document(pdf)

    try {
        // Title
        val title = Paragraph("FORM CHECKLIST \n PREVENTIVE MAINTENANCE \n LV PANEL").apply {
            setTextAlignment(TextAlignment.CENTER)
            setPadding(10f)
        }
        pdf.addNewPage(PageSize.A4)
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

    } catch (ioe: IOException) {
        System.err.println(ioe)
    } finally {
        document.close()
    }
}

fun createMetaTable(
    location: String,
    panelName: String,
    date: Instant,
    model: String,
    jobDesc: JobDesc,
    serialNumber: String
): Table {
    val table = Table(listOf(150f, 150f, 150f, 150f).toFloatArray())
    table.addCell(Cell().apply {
        add(Paragraph("Customer Name"))
        setVerticalAlignment(VerticalAlignment.TOP)
        setBackgroundColor(DeviceRgb(255, 102, 102))
    })
    table.addCell("")
    table.addCell(Cell().apply {
        add(Paragraph("Location"))
        setVerticalAlignment(VerticalAlignment.TOP)
        setBackgroundColor(DeviceRgb(255, 102, 102))
    })
    table.addCell(location)

    table.addCell(Cell().apply {
        add(Paragraph("Panel Name"))
        setVerticalAlignment(VerticalAlignment.TOP)
        setBackgroundColor(DeviceRgb(255, 102, 102))
    })
    table.addCell(panelName)

    val formattedDate =
        date.atZone(ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("dd MMM yyyy, HH:MM"))
    table.addCell(Cell().apply {
        add(Paragraph("Date"))
        setVerticalAlignment(VerticalAlignment.TOP)
        setBackgroundColor(DeviceRgb(255, 102, 102))
    })
    table.addCell(formattedDate)

    table.addCell(Cell().apply {
        add(Paragraph("Model/Type"))
        setVerticalAlignment(VerticalAlignment.TOP)
        setBackgroundColor(DeviceRgb(255, 102, 102))
    })
    table.addCell(model)

    table.addCell(Cell().apply {
        add(Paragraph("Job Description"))
        setVerticalAlignment(VerticalAlignment.TOP)
        setBackgroundColor(DeviceRgb(255, 102, 102))
    })
    table.addCell(jobDesc.name)

    table.addCell(Cell().apply {
        add(Paragraph("Serial Number"))
        setVerticalAlignment(VerticalAlignment.TOP)
        setBackgroundColor(DeviceRgb(255, 102, 102))
    })
    table.addCell(serialNumber)
    return table
}