import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication.screen.HomeScreen
import com.example.uicomponents.CardDetailScreen
import com.example.uicomponents.DividerDetailScreen
import com.example.uicomponents.IconDetailScreen
import com.example.uicomponents.ImageDetailScreen
import com.example.uicomponents.TextDetailScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController, startDestination = "home") {

        composable("home") { HomeScreen(navController) }

        composable("text") { TextDetailScreen() }
        composable("image") { ImageDetailScreen() }
        composable("icon") { IconDetailScreen() }
        composable("divider") { DividerDetailScreen() }
        composable("card") { CardDetailScreen() }

//        composable("textfield") { TextFieldDetailScreen() }
//        composable("outlined") { OutlinedTextFieldDetailScreen() }
//        composable("password") { PasswordDetailScreen() }
//        composable("checkbox") { CheckboxDetailScreen() }
//        composable("switch") { SwitchDetailScreen() }
//
//        composable("button") { ButtonDetailScreen() }
//        composable("outlinedbutton") { OutlinedButtonDetailScreen() }
//        composable("iconbutton") { IconButtonDetailScreen() }
//        composable("fab") { FabDetailScreen() }
//
//        composable("column") { ColumnDetailScreen() }
//        composable("row") { RowDetailScreen() }
//        composable("box") { BoxDetailScreen() }
//        composable("lazycolumn") { LazyColumnDetailScreen() }
    }
}

