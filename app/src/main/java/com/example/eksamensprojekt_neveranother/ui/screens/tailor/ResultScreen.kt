package com.example.eksamensprojekt_neveranother.ui.screens.tailor

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.eksamensprojekt_neveranother.R
import com.example.eksamensprojekt_neveranother.data.TailorState

import com.example.eksamensprojekt_neveranother.viewmodel.MeasurementViewModel

@Composable
fun ResultScreen(
    navController: NavController,
    viewModel: MeasurementViewModel,
    onSeeProduct: () -> Unit = {}
) {
    val state = viewModel.tailorState
    val volumeOptions = listOf(
        "fast fylde i toppen",
        "blød fylde i toppen",
        "fast fylde i bunden",
        "blød fylde i bunden"
    )

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
                    .clickable { navController.popBackStack("tailor_start", inclusive = false) }
            )

            Image(
                painter = painterResource(R.drawable.homeicon),
                contentDescription = "Home",
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(56.dp)
                    .clickable { navController.navigate("home") {
                        popUpTo("home") { inclusive = true }
                    } }
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = buildAnnotatedString {
                append("Her er dit ")
                withStyle(style = SpanStyle(color = Color(0xFFFE5F00))) {
                    append("resultat")
                }
            },
            fontSize = 32.sp,
            textAlign = TextAlign.Center
        )

        HorizontalDivider(
            thickness = 2.dp,
            color = Color(0xFFFE5F00),
            modifier = Modifier
                .width(60.dp)
                .padding(top = 12.dp)
        )

        Spacer(modifier = Modifier.height(48.dp))

        // Results List
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 48.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            ResultItem(label = "Øvre omkreds", value = "${state.upperCircumference} cm")
            ResultItem(label = "Nedre omkreds", value = "${state.lowerCircumference} cm")
            ResultItem(label = "Brysthøjde", value = "${state.height} cm")
            ResultItem(label = "Brystspænd", value = "${state.width} cm")
            ResultItem(
                label = "Volumn",
                value = if (state.selectedVolume != -1) volumeOptions[state.selectedVolume] else "-"
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        // Bottom Buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedButton(
                onClick = {
                    navController.navigate("tailor_start") {
                        popUpTo("tailor_start") { inclusive = true }
                    }
                },
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp),
                shape = RoundedCornerShape(28.dp),
                border = BorderStroke(1.dp, Color(0xFFFE5F00)),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFFFE5F00))
            ) {
                Text(text = "Forfra", fontSize = 18.sp)
            }

            Button(
                onClick = onSeeProduct,
                modifier = Modifier
                    .weight(1.5f)
                    .height(56.dp),
                shape = RoundedCornerShape(28.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFE5F00),
                    contentColor = Color.White
                )
            ) {
                Text(text = "Se produkt", fontSize = 18.sp)
            }
        }
    }
}

@Composable
fun ResultItem(label: String, value: String) {
    Column {
        Text(text = label, fontSize = 20.sp, color = Color.Black)
        Text(text = value, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.Black)
    }
}
