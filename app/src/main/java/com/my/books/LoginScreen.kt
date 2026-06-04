package com.my.books

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material3.Text
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import androidx.compose.material3.Icon
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.material3.Card
import androidx.compose.ui.draw.shadow
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.foundation.layout.Row
import androidx.compose.ui.Alignment
import androidx.compose.foundation.clickable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun LoginScreen() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("")}
    var passwordVisible by remember { mutableStateOf(false)}

    val primaryColor = Color(0xFF002B9A)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F9FC))
            .statusBarsPadding()
            .navigationBarsPadding()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Spacer(modifier = Modifier.height(16.dp))


        Text(
            text="My Books",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = primaryColor
        )

        Spacer(modifier = Modifier.height(40.dp) )

        Box (
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(Color(0xFFE2E7FF)),
            contentAlignment = Alignment.Center
        ){
            Icon(
               painter = painterResource(id = R.drawable.books),
                contentDescription = "Logo Buku",
                tint = Color.Unspecified,
                modifier = Modifier.size(80.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Selamat Datang Di My Books",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1E293B),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Masuk untuk mengelola wishlist buku impianmu", fontSize = 16.sp, color = Color(0xFF64748B), textAlign = TextAlign.Center, modifier = Modifier.padding(horizontal = 16.dp))

        Spacer(modifier = Modifier.height(32.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(elevation = 12.dp, shape = RoundedCornerShape(20.dp), ambientColor = Color.LightGray),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ){
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                Text("Email", fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF1E293B))


                Spacer(modifier = Modifier.height(6.dp))

                OutlinedTextField(
                    value = email,
                    onValueChange = {email = it},
                    placeholder =  {Text("nama@gmail.com", color= Color.LightGray)},
                    leadingIcon = { Icon(painter = painterResource(id = R.drawable.email), contentDescription = null, tint = Color.Gray)},
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = Color(0xFFE2E8F0),
                        focusedBorderColor = primaryColor
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))


                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text("Kata Sandi", fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF1E293B))
                    Text(
                        text = "Lupa Kata Sandi?",
                        fontSize = 11.sp,
                        color = primaryColor,
                        fontWeight =FontWeight.SemiBold,
                        modifier = Modifier.clickable {}
                    )
                }

                Spacer(modifier = Modifier.height(6.dp))

                OutlinedTextField(
                    value = password,
                    onValueChange = {password = it},
                    placeholder = {Text("........", color = Color.LightGray)},
                    leadingIcon = { Icon(painter = painterResource(id = R.drawable.key), contentDescription = null, tint = Color.Gray)},
                    trailingIcon = {
                        val icon = if (passwordVisible) R.drawable.eye else R.drawable.eye_off
                    },
                    visualTransformation = if(passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = Color(0xFFE2E8F0),
                        focusedBorderColor = primaryColor
                    )
                )

            }





        }





    }

}