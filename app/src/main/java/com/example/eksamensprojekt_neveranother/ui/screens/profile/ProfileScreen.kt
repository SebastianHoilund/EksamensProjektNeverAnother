package com.example.eksamensprojekt_neveranother.ui.screens.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eksamensprojekt_neveranother.R
import com.example.eksamensprojekt_neveranother.ui.theme.backgroundColor
import com.example.eksamensprojekt_neveranother.ui.theme.blackColor
import com.example.eksamensprojekt_neveranother.viewmodel.ProfileViewModel

// ===== PROFILE SCREEN =====

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel,
    goToHome: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(bottom = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // TOP BAR
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 48.dp, start = 20.dp, end = 20.dp)
        ) {
            Icon(
                painterResource(id = R.drawable.homeicon),
                contentDescription = "Logo - gå til forside",
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.Center)
                    .clickable { goToHome() }
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
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 24.dp)
        )

        // Bruger data fra ViewModel (userName)
        Text(
            text = viewModel.userName,
            fontSize = 38.sp,
            color = blackColor,
            modifier = Modifier.padding(top = 8.dp)
        )

        Spacer(modifier = Modifier.height(82.dp))

        // Vi looper gennem menuItems fra ViewModel
        viewModel.menuItems.forEach { item ->
            ProfileMenuItem(item)
        }

        // Knapper kalder nu funktioner i ViewModel
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(bottom = 24.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.Bottom),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            CustomProfileButton("Vælg sprog", onClick = { viewModel.onLanguageSelect() })
            CustomProfileButton("Tilmeld nyhedsbrev", onClick = { viewModel.onNewsletterSignup() })
            CustomProfileButton("Kontakt os", onClick = { viewModel.onContactUs() })
        }
    }
}

@Composable
fun ProfileMenuItem(itemText: String) {
    Column(
        modifier = Modifier
            .width(250.dp)
            .padding(vertical = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = itemText,
            fontSize = 20.sp,
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
    OutlinedButton(
        onClick = onClick,
        colors = ButtonDefaults.outlinedButtonColors(contentColor = blackColor),
        modifier = Modifier
            .width(250.dp)
            .height(48.dp)
    ) {
        Text(
            text = itemText,
            fontSize = 18.sp
        )
    }
}