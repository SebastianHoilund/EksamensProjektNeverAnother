package com.example.eksamensprojekt_neveranother.ui.screens.product

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
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
import com.example.eksamensprojekt_neveranother.ui.theme.blackColor
import com.example.eksamensprojekt_neveranother.ui.theme.ctaColor
import com.example.eksamensprojekt_neveranother.ui.theme.whiteColor
import com.example.eksamensprojekt_neveranother.viewmodel.CartViewModel
import com.example.eksamensprojekt_neveranother.viewmodel.ProductViewModel


// ===== PRODUCT SCREEN =====
// Denne skærm viser detaljer om OneBra™.
// Logikken her er meget afhængig af 'isTailored' tilstanden fra ProductViewModel.
//   - Hvis isTailored er false: Brugeren ser en generisk side og knappen "Skræddersy BH".
//   - Hvis isTailored er true: Brugeren ser deres egne mål og knappen "Føj til kurv".
@Composable
fun ProductScreen (
    navController: NavController,
    viewModel: ProductViewModel,
    cartViewModel: CartViewModel
) {
    // Henter dynamisk tekst til knappen fra ViewModel (separation af UI og logik).
    val btnText = viewModel.getBtnText()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(// LazyColumn med alle sektioner
            modifier = Modifier
                .fillMaxWidth()
                .background(backgroundColor)
        ) {
            item { Header(navController) }
            item { ProductCarousel() }
            item { ColorSelection(viewModel, navController) }
            item { ProductDescription() }
        }

        // CTA KNAP (Fastgjort i bunden)
        // Vi placerer den uden for LazyColumn, men inde i Box, så den altid er synlig (sticky).
        Button(
            onClick = {
                // Vi kalder en funktion i ViewModel, der håndterer navigation og kurv-logik.
                viewModel.onProductAction(
                    cartViewModel = cartViewModel,
                    navigateToBasket = { navController.navigate("basket") },
                    navigateToTailor = { navController.navigate("tailor_start") }
                )
            },
            colors = ButtonDefaults.buttonColors(containerColor = ctaColor),
            shape = RoundedCornerShape(50),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(start = 48.dp, end = 48.dp)
                .padding(bottom = 40.dp)
                .fillMaxWidth(0.6f)
                .height(50.dp)
        ) {
            Text(text = btnText, color = whiteColor, fontSize = 18.sp)
        }
    }
}


// ===== HEADER =====
@Composable
fun Header (navController: NavController) {
    Spacer(modifier = Modifier.height(18.dp))

    Column{
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 48.dp)
        ) {
            Text(text = "x",// X-tekst navigerer til homescreen
                fontSize = 34.sp,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 16.dp)
                    .clickable{navController.navigate("home")}
                )

            // Logoet i midten fungerer også som genvej til Home.
            Image(
                painter = painterResource(id = R.drawable.neveranother_a_logo),
                contentDescription = "neverAnother A logo",
                modifier = Modifier
                    .align(Alignment.Center)
                    .width(140.dp)
                    .height(50.dp)
                    .clickable{
                        navController.navigate("home")
                    },
                contentScale = ContentScale.Fit
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(// Produktnavn "OneBra™" centreret
            text = "OneBra™",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = blackColor,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(12.dp))
        Box(modifier = Modifier.width(60.dp).height(2.dp).background(ctaColor).align(Alignment.CenterHorizontally))
    }
}


// ===== PRODUCT CAROUSEL =====
@Composable
fun ProductCarousel () {// Swipebar karussel med 9 produktbilleder
    val pictures = listOf(
        R.drawable.productsitemodel1,
        R.drawable.productsitemodel2,
        R.drawable.productsitemodel3,
        R.drawable.productsitemodel4,
        R.drawable.productsitemodel5,
        R.drawable.productsitemodel6,
        R.drawable.productsitemodel7,
        R.drawable.productsitemodel8,
        R.drawable.productsitemodel9
    )

    // PagerState husker hvilken side vi er på.
    val carousel = rememberPagerState(pageCount = { pictures.size })

    Spacer(modifier = Modifier.height(40.dp))

    HorizontalPager(
        state = carousel,
        modifier = Modifier.fillMaxWidth().height(300.dp),
        contentPadding = PaddingValues (horizontal = 16.dp),
        pageSpacing = 8.dp,
        pageSize = PageSize.Fixed(200.dp)// PageSize.Fixed = fast bredde på hvert billede så man kan se flere ad gangen

    ) {
        page ->
        Image(painter = painterResource(id = pictures[page]),
            contentDescription = "Carousel for product site",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
            )
    }
}


