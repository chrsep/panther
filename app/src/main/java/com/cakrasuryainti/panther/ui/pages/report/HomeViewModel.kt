package com.cakrasuryainti.panther.ui.pages.report

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.cakrasuryainti.panther.repository.PanelReportRepository

class HomeViewModel @ViewModelInject constructor(repo: PanelReportRepository): ViewModel() {
    val allReport = repo.findAllReport()
}