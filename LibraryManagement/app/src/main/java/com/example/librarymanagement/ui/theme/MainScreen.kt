import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.librarymanagement.model.Book

@Composable
fun MainScreen() {

    var selectedTab by remember { mutableStateOf(0) }

    // dữ liệu dùng chung
    val books = remember {
        mutableStateListOf(
            Book(1, "Sách 01"),
            Book(2, "Sách 02"),
            Book(3,"Sách 03"),
            Book(4,"Sách 04")
        )
    }

    val users = remember {
        mutableStateListOf(
            User(1, "Nguyen Van A"),
            User(2, "Tran Thi B")
        )
    }

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    icon = { Icon(Icons.Default.Home, null) },
                    label = { Text("Quản lý") }
                )
                NavigationBarItem(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    icon = { Icon(Icons.Default.List, null) },
                    label = { Text("DS Sách") }
                )
                NavigationBarItem(
                    selected = selectedTab == 2,
                    onClick = { selectedTab = 2 },
                    icon = { Icon(Icons.Default.Person, null) },
                    label = { Text("Nhân viên") }
                )
            }
        }
    ) { padding ->
        when (selectedTab) {
            0 -> {
                ManageScreen(books, users, Modifier.padding(padding))
            }
            1 -> {
                BookListScreen(books, Modifier.padding(padding))
            }
            2 -> {
                UserScreen(users, Modifier.padding(padding))
            }
        }
    }
}