// ===== COLOR SELECTION =====
@Composable
fun ColorSelection (viewModel: ProductViewModel, navController: NavController) {

   Column(modifier = Modifier.padding(16.dp)) {
       Row(
           modifier = Modifier.fillMaxWidth(),
           horizontalArrangement = Arrangement.SpaceBetween,
           verticalAlignment = Alignment.CenterVertically
       ) {
           Text(
               text = "Vælg Farve",
               fontWeight = FontWeight.Bold,
               color = blackColor,
               fontSize = 24.sp)

           // Denne knap vises kun, hvis brugeren har gennemført skrædder-processen.
           if (viewModel.isTailored) {
               OutlinedButton(
                   onClick = { navController.navigate("result_screen") },
                   border = BorderStroke(1.dp, ctaColor),
                   modifier = Modifier.fillMaxWidth(0.7f).height(36.dp)
               ) {
                   Text("Se dine mål", color = ctaColor, fontSize = 18.sp)
               }
           }
       }

       Row(horizontalArrangement = Arrangement.spacedBy(8.dp),
           modifier = Modifier.padding(start = 16.dp)
           ) {
// Orange ring vises rundt om valgt farve via border-farve
           Box(
               modifier = Modifier
                   .size(32.dp)
                   .border(
                       width = 2.dp,
                        color = if (viewModel.chosenColor == "White")// Hvid cirkel = "White".
                           ctaColor
                       else
                       Color.Transparent,
                       shape = CircleShape
                   )
                   .padding(2.dp)
                   .background(Color.White, shape = CircleShape)
                   .clickable{viewModel.chosenColor = "White"}
           )
           // Sort cirkel
           Box(
               modifier = Modifier
                   .size(32.dp)
                   .border(
                       width = 2.dp,
                           color = if (viewModel.chosenColor == "Black")//sort cirkel = "Black"
                               ctaColor
                       else
                       Color.Transparent,
                       shape = CircleShape
                   )
                   .padding(2.dp)
                   .background(color = blackColor, shape = CircleShape)
                   .clickable{viewModel.chosenColor = "Black"}// choseColor gemmes i ProductViewModel
           )
       }
   }
}


// ===== PRODUCT DESCRIPTION =====
@Composable
fun ProductDescription () {
    Column(modifier = Modifier.fillMaxWidth().padding(28.dp)) {
        Text(text = "Mød din nye yndlings-BH", fontSize = 30.sp, fontWeight = FontWeight.Bold, color = blackColor)
        Spacer(modifier = Modifier.height(18.dp))
        Text(text = "Den er skabt ved hjælp af vores specialtilpassede fit-algoritme...", fontSize = 18.sp, color = blackColor)
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = "Det brede bånd giver støtte og stabilitet...", fontSize = 18.sp, color = blackColor)
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = "Den bløde strikkede bøjle løfter og former skånsomt...", fontSize = 18.sp, color = blackColor)
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = "De dobbeltlagede skåle har et ydre lag...", fontSize = 18.sp, color = blackColor)
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = "Siderne og ryggen har en luftig struktur...", fontSize = 18.sp, color = blackColor)
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = "Har justerbare stropper og tre hægtelukninger på ryggen.", fontSize = 18.sp, color = blackColor)
        
        // Ekstra padding i bunden så teksten ikke bliver dækket af den "flydende" knap.
        Spacer(modifier = Modifier.height(80.dp)) 
    }
}
