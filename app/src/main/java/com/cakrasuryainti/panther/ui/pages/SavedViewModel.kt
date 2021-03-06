package com.cakrasuryainti.panther.ui.pages

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.cakrasuryainti.panther.repository.PanelReportRepository

class SavedViewModel @ViewModelInject constructor(repo: PanelReportRepository): ViewModel() {
    val allReport = repo.findAllReport()
}