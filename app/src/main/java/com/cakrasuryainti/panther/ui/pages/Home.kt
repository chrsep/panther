package com.cakrasuryainti.panther.ui.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddCircleOutline
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Share
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawOpacity
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import androidx.ui.tooling.preview.Preview
import com.cakrasuryainti.panther.R
import com.cakrasuryainti.panther.db.model.PanelReport
import com.cakrasuryainti.panther.domain.shareReportPdf
import com.cakrasuryainti.panther.ui.pages.report.HomeViewModel
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

@ExperimentalMaterialApi
@Composable
fun HomeContainer(navController: NavHostController, viewModel: HomeViewModel) {
    val allReports by viewModel.allReport.collectAsState(initial = listOf())

    Home(navController, allReports)
}

@ExperimentalMaterialApi
@Composable
fun Home(navController: NavHostController, panelReports: List<PanelReport>) {
    var tabIndex by remember { mutableStateOf(0) }
    val scaffoldState = rememberBackdropScaffoldState(initialValue = BackdropValue.Revealed)

    BackdropScaffold(
        gesturesEnabled = false,
        scaffoldState = scaffoldState,
        appBar = {
            TopAppBar(
                title = { Text(text = "Cakra Surya Inti") },
                elevation = 0.dp,
                actions = {
                    IconButton(onClick = {
                        if (scaffoldState.isConcealed) {
                            scaffoldState.reveal()
                        } else {
                            scaffoldState.conceal()
                        }
                    })
                    {
                        Icon(
                            Icons.Rounded.AddCircleOutline,
                            modifier = Modifier.drawOpacity(if (scaffoldState.isRevealed) 0f else 1f)
                        )
                        Icon(
                            Icons.Rounded.Close,
                            modifier = Modifier.drawOpacity(if (scaffoldState.isConcealed) 0f else 1f)
                        )
                    }
                }
            )
        },
        backLayerContent = {
            Column(modifier = Modifier.padding(bottom = 16.dp)) {
                ListItem(
                    modifier = Modifier.clickable(onClick = { navController.navigate("create/panel") }),
                    icon = { Icon(Icons.Rounded.AddCircleOutline) }
                ) {
                    Text(text = "Buat Laporan LV Panel")
                }
                ListItem(
                    modifier = Modifier.clickable(onClick = { navController.navigate("create/genset") }),
                    icon = { Icon(Icons.Rounded.AddCircleOutline) }
                ) {
                    Text(text = "Buat Laporan Genset")
                }
            }
        }
    ) {
        Column {
            TabRow(
                selectedTabIndex = tabIndex,
                backgroundColor = Color.White,
                tabs = {
                    Tab(
                        selected = tabIndex == 0,
                        onClick = { tabIndex = 0 },
                    ) {
                        Text(
                            "LV Panel",
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                    Tab(
                        selected = tabIndex == 1,
                        onClick = { tabIndex = 1 },
                    ) {
                        Text(
                            "Genset",
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            )
            when (tabIndex) {
                0 -> ListLVPanel(panelReports)
                1 -> GensetReport(listOf())
            }
        }
    }

//    ConstraintLayout(Modifier.fillMaxWidth().fillMaxHeight()) {
//        val (fab) = createRefs()
//
//        ExtendedFloatingActionButton(
//            onClick = { navController.navigate("create") },
//            text = { Text("Laporan Baru") },
//            icon = { Icon(Icons.Rounded.Add) },
//            modifier = Modifier.constrainAs(fab) {
//                bottom.linkTo(parent.bottom, 16.dp)
//                end.linkTo(parent.end, 16.dp)
//            }
//        )
//    }
}

@Composable
fun ListLVPanel(allReports: List<PanelReport>) {
    val context = ContextAmbient.current

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
                "Belum Ada Laporan LV Panel Tersimpan",
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
                    lineHeight = 20.sp
                )
                Text(
                    it.dateTime.atZone(ZoneId.systemDefault())
                        .format(DateTimeFormatter.ofPattern("dd MMMM yyyy")),
                    style = MaterialTheme.typography.subtitle2,
                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f),
                    lineHeight = 20.sp
                )
            }
            IconButton(onClick = {
                shareReportPdf(context, it)
            }, modifier = Modifier.drawOpacity(0.7f)) {
                Icon(Icons.Rounded.Share)
            }
        }
    }
}

@Composable
fun GensetReport(allReports: List<PanelReport>) {
    val context = ContextAmbient.current

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
                "Belum Ada Laporan Genset Tersimpan",
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
                    lineHeight = 20.sp
                )
                Text(
                    it.dateTime.atZone(ZoneId.systemDefault())
                        .format(DateTimeFormatter.ofPattern("dd MMMM yyyy")),
                    style = MaterialTheme.typography.subtitle2,
                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f),
                    lineHeight = 20.sp
                )
            }
            IconButton(onClick = {
                shareReportPdf(context, it)
            }, modifier = Modifier.drawOpacity(0.7f)) {
                Icon(Icons.Rounded.Share)
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
@Preview
fun HomePreview() {
    val navController = rememberNavController()
    Home(navController, listOf())
}