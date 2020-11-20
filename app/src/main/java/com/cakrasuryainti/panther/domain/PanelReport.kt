package com.cakrasuryainti.panther.domain

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.cakrasuryainti.panther.db.model.PanelReport
import com.cakrasuryainti.panther.db.model.ReportImage
import java.io.File
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

fun generatePdfFileName(report: PanelReport): String {
    val customerName = report.customer.replace(" ", "_")
    val date = report.dateTime.atZone(ZoneId.systemDefault()).format(
        DateTimeFormatter.ISO_LOCAL_DATE
    )
    val id = report.id.take(4)

    return "${customerName}-${date}-${id}.pdf"
}

fun saveImagesIntoReport(
    context: Context,
    uris: List<Uri>,
    reportId: String,
    persistImageData: (List<ReportImage>) -> Unit
) {
    val newImages = uris.map { uri ->
        // Copy images into app's internal storage
        if (uri.path != null) {
            val id = UUID.randomUUID().toString()
            context.openFileOutput(id, Context.MODE_PRIVATE).use { outputStream ->
                context.contentResolver.openInputStream(uri).use { stream ->
                    outputStream.write(stream?.readBytes())
                }
            }
            val filePath = context.filesDir.absolutePath + "/" + id
            return@map ReportImage(
                id = id,
                reportId = reportId,
                filePath = filePath
            )
        } else {
            return@map null
        }
    }.filterNotNull()

    persistImageData(newImages)
}

fun shareReportPdf(context: Context, report: PanelReport) {
    val file = File(report.pdfFilePath)
    val uri = FileProvider.getUriForFile(context, "com.cakrasuryainti.panther.provider", file)
    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_STREAM, uri)
        flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
        type = "application/pdf"
    }

    val shareIntent = Intent.createChooser(sendIntent, "Share Report")
    ContextCompat.startActivity(context, shareIntent, null)
}