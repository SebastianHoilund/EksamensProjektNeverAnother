package com.example.eksamensprojekt_neveranother.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eksamensprojekt_neveranother.R

// ===== MEASUREMENT TEMPLATE - En genanvendelig komponent =====
// Dette er et klassisk eksempel på "Component-Driven UI".
// I stedet for at skrive koden til alle 4 målingsskærme forfra, har jeg lavet denne skabelon.
// Den tager parametre (title, description, video osv.) og funktioner (onNextClick, onBackClick),
// hvilket gør den ekstremt fleksibel og nemt at maintain.
@Composable
fun MeasurementTemplate(
    title: String,
    description: String,
    videoResId: Int,
    illustrationResId: Int,
    progressResId: Int,
    initialValue: String = "0.00",
    
    // Callback-funktioner: Disse gør det muligt for komponenten at sende data 
    // tilbage til den skærm, der bruger den.
    onBackClick: () -> Unit,
    onNextClick: (String) -> Unit,
    onExitClick: () -> Unit = {},
    onHomeClick: () -> Unit = {}
) {
    // ===== LOKAL STATE =====
    // Disse variabler findes kun inde i denne komponent.
    // showIllustration styrer, om vi ser tegningen eller videoen.
    var showIllustration by remember { mutableStateOf(true) }
    // measurementValue holder styr på, hvad brugeren har skrevet i tekstfeltet lige nu.
    var measurementValue by remember { mutableStateOf(initialValue) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFAF9F1))
            .statusBarsPadding() // Sikrer at indholdet ikke ligger bag statusbaren øverst.
            .verticalScroll(rememberScrollState()), // Gør det muligt at scrolle på små skærme.
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // TOP BAR med Exit, Home og Progress
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
                    .clickable { onExitClick() }
            )

            Image(
                painter = painterResource(R.drawable.homeicon),
                contentDescription = "Home",
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(56.dp)
                    .clickable { onHomeClick() }
            )

            Image(
                painter = painterResource(progressResId),
                contentDescription = "Progress Indicator",
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .size(56.dp)
            )
        }

        Text(
            text = title,
            fontSize = 30.sp,
            modifier = Modifier.padding(top = 24.dp, bottom = 16.dp)
        )

        HorizontalDivider(
            thickness = 2.dp,
            color = Color(0xFFFE5F00),
            modifier = Modifier.width(80.dp)
        )

        Text(
            text = description,
            fontSize = 22.sp,
            lineHeight = 32.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(horizontal = 32.dp, vertical = 16.dp)
                .width(300.dp)
        )

        // ===== VIDEO & ILLUSTRATION BOX =====
        Box(
            modifier = Modifier
                .width(300.dp)
                .height(450.dp)
                .clip(RoundedCornerShape(15.dp))
        ) {
            // VideoPlayer er en anden custom komponent.
            // Vi bruger .blur() effekten, hvis illustrationen vises ovenpå.
            VideoPlayer(
                videoResId = videoResId,
                modifier = Modifier
                    .fillMaxSize()
                    .blur(if (showIllustration) 100.dp else 0.dp)
            )

            if (showIllustration) {
                Box(modifier = Modifier.fillMaxSize())
                Image(
                    modifier = Modifier.align(Alignment.Center),
                    painter = painterResource(illustrationResId),
                    contentDescription = "Illustration"
                )
            }

            // Knap til at skifte mellem video og illustration.
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextButton(
                    onClick = { showIllustration = !showIllustration },
                    colors = ButtonDefaults.textButtonColors(contentColor = Color.White)
                ) {
                    Text(
                        if (showIllustration) "Se video" else "Se illustration",
                        fontSize = 20.sp
                    )
                }
                HorizontalDivider(thickness = 2.dp, color = Color(0xFFFE5F00), modifier = Modifier.width(64.dp).align(Alignment.CenterHorizontally))
            }
        }

        // ===== INPUT FELT =====
        Column(
            modifier = Modifier
                .padding(top = 28.dp)
                .width(200.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                verticalAlignment = Alignment.Bottom
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    // Placeholder tekst (0.00), hvis feltet er tomt.
                    if (measurementValue.isEmpty()) {
                        Text(
                            text = "0.00",
                            fontSize = 28.sp,
                            color = Color.LightGray,
                            textAlign = TextAlign.Start
                        )
                    }
                    // Det faktiske inputfelt.
                    BasicTextField(
                        value = measurementValue,
                        onValueChange = { measurementValue = it },
                        textStyle = TextStyle(
                            fontSize = 28.sp,
                            color = Color.Gray,
                            textAlign = TextAlign.Start
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), // kun tal kan skrives
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Text(
                    text = "cm",
                    fontSize = 28.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
            }
            HorizontalDivider(
                thickness = 2.dp,
                color = Color(0xFFFE5F00),
                modifier = Modifier.fillMaxWidth().padding(top = 4.dp)
            )
        }

        // ===== NAVIGATIONS KNAPPER (Bund) =====
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 32.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedButton(
                onClick = { onBackClick() },
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
                onClick = { onNextClick(measurementValue) }, // Sender den indtastede værdi videre.
                modifier = Modifier.weight(1.5f).height(56.dp),
                shape = RoundedCornerShape(28.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFE5F00), contentColor = Color.White)
            ) {
                Text(text = "Fortsæt", fontSize = 18.sp)
            }
        }
    }
}
