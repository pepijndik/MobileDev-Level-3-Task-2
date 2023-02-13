package nl.pdik.level3.madlevel3_task2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import nl.pdik.level3.madlevel3_task2.ui.screens.AddPortalScreen
import nl.pdik.level3.madlevel3_task2.ui.screens.PortalOverviewScreen
import nl.pdik.level3.madlevel3_task2.ui.screens.PortalScreens
import nl.pdik.level3.madlevel3_task2.ui.theme.MADLevel3Task2Theme
import nl.pdik.level3.madlevel3_task2.viewModel.PortalViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MADLevel3Task2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    val navController = rememberNavController()
                    PortalNavHost(navController,modifier = Modifier)
                }
            }
        }
    }
}
@Composable
private fun PortalNavHost(
    navController: NavHostController, modifier: Modifier
) {
    val viewModel: PortalViewModel = viewModel()
    viewModel.someValues(); //some random start values
    NavHost(
        navController = navController,
        startDestination = PortalScreens.OverviewScreen.name,
        modifier = modifier
    ){
        composable(route = PortalScreens.OverviewScreen.name)
        {
            PortalOverviewScreen(navController,viewModel)
        }
        composable(PortalScreens.AddPortalScreen.name) {
            AddPortalScreen(navController,viewModel)
        }
    }

}




@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MADLevel3Task2Theme {
        val navController = rememberNavController()
        val viewModel: PortalViewModel = viewModel()
        PortalOverviewScreen(navController,viewModel)
    }
}