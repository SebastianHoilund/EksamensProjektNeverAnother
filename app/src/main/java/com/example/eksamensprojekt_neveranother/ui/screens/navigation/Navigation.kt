package com.example.eksamensprojekt_neveranother.ui.screens.navigation

import android.R.color.black
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.eksamensprojekt_neveranother.R

@Composable
fun BottomNavBar(onTabClick: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .clickable { onTabClick("home-screen") } // så der både bliver klikket på icon og tekst
                .padding(8.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.homeicon),
                contentDescription = "Home",
                tint = Color.Black,
            )

        }
    }
}
/*
        // 1. HOME (Hjerte)
        IconButton(onClick = { onTabClick("home-screen") }) {
            Icon(
                contentDescription = "Home",
                tint = black,
                modifier = Modifier.size(36.dp)
            )
        }

        // 3. COMMUNITY (Globus)
        IconButton(onClick = { onTabClick("screen-3") }) {
            Icon(
                imageVector = Icons.Outlined.Public,
                contentDescription = "Community",
                tint = black,
                modifier = Modifier.size(36.dp)
            )
        }

        // 4. PROFILE (Person)
        IconButton(onClick = { onTabClick("screen-4") }) {
            Icon(
                imageVector = Icons.Outlined.Person,
                contentDescription = "Profile",
                tint = black,
                modifier = Modifier.size(36.dp)
            )
        }
    }
}

 */