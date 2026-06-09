package com.my.books

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Warna diambil dari AddScreen.kt — tidak didefinisikan ulang di sini
// PrimaryBlue, AccentBlue, LightBlue, BackgroundGray, BorderColor,
// TextPrimary, TextSecondary, White sudah ada di AddScreen.kt

// ── Data Model ────────────────────────────────────────────────────────────────

data class Book(
    val id: Int,
    val title: String,
    val author: String,
    val coverColor: Color,
    val accentColor: Color
)

// ── Sample Data ───────────────────────────────────────────────────────────────

val sampleBooks = listOf(
    Book(1, "Filosoft Teras",  "Henny Manaampang", Color(0xFF2D5016), Color(0xFFE8D5A3)),
    Book(2, "Atomic Habits",   "James Clear",      Color(0xFF1A1A2E), Color(0xFFD4AF37)),
    Book(3, "Laut Bercerita",  "Leila S. Chudori", Color(0xFF0D1B4B), Color(0xFF7EC8E3)),
    Book(4, "Ikigai",          "Hector Garcia",    Color(0xFFF5E6D3), Color(0xFFB8860B))
)

// ── Main Screen ───────────────────────────────────────────────────────────────

@Composable
fun HomeScreen(
    selectedTab: Int = 0,
    onTabSelected: (Int) -> Unit = {}
) {
    var searchQuery by remember { mutableStateOf("") }
    val books = remember { sampleBooks.toMutableStateList() }

    Scaffold(
        containerColor = BackgroundGray,
        bottomBar = {
            AddScreenBottomBar(
                selectedTab = selectedTab,
                onTabSelected = onTabSelected
            )
        }
    ) { innerPadding ->
        when (selectedTab) {
            0 -> HomeContent(
                modifier = Modifier.padding(innerPadding),
                searchQuery = searchQuery,
                onSearchChange = { searchQuery = it },
                books = books,
                onDeleteBook = { book -> books.removeIf { it.id == book.id } }
            )
            2 -> LibraryContent(
                modifier = Modifier.padding(innerPadding),
                books = books,
                onDeleteBook = { book -> books.removeIf { it.id == book.id } }
            )
        }
    }
}

// ── Home Content ──────────────────────────────────────────────────────────────

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    searchQuery: String,
    onSearchChange: (String) -> Unit,
    books: List<Book>,
    onDeleteBook: (Book) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    ) {
        Spacer(Modifier.height(20.dp))
        HomeTopBar()
        Spacer(Modifier.height(22.dp))
        GreetingSection()
        Spacer(Modifier.height(20.dp))
        BookSearchBar(query = searchQuery, onQueryChange = onSearchChange)
        Spacer(Modifier.height(28.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "Rekomendasi Untukmu",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
            )
            Text(
                "Lihat Semua",
                fontSize = 13.sp,
                color = AccentBlue,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.clickable { }
            )
        }

        Spacer(Modifier.height(16.dp))

        val filtered = if (searchQuery.isBlank()) books
        else books.filter {
            it.title.contains(searchQuery, ignoreCase = true) ||
                    it.author.contains(searchQuery, ignoreCase = true)
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(filtered, key = { it.id }) { book ->
                BookCard(book = book, onDelete = { onDeleteBook(book) })
            }
        }
    }
}

// ── Library Content ───────────────────────────────────────────────────────────

@Composable
fun LibraryContent(
    modifier: Modifier = Modifier,
    books: List<Book>,
    onDeleteBook: (Book) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    ) {
        Spacer(Modifier.height(20.dp))
        Text("Library", fontSize = 22.sp, fontWeight = FontWeight.ExtraBold, color = TextPrimary)
        Text("Semua koleksi bukumu", fontSize = 14.sp, color = TextSecondary)
        Spacer(Modifier.height(20.dp))

        if (books.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        Icons.Outlined.Book,
                        contentDescription = null,
                        tint = TextSecondary,
                        modifier = Modifier.size(48.dp)
                    )
                    Spacer(Modifier.height(12.dp))
                    Text("Belum ada buku", color = TextSecondary, fontSize = 14.sp)
                }
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(14.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(books, key = { it.id }) { book ->
                    BookCard(book = book, onDelete = { onDeleteBook(book) })
                }
            }
        }
    }
}

// ── Top Bar ───────────────────────────────────────────────────────────────────

@Composable
fun HomeTopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(White)
            .padding(horizontal = 20.dp, vertical = 14.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(AccentBlue),
                contentAlignment = Alignment.Center
            ) {
                Text("M", color = White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
            Text(
                "My Books",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = TextPrimary
            )
        }
        Icon(
            Icons.Outlined.Notifications,
            contentDescription = null,
            tint = TextPrimary,
            modifier = Modifier.size(24.dp)
        )
    }
}

// ── Greeting ──────────────────────────────────────────────────────────────────

@Composable
fun GreetingSection() {
    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                "Halo, Pembaca",
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold,
                color = TextPrimary
            )
            Spacer(Modifier.width(6.dp))
            Text("👋", fontSize = 22.sp)
        }
    }
}

// ── Search Bar ────────────────────────────────────────────────────────────────

@Composable
fun BookSearchBar(query: String, onQueryChange: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(LightBlue)
            .padding(horizontal = 14.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            Icons.Default.Search,
            contentDescription = null,
            tint = TextSecondary,
            modifier = Modifier.size(20.dp)
        )
        Spacer(Modifier.width(10.dp))
        Box(modifier = Modifier.fillMaxWidth()) {
            if (query.isEmpty()) {
                Text("Cari judul buku, penulis...", fontSize = 14.sp, color = TextSecondary)
            }
            BasicTextField(
                value = query,
                onValueChange = onQueryChange,
                singleLine = true,
                textStyle = TextStyle(fontSize = 14.sp, color = TextPrimary),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

// ── Book Card ─────────────────────────────────────────────────────────────────

@Composable
fun BookCard(book: Book, onDelete: () -> Unit) {
    var showMenu by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(4.dp, RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = White)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                    .background(
                        Brush.verticalGradient(
                            listOf(book.coverColor, book.coverColor.copy(alpha = 0.7f))
                        )
                    )
            ) {
                Text(
                    text = book.title,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = book.accentColor,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    lineHeight = 15.sp,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(12.dp)
                )

                Box(modifier = Modifier.align(Alignment.TopEnd)) {
                    Box(
                        modifier = Modifier
                            .padding(8.dp)
                            .size(28.dp)
                            .clip(CircleShape)
                            .background(Color.White.copy(alpha = 0.9f))
                            .clickable { showMenu = true },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            Icons.Default.MoreVert,
                            contentDescription = null,
                            tint = TextPrimary,
                            modifier = Modifier.size(14.dp)
                        )
                    }
                    DropdownMenu(
                        expanded = showMenu,
                        onDismissRequest = { showMenu = false }
                    ) {
                        DropdownMenuItem(
                            text = {
                                Text("Hapus dari daftar", color = Color(0xFFFF4B6E), fontSize = 13.sp)
                            },
                            leadingIcon = {
                                Icon(
                                    Icons.Default.Delete,
                                    contentDescription = null,
                                    tint = Color(0xFFFF4B6E),
                                    modifier = Modifier.size(16.dp)
                                )
                            },
                            onClick = { showMenu = false; onDelete() }
                        )
                    }
                }
            }

            Column(modifier = Modifier.padding(10.dp)) {
                Text(
                    book.title,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = TextPrimary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(Modifier.height(2.dp))
                Text(
                    book.author,
                    fontSize = 11.sp,
                    color = TextSecondary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

// ── Preview ───────────────────────────────────────────────────────────────────

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}