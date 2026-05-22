package com.example.eksamensprojekt_neveranother.ui.screens.tailor

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.eksamensprojekt_neveranother.R
import kotlinx.coroutines.delay

@Composable
fun ResultLoadingScreen(navController: NavController) {
    var progress by remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        for (i in 0..10) {
            progress = i * 10
            if (i < 10) delay(150)
        }
        delay(300)
        navController.navigate("result_screen") {
            popUpTo("result_loading") { inclusive = true }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFAF9F1)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.braloading),
            contentDescription = "Loading Illustration",
            modifier = Modifier.size(240.dp)
        )

        Spacer(modifier = Modifier.height(48.dp))

        Text(
            text = progress.toString(),
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF333333)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Resultatet er klar",
            fontSize = 20.sp,
            color = Color.Gray
        )
    }
}
