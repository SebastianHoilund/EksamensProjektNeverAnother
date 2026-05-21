package com.example.eksamensprojekt_neveranother.ui.screens.profile

import android.R.attr.text
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eksamensprojekt_neveranother.R
import com.example.eksamensprojekt_neveranother.ui.theme.backgroundColor
import com.example.eksamensprojekt_neveranother.ui.theme.blackColor
import com.example.eksamensprojekt_neveranother.ui.theme.greyColor
import com.example.eksamensprojekt_neveranother.ui.theme.whiteColor

//herinde opretter jeg min funktion og giver den parametre som jeg sender under AppNavigaiton

@Composable
fun ProfileMenuItem(itemText: String) {
    Column(
        modifier = Modifier
            .width(210.dp) // Sætter en fast bredde på linjen, så den ligner Figma
            .padding(vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = itemText,
            fontSize = 22.sp,
            color = blackColor,
            modifier = Modifier.padding(bottom = 2.dp)
        )
        HorizontalDivider(
            thickness = 1.dp,
            color = blackColor
        )
    }
}
@Composable
fun CustomProfileButton(itemText: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .width(205.dp)
            .height(60.dp)
            .padding(vertical = 10.dp),
        colors = ButtonDefaults.buttonColors
        (
            containerColor = whiteColor, // Knappens baggrund bliver hvid
            contentColor = blackColor    // Teksten bliver sort
        ),
        // Border skal stå her for sig selv – ikke inde i ButtonColors!
        border = androidx.compose.foundation.BorderStroke(1.dp, greyColor)
    ) {
        // Alt indholdet (knappens tekst) skal stå herinde i krølparenteserne til sidst
        Text(
            text = itemText,
            fontSize = 16.sp
        )
    }
}


@Composable
fun ProfileScreen(goToHome: () -> Unit, goToBasket: () -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(bottom = 30.dp, start = 20.dp, end = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 60.dp)
            ) {
                Icon(
                    painterResource(id = R.drawable.homeicon),
                    contentDescription = "Logo - gå til forside",
                    modifier = Modifier
                        .size(53.dp)
                        .align(Alignment.Center)
                        .clickable { goToHome() } // Gør at man kan trykke sig hjem
                )
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Indstillinger",
                    modifier = Modifier
                        .size(30.dp)
                        .align(Alignment.CenterEnd)
                )
            }
            Text(
                text = "Min profil",
                color = blackColor,
                fontSize = 36.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 42.dp, bottom = 20.dp)
            )

            Icon(
                painter = painterResource(id = R.drawable.profilicon),
                contentDescription = "Profil",
                modifier = Modifier
                    .size(90.dp)
            )
            Text(
                text = "Lisbeth",
                fontSize = 24.sp,
                color = blackColor,
                modifier = Modifier
                    .padding(top = 15.dp, bottom = 45.dp)
            )

            //kalder den genanvendelige funktio
            ProfileMenuItem("Mine mål")
            ProfileMenuItem("Ordre")
            ProfileMenuItem("Gavekort")

        //Spacer der skubber knapperne godt ned mod bunden
        Spacer(
            modifier = Modifier
                .weight(1f)

        )

            CustomProfileButton("Vælg sprog", onClick = {})
            CustomProfileButton("Tilmeld nyhedsbrev", onClick = {})
            CustomProfileButton("Kontakt os", onClick = {})
        }
    }

