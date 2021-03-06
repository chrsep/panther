package com.cakrasuryainti.panther

import android.graphics.BitmapFactory
import com.cakrasuryainti.panther.db.model.JobDesc
import com.cakrasuryainti.panther.db.model.PanelReport
import com.cakrasuryainti.panther.db.model.ReportImage
import com.cakrasuryainti.panther.db.model.Status
import com.cakrasuryainti.panther.domain.generatePanelReport
import org.junit.Test
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.time.Instant
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class PanelPDFUnitTest {
    @Test
    fun can_generate_panel_pdf() {
        val outputStream = FileOutputStream("test.pdf")
        val logo = File("./src/main/assets/csi_logo_full.png")

        val mockImages = listOf(
            ReportImage(
                UUID.randomUUID().toString(),
                "test-image-2.jpg",
                "Deskripsi tentang gambar 1",
                ""
            ),
            ReportImage(
                UUID.randomUUID().toString(),
                "test-image-3.jpg",
                "Deskripsi tentang gambar 2",
                ""
            ),
            ReportImage(
                UUID.randomUUID().toString(),
                "test-image.jpg",
                "Deskripsi tentang gambar 3",
                ""
            ),
            ReportImage(
                UUID.randomUUID().toString(),
                "test-image-2.jpg",
                "Deskripsi tentang gambar 4",
                ""
            ),
            ReportImage(
                UUID.randomUUID().toString(),
                "test-image-3.jpg",
                "Deskripsi tentang gambar 5",
                ""
            ),
            ReportImage(
                UUID.randomUUID().toString(),
                "test-image-2.jpg",
                "Deskripsi tentang gambar 6",
                ""
            ),
            ReportImage(
                UUID.randomUUID().toString(),
                "test-image-3.jpg",
                "Deskripsi tentang gambar 7",
                ""
            ),
            ReportImage(
                UUID.randomUUID().toString(),
                "test-image-3.jpg",
                "Deskripsi tentang gambar 8",
                ""
            ),
            ReportImage(
                UUID.randomUUID().toString(),
                "test-image.jpg",
                "Deskripsi tentang gambar 9",
                ""
            ),
            ReportImage(
                UUID.randomUUID().toString(),
                "test-image.jpg",
                "Deskripsi tentang gambar 10",
                ""
            ),
            ReportImage(
                UUID.randomUUID().toString(),
                "test-image.jpg",
                "Deskripsi tentang gambar 11",
                ""
            ),
            ReportImage(
                UUID.randomUUID().toString(),
                "test-image-2.jpg",
                "Deskripsi tentang gambar 12",
                ""
            ),
            ReportImage(
                UUID.randomUUID().toString(),
                "test-image-2.jpg",
                "Deskripsi tentang gambar 13",
                ""
            )
        )
        generatePanelReport(
            PanelReport(
                UUID.randomUUID().toString(),
                "Test",
                "test",
                "test",
                "test",
                "test",
                JobDesc.Repair,
                Instant.now(),
                377f,
                379f,
                378f,
                217f,
                220f,
                217f,
                0f,
                "keterangan",
                87.24f,
                573f,
                65.5f,
                "keterangan",
                49.93f,
                "keterangan",
                0.95f,
                "keterangan",
                "Status baik baik saja",
                "keterangan",
                Status.Ok,
                "keterangan",
                Status.Ok,
                "keterangan",
                Status.Ok,
                "keterangan",
                Status.Ok,
                "keterangan",
                Status.Ok,
                "keterangan",
                Status.Ok,
                "keterangan",
                Status.Ok,
                "keterangan",
                Status.Ok,
                "keterangan",
                Status.Ok,
                "keterangan",
                Status.Ok,
                "keterangan",
                Status.Ok,
                "keterangan",
                Status.Ok,
                "keterangan",
                Status.Ok,
                "keterangan",
                Status.Ok,
                "keterangan",
                Status.Ok,
                "keterangan",
                Status.Ok,
                "keterangan",
                Status.Ok,
                "keterangan",
                Status.Ok,
                "keterangan",
                Status.Ok,
                "keterangan",
                Status.Ok,
                "keterangan",
                Status.Ok,
                "keterangan",
                Status.Ok,
                "keterangan",
                "It's all good.",
                true
            ),
            mockImages,
            outputStream,
            logo.readBytes()
        )
    }
}