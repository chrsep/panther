package com.cakrasuryainti.panther.ui.pages.saved

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.cakrasuryainti.panther.R
import com.cakrasuryainti.panther.db.model.GeneratorReport
import com.cakrasuryainti.panther.ui.pages.SavedViewModel


@Composable
fun ListGeneratorReport(
    navController: NavHostController,
    savedViewModel: SavedViewModel,
) {
    val allReports = listOf<GeneratorReport>()
    Scaffold(

        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Rounded.ArrowBack)
                    }
                },
                title = { Text(text = "Generator Reports") }
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
                    asset = vectorResource(id = R.drawable.undraw_list),
                    modifier = Modifier.width(240.dp).padding(top = 72.dp, bottom = 16.dp)
                )
                Text(
                    "Belum Ada Laporan Generator Inspection Tersimpan",
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier.width(240.dp),
                    textAlign = TextAlign.Center
                )
            }
        }

        LazyColumnFor(
            items = allReports, Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(bottom = 88.dp)
        ) {
            Row(
                Modifier.padding(16.dp).fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
//            Column {
//                Text(
//                    it.jobDesc.name.toUpperCase(Locale.ROOT),
//                    style = MaterialTheme.typography.overline,
//                    lineHeight = 20.sp
//                )
//                Text(
//                    it.customer,
//                    style = MaterialTheme.typography.body1,
//                    color = MaterialTheme.colors.onSurface,
//                    lineHeight = 20.sp
//                )
//                Text(
//                    it.dateTime.atZone(ZoneId.systemDefault())
//                        .format(DateTimeFormatter.ofPattern("dd MMMM yyyy")),
//                    style = MaterialTheme.typography.subtitle2,
//                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f),
//                    lineHeight = 20.sp
//                )
//            }
//            IconButton(onClick = {
//                shareReportPdf(context, it)
//            }, modifier = Modifier.drawOpacity(0.7f)) {
//                Icon(Icons.Rounded.Share)
//            }
            }
        }
    }
}
