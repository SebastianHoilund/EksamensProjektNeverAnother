package com.example.eksamensprojekt_neveranother.ui.screens.tailor

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.eksamensprojekt_neveranother.R

@Composable
fun TailorStartScreen(
    navController: NavController
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFAF9F1))
            .statusBarsPadding()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.exit),
                contentDescription = "Exit",
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .size(32.dp)
                    .clickable { /*onExitClick()*/ }
            )

            Image(
                painter = painterResource(R.drawable.homeicon),
                contentDescription = "Home",
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(56.dp)
                    .clickable { /*onHomeClick()*/ }
            )

            Image(
                painter = painterResource(R.drawable.progressindicatorstep0),
                contentDescription = "Progress",
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .size(56.dp)
            )
        }

        Spacer(modifier = Modifier.height(140.dp))

        Text(
            text = "Lad os komme i gang!",
            fontSize = 32.sp,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 32.dp, vertical = 8.dp)
        )

        HorizontalDivider(
            thickness = 2.dp,
            color = Color(0xFFFE5F00),
            modifier = Modifier
                .width(60.dp)
                .padding(top = 12.dp)
        )

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "Du skal bruge to ting:",
            fontSize = 22.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(32.dp))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "1. Et blødt målebånd",
                fontSize = 22.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 12.dp)
            )
            Text(
                text = "2. Din bedste passende BH",
                fontSize = 22.sp,
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = { navController.navigate("upper_measurements") },
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .height(56.dp),
            shape = RoundedCornerShape(28.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFE5F00),
                contentColor = Color.White
            )
        ) {
            Text(text = "Fortsæt", fontSize = 18.sp)
        }

        Text(
            text = "Jeg har ikke et målebånd",
            fontSize = 14.sp,
            color = Color.Gray,
            textDecoration = TextDecoration.Underline,
            modifier = Modifier
                .padding(top = 16.dp, bottom = 32.dp)
                .clickable { /* TODO */ }
        )
    }
}
