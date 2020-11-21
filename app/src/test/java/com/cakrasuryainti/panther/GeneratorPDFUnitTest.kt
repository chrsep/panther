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
                "Test",
                "test",
                "",
               2300,
                "Yes"
            ),
            outputStream
        )
    }
}