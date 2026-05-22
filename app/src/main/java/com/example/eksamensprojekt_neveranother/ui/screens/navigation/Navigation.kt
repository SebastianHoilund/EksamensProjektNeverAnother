package com.example.eksamensprojekt_neveranother.ui.screens.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eksamensprojekt_neveranother.R
import com.example.eksamensprojekt_neveranother.ui.theme.backgroundColor
import com.example.eksamensprojekt_neveranother.ui.theme.blackColor
import com.example.eksamensprojekt_neveranother.ui.theme.ctaColor
import com.example.eksamensprojekt_neveranother.ui.theme.greyColor
import com.example.eksamensprojekt_neveranother.ui.theme.whiteColor

@Composable
fun OnCurrentScreen(modifier: Modifier = Modifier){
        HorizontalDivider(
            thickness = 4.dp,
            color = ctaColor,
            modifier = modifier
        )
}

@Composable
fun BottomNavBar(currentScreen: String, onTabClick: (String) -> Unit) {

    //Denne overordnede Column sørger for, at ALT i bunden bliver 100% hvidt helt ud til kanterne
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(whiteColor) // Sætter hvid baggrund på HELE bunden fra kant til kant
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {


            HorizontalDivider(
                thickness = 1.dp,
                color = greyColor
            )

            OnCurrentScreen(
                modifier = when (currentScreen) {
                    "home-screen" -> Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.Start)
                        .padding(start = 42.dp)
                        .width(60.dp)

                    "basket-screen" -> Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally)
                        .width(60.dp)

                    "profil-screen" -> Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.End)
                        .padding(end = 42.dp)
                        .width(60.dp)

                    else -> Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.Start)
                        .padding(start = 42.dp)
                        .width(60.dp)
                }
            )
        }



            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 10.dp, start = 55.dp, end = 55.dp)
                    .background(whiteColor),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                //HOME
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .clickable { onTabClick("home-screen") } // så der både bliver klikket på icon og tekst
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.homeicon),
                        contentDescription = "Home",
                        tint = Color.Black,
                        modifier = Modifier
                            .size(34.dp)
                    )
                    Text(
                        text = "Hjem",
                        color = greyColor,
                        fontSize = 13.sp
                    )
                }
                //BASKET SCREEN
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .clickable { onTabClick("basket-screen") } // så der både bliver klikket på icon og tekst
                ) {

                    Icon(
                        //imageVector = Icons.Filled.ShoppingCart,
                        painter = painterResource(id = R.drawable.basketicon),
                        contentDescription = "Basket",
                        modifier = Modifier
                            .size(34.dp)
                    )
                    Text(
                        text = "Kurv",
                        color = greyColor,
                        fontSize = 13.sp
                    )
                }
                //PROFIL SCREEN
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .clickable { onTabClick("profil-screen") } // så der både bliver klikket på icon og tekst
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.profilicon),
                        contentDescription = "Profil",
                        modifier = Modifier
                            .size(34.dp)
                    )
                    Text(
                        text = "Profil",
                        color = greyColor,
                        fontSize = 13.sp
                    )
                }
            }
        }

}
