package nl.pdik.level3.madlevel3_task2.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import nl.pdik.level3.madlevel3_task2.R
import nl.pdik.level3.madlevel3_task2.model.Portal
import nl.pdik.level3.madlevel3_task2.ui.theme.MADLevel3Task2Theme
import nl.pdik.level3.madlevel3_task2.viewModel.PortalViewModel

@Composable
fun PortalOverviewScreen(navHostController: NavHostController, viewModel: PortalViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = stringResource(id = R.string.app_name)) })
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { null },
                icon = { Icon(Icons.Filled.Add, "Next") },
                onClick = { navHostController.navigate(PortalScreens.AddPortalScreen.name) },
                shape = MaterialTheme.shapes.large.copy(CornerSize(percent = 34)),
            )
        },

        content = { padding ->
            PortalOverviewScreenContent(
                Modifier.padding(padding),
                navHostController,
                viewModel
            )
        }
    )
}

@Composable
private fun PortalOverviewScreenContent(
    modifier: Modifier,
    navHostController: NavHostController,
    viewModel: PortalViewModel
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(140.dp),
        contentPadding = PaddingValues(8.dp),
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceAround,

        content = {
            items(items = viewModel.portals) { portal ->
                Row(Modifier.padding(8.dp)) {
                    PortalLayout(portal)
                }
            }
        }
    )
}

@Composable
fun PortalLayout(portal: Portal) {
    Card(
        backgroundColor = Color.Gray,
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
        elevation = 8.dp,
    ) {
        Column() {
            Text(
                text = portal.name,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                color = Color(0xFFFFFFFF),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
            Text(
                text = portal.url,
                fontWeight = FontWeight.Normal,
                fontSize = 8.sp,
                color = Color(0xFFFFFFFF),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(start = 16.dp, top = 0.dp, bottom = 4.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreviewOverview() {
    MADLevel3Task2Theme {
        val navController = rememberNavController()
        val viewModel: PortalViewModel = viewModel()
        viewModel.portals.addAll(viewModel.someValues())
        PortalOverviewScreen(navController, viewModel);
    }
}