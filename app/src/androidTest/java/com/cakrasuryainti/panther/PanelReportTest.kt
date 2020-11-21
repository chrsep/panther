package com.cakrasuryainti.panther

import androidx.compose.foundation.layout.ExperimentalLayout
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.cakrasuryainti.panther.ui.Root
import org.junit.Rule
import org.junit.Test


@ExperimentalMaterialApi
@ExperimentalLayout
class PanelReportTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun form_should_work() {
        // Start the app
        composeTestRule.setContent {
            Root()
        }

        composeTestRule.onNodeWithLabel("toogle_scaffold").performClick()
        composeTestRule.onNodeWithText("Buat Laporan LV Panel").performClick()
        composeTestRule.onNodeWithLabel("pekerjaan").performTextInput("Testing")

        // make sure next failed when only on field is filled
        composeTestRule.onNodeWithText("Next").performClick()
    }
}