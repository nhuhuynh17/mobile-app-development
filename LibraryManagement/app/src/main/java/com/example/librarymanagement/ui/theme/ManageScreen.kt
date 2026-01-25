import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.librarymanagement.model.Book

@Composable
fun ManageScreen(
    books: List<Book>,
    users: MutableList<User>,
    modifier: Modifier = Modifier
) {
    var currentUser by remember { mutableStateOf(users.first()) }
    var selectedUserTemp by remember { mutableStateOf(currentUser) }
    var expanded by remember { mutableStateOf(false) } // khai báo expanded

    Column(Modifier.padding(16.dp)) {

        Text("Hệ thống Quản lý Thư viện", fontSize = 20.sp)
        Spacer(Modifier.height(12.dp))

        // ===== NHÂN VIÊN =====
        Text("Nhân viên")

        OutlinedButton(
            onClick = { expanded = true },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(selectedUserTemp.name)
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            users.forEach { user ->
                DropdownMenuItem(
                    text = { Text(user.name) },
                    onClick = {
                        selectedUserTemp = user
                        expanded = false
                    }
                )
            }
        }

        users.forEach { user ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 4.dp)
            ) {
                Checkbox(
                    checked = selectedUserTemp == user,
                    onCheckedChange = {
                        selectedUserTemp = user
                    }
                )
                Text(user.name)
            }
        }

        Button(
            onClick = {
                currentUser = selectedUserTemp
            },
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("Đổi")
        }

        Spacer(Modifier.height(16.dp))

        // ===== SÁCH =====
        Text("Danh sách sách (Nhân viên: ${currentUser.name})")

        books.forEach { book ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = currentUser.borrowedBooks.contains(book),
                    onCheckedChange = { checked ->
                        if (checked)
                            currentUser.borrowedBooks.add(book)
                        else
                            currentUser.borrowedBooks.remove(book)
                    }
                )
                Text(book.name)
            }
        }
    }
}
