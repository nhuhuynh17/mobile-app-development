import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.librarymanagement.model.Book

@Composable
fun BookListScreen(
    books: List<Book>,
    modifier: Modifier = Modifier
) {
    Column(modifier.padding(16.dp)) {
        Text("Danh sách sách", fontSize = 20.sp)

        books.forEach {
            Text("• ${it.name}")
        }
    }
}
