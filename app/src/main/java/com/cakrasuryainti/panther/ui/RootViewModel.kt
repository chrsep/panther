package com.cakrasuryainti.panther.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.cakrasuryainti.panther.repository.PanelReportRepository

class RootViewModel @ViewModelInject constructor(repo: PanelReportRepository) :
    ViewModel() {
    val currentPanelReport = repo.getCurrentReport()
}
