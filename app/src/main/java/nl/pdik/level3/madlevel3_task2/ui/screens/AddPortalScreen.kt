package nl.pdik.level3.madlevel3_task2.ui.screens

import android.content.Context
import android.webkit.URLUtil
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import nl.pdik.level3.madlevel3_task2.R
import nl.pdik.level3.madlevel3_task2.model.Portal
import nl.pdik.level3.madlevel3_task2.ui.theme.MADLevel3Task2Theme
import nl.pdik.level3.madlevel3_task2.viewModel.PortalViewModel

@Composable
fun AddPortalScreen(
    navHostController: NavHostController,
    viewModel: PortalViewModel,
    context: Context
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = stringResource(id = R.string.app_name)) })
        },
        content = { padding ->
            AddPortalScreenContent(
                Modifier.padding(padding),
                navHostController,
                viewModel,
                context
            )
        }
    )
}

@Composable
private fun AddPortalScreenContent(
    modifier: Modifier,
    navHostController: NavHostController,
    viewModel: PortalViewModel,
    context: Context
) {
    val name = remember { mutableStateOf("") }
    val url = remember { mutableStateOf("https://") }
    Column(modifier = Modifier) {
        TextField(
            value = name.value,
            onValueChange = { name.value = it },
            label = { Text(stringResource(id = R.string.new_portal_name)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 32.dp, top = 24.dp),
            textStyle = LocalTextStyle.current.copy(color = LocalContentColor.current),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            maxLines = 1
        )
        TextField(
            value = url.value,
            onValueChange = { url.value = it },
            label = { Text(stringResource(id = R.string.new_portal_url)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 32.dp, top = 24.dp),
            textStyle = LocalTextStyle.current.copy(color = LocalContentColor.current),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            maxLines = 1
        )

        Button(
            modifier = Modifier.padding(start = 32.dp, top = 24.dp).fillMaxWidth(),
            onClick = { onAdd(name.value, url.value, viewModel, context,navHostController) })
        {
            Text(text = stringResource(id = R.string.add_portal))
        }

    }
}


private fun onAdd(name: String, url: String, viewModel: PortalViewModel, context: Context, navHostController: NavHostController) {
    val regex = Regex("(.{6})")
    if(name.isBlank()){
        informUser(context,R.string.name_empty)
    }
    if (url.length >=6 && name.isNotBlank() && URLUtil.isValidUrl(url)) {
        viewModel.addPortal(Portal(url, name))
        navHostController.popBackStack()
    }else{
        informUser(context,R.string.url_misformed)
    }
}
private fun informUser(context: Context, msgId: Int) {
    Toast.makeText(context, context.getString(msgId), Toast.LENGTH_SHORT).show()
}


@Preview(showBackground = true)
@Composable
fun DefaultPreviewNewPortal() {
    MADLevel3Task2Theme {
        val navController = rememberNavController()
        val viewModel: PortalViewModel = viewModel()
        AddPortalScreen(navController, viewModel, LocalContext.current)
    }
}