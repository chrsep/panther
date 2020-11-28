package com.cakrasuryainti.panther.ui.pages.report.panel

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cakrasuryainti.panther.db.model.PanelReport
import com.cakrasuryainti.panther.db.model.PanelReportWithImages
import com.cakrasuryainti.panther.db.model.ReportImage
import com.cakrasuryainti.panther.domain.generatePanelReport
import com.cakrasuryainti.panther.domain.generatePdfFileName
import com.cakrasuryainti.panther.repository.PanelReportRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.io.ByteArrayOutputStream
import java.util.*

// We currently only use a single viewModel due to the simplicity of the app and the lack of support
// of attaching dagger hilt's viewModelFactory to components under navHost, which provides its own
// viewModel scope and factory with no hilt's di capabilities.
class PanelViewModel @ViewModelInject constructor(
    private val repo: PanelReportRepository
) : ViewModel() {
    private val _currentPanelReport = MutableLiveData<PanelReportWithImages>().also {
        viewModelScope.launch {
            repo.getCurrentReport().first { report ->
                if (report != null) {
                    it.value = report
                    return@first true
                } else {
                    return@first false
                }
            }
        }
    }
    val currentPanelReport: LiveData<PanelReportWithImages> = _currentPanelReport

    fun updateReport(report: PanelReport) {
        _currentPanelReport.value = _currentPanelReport.value?.copy(report = report)
        viewModelScope.launch(Dispatchers.IO) {
            val updatedRow = repo.updateReport(report)
            if (updatedRow == 0) {
                repo.insertNewReport(report)
            }
        }
    }

    fun saveImages(images: List<ReportImage>) {
        _currentPanelReport.value = _currentPanelReport.value?.copy(
            images = _currentPanelReport.value?.images?.plus(images) ?: listOf()
        )
        viewModelScope.launch(Dispatchers.IO) {
            repo.saveImages(images)
        }
    }

    fun updateImage(image: ReportImage) {
        _currentPanelReport.value = _currentPanelReport.value?.copy(
            images = _currentPanelReport.value?.images?.map {
                if (it.id == image.id) image else it
            } ?: listOf()
        )

        viewModelScope.launch(Dispatchers.IO) {
            repo.updateImage(image)
        }
    }

    fun removeImage(image: ReportImage) {
        _currentPanelReport.value = _currentPanelReport.value?.copy(
            images = _currentPanelReport.value?.images?.filter {
                it.id != image.id
            } ?: listOf()
        )

        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteImage(image)
        }
    }

    fun finalizeReport(
        reportWithImages: PanelReportWithImages?,
        context: Context,
        onSuccess: () -> Unit,
        onCatch: () -> Unit
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                if (reportWithImages != null) {
                    // generate filename
                    val pdfFileName = generatePdfFileName(reportWithImages.report)

                    // get logo
                    val ims = context.assets.open("csi_logo_full.png")
                    val bmp = BitmapFactory.decodeStream (ims)
                    val stream = ByteArrayOutputStream()
                    bmp.compress(Bitmap.CompressFormat.PNG, 100, stream)
                    // Save file
                    context.openFileOutput(pdfFileName, Context.MODE_PRIVATE).use {
                        generatePanelReport(
                            reportWithImages.report,
                            reportWithImages.images,
                            it,
                            stream.toByteArray()
                        )
                    }

                    // Update DB
                    val newReport = reportWithImages.report.copy(
                        finished = true,
                        pdfFilePath = context.filesDir.absolutePath + "/" + pdfFileName,
                    )
                    repo.updateReport(newReport)

                    // Update viewModel
                    withContext(Dispatchers.Main) {
                        _currentPanelReport.value =
                            _currentPanelReport.value?.copy(report = newReport)
                        onSuccess()
                    }
                }
            } catch (e: Exception) {
                Timber.e(e)
                onCatch()
            }
        }
    }

    fun newReport() {
        val report = PanelReport(UUID.randomUUID().toString())
        _currentPanelReport.value = PanelReportWithImages(report, listOf())
        viewModelScope.launch(Dispatchers.IO) {
            repo.insertNewReport(report)
        }
    }

    fun getImage(imageId: String?): Flow<ReportImage> {
        return repo.findImageById(imageId)
    }
}
