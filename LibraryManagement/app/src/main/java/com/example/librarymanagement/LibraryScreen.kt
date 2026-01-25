package com.example.librarymanagement.ui



import User
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.librarymanagement.model.Book

@Composable
fun LibraryScreen() {

    // 👤 Người dùng
    var user by remember {
        mutableStateOf(User(1,"Nguyen Van A"))
    }

    // 📚 Danh sách sách
    var books by remember {
        mutableStateOf(
            listOf(
                Book(1, "Sách 01"),
                Book(2, "Sách 02")
            )
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        // 🏷️ Tiêu đề
        Text(
            text = "Hệ thống\nQuản lý Thư viện",
            fontSize = 22.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 👤 Nhân viên
        Text(text = "Nhân viên")
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = user.name,
                onValueChange = { user = user.copy(name = it) },
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Button(onClick = {}) {
                Text("Đổi")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 📚 Danh sách sách
        Text(text = "Danh sách sách")

        LazyColumn {
            items(books) { book ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 8.dp)
                ) {
                    Checkbox(
                        checked = book.isBorrowed,
                        onCheckedChange = { checked ->
                            books = books.map {
                                if (it.id == book.id)
                                    it.copy(isBorrowed = checked)
                                else it
                            }
                        }
                    )
                    Text(text = book.name)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // ➕ Thêm sách
        Button(
            onClick = {
                val newBook = Book(
                    id = books.size + 1,
                    name = "Sách ${books.size + 1}"
                )
                books = books + newBook
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Thêm")
        }
    }
}
