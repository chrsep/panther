package com.cakrasuryainti.panther.domain

import com.cakrasuryainti.panther.db.model.GeneratorReport
import com.itextpdf.kernel.geom.PageSize
import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.layout.Document
import com.itextpdf.layout.element.Cell
import com.itextpdf.layout.element.Paragraph
import com.itextpdf.layout.element.Table
import com.itextpdf.layout.property.TextAlignment
import com.itextpdf.layout.property.VerticalAlignment
import java.io.IOException
import java.io.OutputStream

fun generateGeneratorReport(
    report: GeneratorReport,
    outputStream: OutputStream
) {
    val writer = PdfWriter(outputStream)
    val pdf = PdfDocument(writer)
    val document = Document(pdf)
    pdf.addNewPage(PageSize.A4)

    try {
        // Title
        val title = Paragraph("GENERATOR MONTHLY\n INSPECTION").apply {
            setTextAlignment(TextAlignment.CENTER)
            setMarginBottom(32f)
        }
        document.add(title)

        val metaTable = createMetaTable(report.generatorId, report.period)
        document.add(metaTable)

        val mainTable = createMainReportTable(report)
        document.add(mainTable)


    } catch (ioe: IOException) {
        System.err.println(ioe)
    } finally {
        document.close()
    }
}

private fun createMainReportTable(report: GeneratorReport): Table {
    val table = Table(
        floatArrayOf(200f, 20f, 250f, 100f)
    )
    table.addCell(
        Cell()
            .add(smallParagraph("Checklist").center())
            .setBackgroundColor(BLUE)
            .alignMiddle()
    )
    table.addCell(
        Cell()
            .add(smallParagraph("V").center())
            .setBackgroundColor(BLUE)
            .alignMiddle()
    )
    table.addCell(
        Cell()
            .add(smallParagraph("Description").center())
            .setBackgroundColor(BLUE)
            .alignMiddle()
    )
    table.addCell(
        Cell(1, 6)
            .add(smallParagraph("Note").center())
            .setBackgroundColor(BLUE)
            .alignMiddle()
    )

    addChecklistRow(table, "Running Hours", true, "")
    addChecklistRow(table, "Generator is clean and in good condition?", true, "")
    addChecklistRow(table, "Shed is clean and in good condition?", true, "")
    addChecklistRow(table, "Fuel tank at least 50% full?", true, "")
    addChecklistRow(table, "Engine oil level is okay?", true, "")
    addChecklistRow(table, "Engine oil condition?", true, "")
    addChecklistRow(table, "Filter oil check condition", true, "")
    addChecklistRow(table, "Filter air check condition", true, "")
    addChecklistRow(table, "Radiator, no leaks?", true, "")
    addChecklistRow(table, "Radiator, coolant level okay?", true, "")
    addChecklistRow(table, "Battery connections good?", true, "")
    addChecklistRow(table, "Battery water level ok?", true, "")
    addChecklistRow(table, "Battery charger is charging?", true, "")
    addChecklistRow(table, "Exhaust system is functioning normally?", true, "")
    addChecklistRow(table, "Manual-start is working?", true, "")
    addChecklistRow(table, "Auto-start is working?", true, "")

    // Equipments
    sectionHeading(table, "Equipments")
    addChecklistRow(table, "Whrenches", true, "")
    addChecklistRow(table, "Fire extinguisher present", true, "")
    addChecklistRow(table, "Fire extinguisher working", true, "")
    // TODO: we may not need this
    addChecklistRow(table, "Other equipments", true, "")
    // other equipments
    // other equipments
    // other equipments
    addChecklistRow(table, "First aid kit present?", true, "")
    addChecklistRow(table, "First aid kit complete?", true, "")
    addChecklistRow(table, "Water decanter, does it need to be drained?", true, "")

    // Documents
    sectionHeading(table, "Documents")
    addChecklistRow(table, "Generator log present?", true, "")
    addChecklistRow(table, "Daily check forms present?", true, "")
    addChecklistRow(table, "Manuals presents?", true, "")

    // Start engine
    sectionHeading(table, "Start engine")
    addChecklistRow(table, "Pre heating works?", true, "")
    addChecklistRow(table, "Motor starts easily?", true, "")
    addChecklistRow(table, "Oil pressure ok?", true, "")
    addChecklistRow(table, "Battery charging?", true, "")

    sectionHeading(table, "Breaker OFF condition (manual test)")
    addChecklistRow(table, "Measurement metering (Volt)", true, "")
    addChecklistRow(table, "Measurement metering (Ampere)", true, "")
    addChecklistRow(table, "Measurement metering (Hz)", true, "")

    sectionHeading(table, "Breaker ON condition (manual test)")
    addChecklistRow(table, "Measurement metering (Volt)", true, "")
    addChecklistRow(table, "Measurement metering (Ampere)", true, "")
    addChecklistRow(table, "Measurement metering (Hz)", true, "")

    sectionHeading(table, "Auto start test (Simulation)")
    addChecklistRow(table, "Measurement metering (Volt)", true, "")
    addChecklistRow(table, "Measurement metering (Ampere)", true, "")
    addChecklistRow(table, "Measurement metering (Hz)", true, "")

    return table
}

private fun sectionHeading(
    table: Table,
    title: String,
) {
    table.addCell(
        Cell().add(smallParagraph(title)).setBackgroundColor(GREEN)
    )
    table.addCell(Cell())
    table.addCell(Cell())
    table.addCell(Cell())
}

private fun addChecklistRow(
    table: Table,
    question: String,
    isTrue: Boolean,
    description: String
) {
    table.addCell(smallParagraph(question))
    table.addCell(
        Cell()
            .add(smallParagraph(if (isTrue) "V" else "").center())
            .alignMiddle()
    )
    table.addCell(smallParagraph(description))
    table.addCell(Cell())
}

private fun createMetaTable(generatorId: String, period: String): Table {
    val table = Table(floatArrayOf(110f, 150f, 150f, 150f))
    table.setMarginBottom(16f)

    // Generator ID
    table.addCell(
        Cell()
            .add(smallParagraph("Generator ID No:"))
            .setBackgroundColor(BLUE)
    )
    table.addCell(smallParagraph(generatorId))

    // Period
    table.addCell(
        Cell()
            .add(smallParagraph("Period"))
            .setBackgroundColor(BLUE)
    )
    table.addCell(smallParagraph(period))

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
