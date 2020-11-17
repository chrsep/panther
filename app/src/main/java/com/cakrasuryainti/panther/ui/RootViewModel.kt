package com.cakrasuryainti.panther.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cakrasuryainti.panther.db.model.PanelReport
import com.cakrasuryainti.panther.repository.PanelReportRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class RootViewModel @ViewModelInject constructor(
    private val repo: PanelReportRepository
) : ViewModel() {
    private val _currentPanelReport = MutableLiveData<PanelReport>()
    val currentPanelReport: LiveData<PanelReport> = _currentPanelReport

    init {
        viewModelScope.launch {
            repo.getCurrentReport().first {
                if (it != null) {
                    _currentPanelReport.value = it
                    return@first true
                } else {
                    return@first false
                }
            }
        }
    }


    fun updateReport(report: PanelReport) {
        _currentPanelReport.value = report
        viewModelScope.launch(Dispatchers.IO) {
            repo.updateReport(report)
        }
    }
}
