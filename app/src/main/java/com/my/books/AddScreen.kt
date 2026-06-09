package com.my.books

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.AddBox
import androidx.compose.material.icons.outlined.Book
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


val PrimaryBlue = Color(0xFF1A3A6B)
val AccentBlue = Color(0xFF2D6FBF)
val LightBlue = Color(0xFFE8F0FA)
val BackgroundGray = Color(0xFFF4F6FA)
val BorderColor = Color(0xFFCDD8EE)
val TextPrimary = Color(0xFF1A2B4A)
val TextSecondary = Color(0xFF6B7A99)
val White = Color(0xFFFFFFFF)


@Composable
fun AddScreen(
    selectedTab: Int = 1,
    onTabSelected: (Int) -> Unit = {}
) {
    var judulBuku by remember { mutableStateOf("") }
    var namaPenulis by remember { mutableStateOf("") }

    Scaffold(
        containerColor = BackgroundGray,
        bottomBar = {
            AddScreenBottomBar(
                selectedTab = selectedTab,
                onTabSelected = onTabSelected
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            // ── Top App Bar ──
            AddScreenTopBar()

            // ── Konten Utama ──
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                Spacer(modifier = Modifier.height(24.dp))

                // Judul Halaman
                Text(
                    text = "Tambah Buku Impian",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary,
                    lineHeight = 32.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Catat buku yang ingin kamu baca selanjutnya untuk\nkoleksi perpustakaan pribadimu.",
                    fontSize = 13.sp,
                    color = TextSecondary,
                    lineHeight = 19.sp
                )

                Spacer(modifier = Modifier.height(28.dp))

                // Upload Cover
                AddScreenUploadCover()

                Spacer(modifier = Modifier.height(24.dp))

                // Input Judul Buku
                AddScreenInputField(
                    label = "Judul Buku",
                    placeholder = "Masukkan judul buku...",
                    value = judulBuku,
                    onValueChange = { judulBuku = it }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Input Nama Penulis
                AddScreenInputField(
                    label = "Nama Penulis",
                    placeholder = "Siapa penulisnya?",
                    value = namaPenulis,
                    onValueChange = { namaPenulis = it }
                )

                Spacer(modifier = Modifier.height(28.dp))

                // Tombol Simpan ke Wishlist
                AddScreenWishlistButton(
                    onClick = {
                        // Handle simpan
                    }
                )

                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}


@Composable
fun AddScreenTopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(White)
            .padding(horizontal = 20.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
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
                Text(
                    text = "M",
                    color = White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Text(
                text = "My Books",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = TextPrimary
            )
        }

        Icon(
            imageVector = Icons.Outlined.Notifications,
            contentDescription = "Notifikasi",
            tint = TextPrimary,
            modifier = Modifier.size(24.dp)
        )
    }
}


@Composable
fun AddScreenUploadCover() {
    Column {
        Text(
            text = "Cover Buku (Opsional)",
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            color = AccentBlue
        )

        Spacer(modifier = Modifier.height(10.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(White)
                .border(
                    width = 1.5.dp,
                    color = BorderColor,
                    shape = RoundedCornerShape(16.dp)
                )
                .clickable { /* Handle upload */ },
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .clip(CircleShape)
                        .background(LightBlue),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.AddBox,
                        contentDescription = "Upload",
                        tint = AccentBlue,
                        modifier = Modifier.size(28.dp)
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Upload Cover Buku",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = AccentBlue
                )
            }
        }
    }
}


@Composable
fun AddScreenInputField(
    label: String,
    placeholder: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    Column {
        Text(
            text = label,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = TextPrimary
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = {
                Text(
                    text = placeholder,
                    color = TextSecondary,
                    fontSize = 14.sp
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = AccentBlue,
                unfocusedBorderColor = BorderColor,
                focusedContainerColor = White,
                unfocusedContainerColor = White,
                cursorColor = AccentBlue,
                focusedTextColor = TextPrimary,
                unfocusedTextColor = TextPrimary
            ),
            singleLine = true
        )
    }
}


@Composable
fun AddScreenWishlistButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(54.dp),
        shape = RoundedCornerShape(30.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = PrimaryBlue,
            contentColor = White
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 0.dp
        )
    ) {
        Icon(
            imageVector = Icons.Outlined.Book,
            contentDescription = null,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "Simpan ke Wishlist",
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}


@Composable
fun AddScreenBottomBar(
    selectedTab: Int,
    onTabSelected: (Int) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(White)
    ) {
        Divider(
            color = BorderColor,
            thickness = 0.5.dp,
            modifier = Modifier.align(Alignment.TopCenter)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {

            AddScreenNavItem(
                label = "Home",
                icon = Icons.Outlined.Home,
                isSelected = selectedTab == 0,
                onClick = { onTabSelected(0) }
            )

            // Tab Add (tengah, bulat biru)
            AddScreenNavCenter(
                isSelected = selectedTab == 1,
                onClick = { onTabSelected(1) }
            )

            // Tab Library
            AddScreenNavItem(
                label = "Library",
                icon = Icons.Outlined.Book,
                isSelected = selectedTab == 2,
                onClick = { onTabSelected(2) }
            )
        }
    }
}

@Composable
fun AddScreenNavItem(
    label: String,
    icon: ImageVector,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val tintColor = if (isSelected) AccentBlue else TextSecondary

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(horizontal = 24.dp, vertical = 4.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = tintColor,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.height(3.dp))
        Text(
            text = label,
            fontSize = 11.sp,
            color = tintColor,
            fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal
        )
    }
}

@Composable
fun AddScreenNavCenter(
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable(onClick = onClick)
    ) {
        Box(
            modifier = Modifier
                .size(52.dp)
                .clip(CircleShape)
                .background(PrimaryBlue),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add",
                tint = White,
                modifier = Modifier.size(28.dp)
            )
        }
        Spacer(modifier = Modifier.height(3.dp))
        Text(
            text = "Add",
            fontSize = 11.sp,
            color = if (isSelected) AccentBlue else TextSecondary,
            fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal
        )
    }
}
@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun AddScreenPreview() {
    AddScreen()
}