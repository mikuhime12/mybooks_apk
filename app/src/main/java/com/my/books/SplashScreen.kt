package com.my.books
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onTimeout: () -> Unit) {

    LaunchedEffect(Unit) {
        delay(4000)
        onTimeout()
    }
    val infiniteTransition = rememberInfiniteTransition(label="SplashTransition")


    val bookScale by infiniteTransition.animateFloat(
        initialValue = 0.95f,
        targetValue = 1.05f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),

        label="BookScale"
    )



    val textAlpha by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 1.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),

        label="TextAlpha"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F9FC))
    ) {

        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                modifier = Modifier
                    .size(120.dp)
                    .graphicsLayer(
                        scaleX = bookScale,
                        scaleY = bookScale
                    )
                    .shadow(8.dp, shape = RoundedCornerShape(24.dp))
                    .clip(RoundedCornerShape(24.dp))
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.books),
                    contentDescription = "Book Icon",
                    modifier = Modifier
                        .size(125.dp),
                )
            }


            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text="My Books",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF0D3E9B),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text="Wishlist Buku Impianmu",
                fontSize = 14.sp,
                color = Color(0xFF7A8B9E),
                textAlign = TextAlign.Center
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 50.dp, start = 48.dp, end = 48.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .clip(RoundedCornerShape(3.dp)),
                color = Color(0xFF00C4FF),
                trackColor = Color(0xFFE0E8F5)
            )

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text="MENYIAPKAN RAK BUKU...",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 2.sp,
                color = Color(0xFF8A99A8),
                textAlign = TextAlign.Center
            )
        }
    }
}