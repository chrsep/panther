package com.cakrasuryainti.panther

import com.cakrasuryainti.panther.db.model.*
import com.cakrasuryainti.panther.domain.generateGeneratorReport
import org.junit.Test
import java.io.FileOutputStream
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class GeneratorPDFUnitTest {
    @Test
    fun can_generate_panel_pdf() {
        val outputStream = FileOutputStream("test-generator.pdf")
        generateGeneratorReport(
            GeneratorReport(
                UUID.randomUUID().toString(),
                "PT STP Group",
                "test",
                "",
                "",
                "Yes",
                ""
            ),
            listOf(
                GeneratorReportImages(
                    UUID.randomUUID().toString(),
                    "test-image-2.jpg",
                    "Deskripsi tentang gambar 1",
                    ""
                ),
                GeneratorReportImages(
                    UUID.randomUUID().toString(),
                    "test-image-3.jpg",
                    "Deskripsi tentang gambar 2",
                    ""
                ),
                GeneratorReportImages(
                    UUID.randomUUID().toString(),
                    "test-image.jpg",
                    "Deskripsi tentang gambar 3",
                    ""
                ),
                GeneratorReportImages(
                    UUID.randomUUID().toString(),
                    "test-image-2.jpg",
                    "Deskripsi tentang gambar 4",
                    ""
                )
            ),
            outputStream
        )
    }
}