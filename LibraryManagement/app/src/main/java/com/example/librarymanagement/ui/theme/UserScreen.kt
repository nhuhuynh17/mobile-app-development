import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun UserScreen(
    users: List<User>,
    modifier: Modifier = Modifier
) {
    Column(modifier.padding(16.dp)) {
        Text("Danh sách nhân viên", fontSize = 20.sp)

        users.forEach { user ->
            Text("👤 ${user.name}")

            if (user.borrowedBooks.isEmpty()) {
                Text("   Chưa mượn sách")
            } else {
                user.borrowedBooks.forEach {
                    Text("   • ${it.name}")
                }
            }

            Spacer(Modifier.height(8.dp))
        }
    }
}
