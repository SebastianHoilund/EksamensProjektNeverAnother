package com.example.eksamensprojekt_neveranother.ui.screens.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import com.example.eksamensprojekt_neveranother.R
import com.example.eksamensprojekt_neveranother.ui.theme.backgroundColor
import com.example.eksamensprojekt_neveranother.ui.theme.blackColor
import com.example.eksamensprojekt_neveranother.ui.theme.greyColor
import com.example.eksamensprojekt_neveranother.ui.theme.whiteColor

@Composable
fun BottomNavBar(onTabClick: (String) -> Unit) {

    //Denne overordnede Column sørger for, at ALT i bunden bliver 100% hvidt helt ud til kanterne
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(whiteColor) // Sætter hvid baggrund på HELE bunden fra kant til kant
    ) {

    HorizontalDivider(
        thickness = 1.dp,
        color = greyColor
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 3.dp, horizontal = 16.dp)
            .background(whiteColor),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        //HOME
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .clickable { onTabClick("home-screen") } // så der både bliver klikket på icon og tekst
                .padding(start = 35.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.homeicon),
                contentDescription = "Home",
                tint = Color.Black
            )
            Text(
                text = "Hjem",
                color = greyColor
            )
        }
        //BASKET SCREEN
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .clickable { onTabClick("basket-screen") } // så der både bliver klikket på icon og tekst
                .padding(8.dp)
        ) {
            Icon(
                //imageVector = Icons.Filled.ShoppingCart,
                painter = painterResource(id = R.drawable.basketicon),
                contentDescription = "Basket",
                modifier = Modifier
                    .size(28.dp)
            )
            Text(
                text = "Kurv",
                color = greyColor
            )
        }
        //PROFIL SCREEN
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .clickable { onTabClick("profil-screen") } // så der både bliver klikket på icon og tekst
                .padding(end = 35.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.profilicon),
                contentDescription = "Profil",
                modifier = Modifier
                    .size(26.dp)

            )
            Text(
                text = "Profil",
                color = greyColor
            )
        }
    }
    }
}
