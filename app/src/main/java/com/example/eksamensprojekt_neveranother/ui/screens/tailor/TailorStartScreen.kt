package com.example.eksamensprojekt_neveranother.ui.screens.tailor

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material3.OutlinedButton
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
import com.example.eksamensprojekt_neveranother.viewmodel.TailorViewModel

// Sebastian
@Composable
fun TailorStartScreen(
    navController: NavController,
    viewModel: TailorViewModel
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
                    .clickable { navController.popBackStack() }
            )

            Image(
                painter = painterResource(R.drawable.homeicon),
                contentDescription = "Home",
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(56.dp)
                    .clickable { navController.navigate(viewModel.routeHome) {
                        popUpTo(viewModel.routeHome) { inclusive = true }
                    } }
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
            text = viewModel.startTitle,
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
            viewModel.requirements.forEach { text ->
                Text(
                    text = text,
                    fontSize = 22.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // ===== Forsætknap =====
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 32.dp),
            horizontalArrangement = Arrangement.Center
        ) {

            Button(
                onClick = { navController.navigate(viewModel.routeToMeasurements) },
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
        }


        

    }
}
