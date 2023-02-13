package nl.pdik.level3.madlevel3_task2.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import nl.pdik.level3.madlevel3_task2.R
import nl.pdik.level3.madlevel3_task2.viewModel.PortalViewModel

@Composable
fun AddPortalScreen(navHostController: NavHostController, viewModel: PortalViewModel){
    Scaffold(
        topBar =  {
            TopAppBar( title = { Text(text = stringResource(id = R.string.app_name))})
        },
        content = {padding -> AddPortalScreenContent(Modifier.padding(padding))  }
    )
}
@Composable
private fun AddPortalScreenContent(modifier: Modifier){

}