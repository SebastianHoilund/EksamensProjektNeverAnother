package com.example.eksamensprojekt_neveranother.ui.screens.basket

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.eksamensprojekt_neveranother.R
import com.example.eksamensprojekt_neveranother.ui.theme.backgroundColor
import com.example.eksamensprojekt_neveranother.ui.theme.ctaColor
import com.example.eksamensprojekt_neveranother.ui.theme.whiteColor
import com.example.eksamensprojekt_neveranother.viewmodel.CartViewModel


// ===== BASKET SCREEN =====
@Composable
fun BasketScreen (
    navController: NavController,
    viewModel: CartViewModel,
    isTailored: Boolean // Modtager isTailored for at styre knapteksten
) {


    // Knap-logik - 3 tilstande:
// Kurv ikke tom → "Check ud" → checkout
// Kurv tom + tailored → "Se din BH" → produkt
// Kurv tom + ikke tailored → "Skræddersy BH" → measurement start
    val btnText = when {
        viewModel.items.isNotEmpty() -> "Check ud"
        isTailored -> "Se din BH"
        else -> "Skræddersy BH"
    }


    //Knap navigation
    val btnNavigation = when {
        viewModel.items.isNotEmpty() -> "checkout"
        isTailored -> "product"
        else -> "tailor_start"
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .navigationBarsPadding()
            .padding(bottom = 70.dp)
    ) {

        Spacer(modifier = Modifier.height(64.dp))


        // Logo og "Din kurv" titel vises altid øverst
        Image(painter = painterResource(id = R.drawable.neveranother_a_logo),
            contentDescription = "NeverAnother A logo",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .width(180.dp)
                .height(50.dp),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "Din kurv",
            fontSize = 38.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )

        Box(
            modifier = Modifier
                .width(60.dp)
                .height(2.dp)
                .background(ctaColor)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(35.dp))


        // Tom kurv: viser basketicon, "0" og en tekst
        if (viewModel.items.isEmpty()) {

            Column(horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                Spacer(modifier = Modifier.height(50.dp))

                Image(painter = painterResource(id = R.drawable.basketicon),
                    contentDescription = "Basket icon",
                    modifier = Modifier
                        .size(60.dp)
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(text = "0", fontSize = 20.sp, color = Color.Gray)
            }

            Spacer(modifier = Modifier.height(90.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Det ser ud til at din kurv er tom",
                    color = Color.Gray,
                    fontSize = 20.sp
                )
            }

        } else { // Fyldt kurv: LazyColumn med liste af varer
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                items(viewModel.items) { // Hvert produkt har billede, navn, farve, pris og slet-knap
                    item ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        Image(
                            painter = painterResource(id = item.billedeRes),
                            contentDescription = "billede af model med BH på",
                            modifier = Modifier
                                .width(80.dp)
                                .height(120.dp),
                            contentScale = ContentScale.Crop
                            )

                        Spacer(modifier = Modifier.width(6.dp))

                        Column(
                            modifier = Modifier
                                .weight(1f)
                        ) {
                            Text(text = item.navn, fontWeight = FontWeight.Bold, fontSize = 24.sp)
                            Spacer(modifier = Modifier.height(12.dp))
                            Text(text = item.farve, color = Color.Gray)
                        }

                        Column(horizontalAlignment = Alignment.End)
                        {
                            Image(painter = painterResource(id = R.drawable.trashcan_logo),
                                contentDescription = "Skraldespand logo til at slette vare",
                                modifier = Modifier
                                    .size(30.dp)
                                    .clickable { viewModel.deleteItem(item)
                                    }
                                )

                            Spacer(modifier = Modifier.height(52.dp))

                            Text(text = "${item.pris} kr.",
                                color = Color.Gray,
                                fontSize = 20.sp
                                )
                        }

                    }// row inde i else

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Box(
                            modifier = Modifier // Orange streg under hvert produkt i listen
                                .width(250.dp)
                                .height(2.dp)
                                .background(ctaColor)
                        )
                    }

                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

// Knappen er altid synlig i bunden
        Button(
            onClick = {navController.navigate(btnNavigation)},
            colors = ButtonDefaults.buttonColors(containerColor = ctaColor),
            shape = RoundedCornerShape(50),
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .height(50.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = btnText,
                color = whiteColor,
                fontSize = 20.sp
            )
        }


    } //Column
}