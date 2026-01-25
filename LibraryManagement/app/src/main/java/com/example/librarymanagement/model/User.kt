import com.example.librarymanagement.model.Book

data class User(
    val id: Int,
    val name: String,
    val borrowedBooks: MutableList<Book> = mutableListOf()
)
