package com.example.moviemaniac.feature.homeScreen.presentation.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DisclaimerScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.background,
                        Color(0xFF2C0000)
                    )
                )
            )
            .padding(horizontal = 24.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 40.dp, bottom = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(16.dp))

            // Main headline
            Text(
                text = "Watch your favorite shows and movies for\nfree with no ads ever! (っ˘ω˘ς )",
                color = Color(0xFFE57373),
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                lineHeight = 26.sp,
                modifier = Modifier.alpha(0.9f)
            )

            Spacer(modifier = Modifier.height(40.dp))

            // Disclaimer title
            Text(
                text = "Disclaimer  ~(˘▾˘~)",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Disclaimer body
            Text(
                text = "Please note: Movie-Maniac does not host any files itself " +
                        "but instead only display's content from 3rd party providers. " +
                        "Legal issues should be taken up with them.",
                color = Color(0xFFEF9A9A),
                fontSize = 14.sp,
                lineHeight = 22.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            Spacer(modifier = Modifier.weight(1f))

            // Footer actions
            FooterRow(modifier = modifier)
        }

        // Floating scroll-to-top button (optional)
//        FloatingActionButton(
//            onClick = { /* scroll to top */ },
//            shape = CircleShape,
//            containerColor = Color(0xFF1A1F3C),
//            modifier = Modifier
//                .align(Alignment.BottomStart)
//                .padding(8.dp)
//        ) {
//            Text("⌃", color = Color.White, fontSize = 20.sp)
//        }
    }
}

@Composable
private fun FooterRow(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        FooterItem("GitHub")
        FooterItem("Discord")

        Row {
            FooterItem("Support us")
            Spacer(modifier = Modifier.width(12.dp))
            FooterItem("Legal / DMCA")
        }
    }
}

@Composable
private fun FooterItem(text: String) {
    Text(
        text = text,
        color = Color(0xFFEF9A9A),
        fontSize = 13.sp,
        modifier = Modifier.alpha(0.9f)
    )
}
