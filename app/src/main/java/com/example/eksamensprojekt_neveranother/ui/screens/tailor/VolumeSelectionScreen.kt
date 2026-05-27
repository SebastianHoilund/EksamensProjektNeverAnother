package com.example.eksamensprojekt_neveranother.ui.screens.tailor

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.eksamensprojekt_neveranother.R

import com.example.eksamensprojekt_neveranother.viewmodel.MeasurementViewModel

@Composable
fun VolumeSelectionScreen(
    navController: NavController,
    viewModel: MeasurementViewModel
) {
    var selectedOption by remember { mutableStateOf(viewModel.measurement.selectedVolume) }
    
    data class VolumeOption(val title: String, val imageResId: Int)

    val options = listOf(
        VolumeOption("Fast top volumen", R.drawable.fasttop), 
        VolumeOption("Blød top volumen", R.drawable.blodtop),
        VolumeOption("Fast bund volumen", R.drawable.fastbund),
        VolumeOption("Blød bund volumen", R.drawable.blodbund)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFAF9F1))
            .statusBarsPadding()
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
                    .clickable { navController.popBackStack("tailor_start", inclusive = false) }
            )

            Image(
                painter = painterResource(R.drawable.homeicon),
                contentDescription = "Home",
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(56.dp)
            )

            Image(
                painter = painterResource(R.drawable.progressindicatorstep5),
                contentDescription = "Progress",
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .size(56.dp)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Hvilken type volumen passer bedst til dig?",
                fontSize = 28.sp,
                lineHeight = 34.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 32.dp)
            )

            HorizontalDivider(
                thickness = 2.dp,
                color = Color(0xFFFE5F00),
                modifier = Modifier
                    .width(60.dp)
                    .padding(top = 12.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            options.forEachIndexed { index, option ->
                VolumeOptionItem(
                    title = option.title,
                    isSelected = selectedOption == index,
                    onClick = { selectedOption = index },
                    imageResId = option.imageResId
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
            
            // Bottom Buttons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp, vertical = 32.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                OutlinedButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier
                        .weight(1f)
                        .height(56.dp),
                    shape = RoundedCornerShape(28.dp),
                    border = BorderStroke(1.dp, Color(0xFFFE5F00)),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFFFE5F00))
                ) {
                    Text(text = "Tilbage", fontSize = 18.sp)
                }

                Button(
                    onClick = { 
                        if (selectedOption != -1) {
                            viewModel.updateVolume(selectedOption)
                            navController.navigate("result_loading")
                        }
                    },
                    modifier = Modifier
                        .weight(1.5f)
                        .height(56.dp),
                    shape = RoundedCornerShape(28.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedOption != -1) Color(0xFFFE5F00) else Color.LightGray,
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "Fortsæt", fontSize = 18.sp)
                }
            }
        }
    }
}

@Composable
fun VolumeOptionItem(
    title: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    imageResId: Int
) {
    Column(
        modifier = Modifier
            .width(300.dp)
            .clickable { onClick() }
    ) {
        Text(
            text = title,
            fontSize = 18.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .background(Color.White)
                .border(
                    width = if (isSelected) 2.dp else 1.dp,
                    color = if (isSelected) Color(0xFFFE5F00) else Color.LightGray,
                    shape = RoundedCornerShape(4.dp)
                )
        ) {
            Image(
                painter = painterResource(imageResId),
                contentDescription = title,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            )

            Box(
                modifier = Modifier
                    .padding(12.dp)
                    .size(24.dp)
                    .align(Alignment.BottomEnd)
                    .background(
                        color = if (isSelected) Color(0xFFFE5F00) else Color.White,
                        shape = CircleShape
                    )
                    .border(1.dp, if (isSelected) Color.White else Color.LightGray, CircleShape)
            ) {
                if (isSelected) {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .align(Alignment.Center)
                            .background(Color.White, CircleShape)
                    )
                }
            }
        }
    }
}
