package com.cakrasuryainti.panther

import org.junit.Test
import java.time.Instant
import java.time.ZonedDateTime
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class MaintenancePdfUnitTest {
    @Test
    fun can_generate_panel_pdf() {
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
                Status.Ok,
                Status.Ok,
                Status.Ok,
                Status.Ok,
                Status.Ok,
                Status.Ok,
                Status.Ok,
                Status.Ok,
                Status.Ok,
                Status.Ok,
                Status.Ok,
                Status.Ok,
                Status.Ok,
                Status.Ok,
                Status.Ok,
                Status.Ok,
                Status.Ok,
                Status.Ok,
                Status.Ok,
                Status.Ok,
                Status.Ok,
                Status.Ok,
                "It's all good."
            ),
            "test.pdf"
        )
    }
}