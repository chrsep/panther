package com.cakrasuryainti.panther.ui.pages.saved

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawOpacity
import androidx.compose.ui.platform.AmbientContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.cakrasuryainti.panther.R
import com.cakrasuryainti.panther.domain.shareReportPdf
import com.cakrasuryainti.panther.ui.pages.SavedViewModel
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

@Composable
fun ListLVPanel(navController: NavHostController, savedViewModel: SavedViewModel) {
    val context = AmbientContext.current
    val allReports by savedViewModel.allReport.collectAsState(initial = listOf())

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Rounded.ArrowBack)
                    }
                },
                title = { Text(text = "LV Panel Reports") }
            )
        }
    ) {
        if (allReports.isEmpty()) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    vectorResource(id = R.drawable.undraw_list),
                    modifier = Modifier.width(240.dp).padding(top = 72.dp, bottom = 16.dp)
                )
                Text(
                    "Belum Ada Laporan LV Panel Maintenance Tersimpan",
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier.width(240.dp),
                    textAlign = TextAlign.Center
                )
            }
        }

        LazyColumnFor(items = allReports, Modifier.fillMaxWidth()) {
            Row(
                Modifier.padding(16.dp).fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        it.jobDesc.name.toUpperCase(Locale.ROOT),
                        style = MaterialTheme.typography.overline,
                        lineHeight = 20.sp
                    )
                    Text(
                        it.customer,
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.onSurface,
                        lineHeight = 20.sp,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                    Text(
                        it.dateTime.atZone(ZoneId.systemDefault())
                            .format(DateTimeFormatter.ofPattern("dd MMMM yyyy")),
                        style = MaterialTheme.typography.subtitle2,
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f),
                        lineHeight = 20.sp,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
                IconButton(
                    onClick = { shareReportPdf(context, it) },
                    modifier = Modifier.drawOpacity(0.7f)
                ) {
                    Icon(Icons.Rounded.Share)
                }
            }
        }
    }
}

