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
import androidx.compose.foundation.layout.navigationBarsPadding
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.eksamensprojekt_neveranother.viewmodel.BasketItem
import com.example.eksamensprojekt_neveranother.viewmodel.CartViewModel
import com.example.eksamensprojekt_neveranother.viewmodel.ProductViewModel


@Composable
fun ProductScreen (
    navController: NavController,
    viewModel: ProductViewModel,
    cartViewModel: CartViewModel
) {

    val btnText = if (viewModel.isTailored) "Føj til Kurv"
    else "Skræddersy BH"

    Box(
              modifier = Modifier
                  .fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .background(backgroundColor)
        ) {
            item { Header(navController) }
            item { ProductCarousel() }
            item { ColorSelection(viewModel) }
            item { ProductDescription() }
        }

        Button(
            onClick = {
                if (viewModel.isTailored) {
                    cartViewModel.addItem(
                        BasketItem(
                            navn = "OneBra™",
                            farve = viewModel.choseColor,
                            pris = "799,00",
                            billedeRes = R.drawable.productsitemodel1
                        )
                    )
                    navController.navigate("kurv")
                } else {
                    navController.navigate("tailorScreen")
                }
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
            Text(text = btnText,
                color = whiteColor,
                fontSize = 18.sp
                )
        }



    }//box


} //ProductScreen

@Composable
fun Header (navController: NavController) {

    Spacer(modifier = Modifier.height(18.dp))

    Column{
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 48.dp)
        ) {
            Text(text = "x",
                fontSize = 34.sp,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 16.dp)
                    .clickable{navController.navigate("homescreen")}
                )

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
        }//Box


        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "OneBra™",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = blackColor,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Box(
            modifier = Modifier
                .width(60.dp)
                .height(2.dp)
                .background(ctaColor)
                .align(Alignment.CenterHorizontally)
        )


    }    //column

} //Header



@Composable
fun ProductCarousel () {
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

    val carousel = rememberPagerState(pageCount = { pictures.size })

Spacer(modifier = Modifier.height(40.dp))

    HorizontalPager(
        state = carousel,
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        contentPadding =
            PaddingValues (horizontal = 16.dp),
        pageSpacing = 8.dp,
        pageSize = PageSize.Fixed(200.dp)

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



@Composable
fun ColorSelection (viewModel: ProductViewModel) {

    var showMeasurementsPopUp by remember { mutableStateOf(false) }

   Column(
       modifier = Modifier
           .padding(16.dp)
   ) {
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

               if (viewModel.isTailored) {
                   OutlinedButton(
                       onClick = {showMeasurementsPopUp = true},
                       border = BorderStroke(1.dp, ctaColor),
                       modifier = Modifier
                           .fillMaxWidth(0.7f)
                           .height(36.dp)
                   ) {
                       Text("Se dine mål", color = ctaColor, fontSize = 18.sp)
                   }
               }
       }//Row



       Row(horizontalArrangement = Arrangement.spacedBy(8.dp),
           modifier = Modifier.padding(start = 16.dp)
           ) {

           Box(
               modifier = Modifier
                   .size(32.dp)
                   .border(
                       width = 2.dp,
                        color = if (viewModel.choseColor == "White")
                           ctaColor
                       else
                       Color.Transparent,
                       shape = CircleShape
                   )
                   .padding(2.dp)
                   .background(Color.White, shape = CircleShape)
                   .clickable{viewModel.choseColor = "White"}
           )

           Box(
               modifier = Modifier
                   .size(32.dp)
                   .border(
                       width = 2.dp,
                           color = if (viewModel.choseColor == "Black")
                               ctaColor
                       else
                       Color.Transparent,
                       shape = CircleShape
                   )
                   .padding(2.dp)
                   .background(color = blackColor, shape = CircleShape)
                   .clickable{viewModel.choseColor = "Black"}
           )
       }
   }//Column
}

@Composable
fun ProductDescription () {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(28.dp)
    ) {

        Text(
            text = "Mød din nye yndlings-BH",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = blackColor,
        )

        Spacer(modifier = Modifier.height(18.dp))

//////////////////////

        Text(
            text = "Den er skabt ved hjælp af vores specialtilpassede fit-algoritme og skræddersyet til at passe perfekt til dine mål.",
            fontSize = 18.sp,
            color = blackColor
        )

        Spacer(modifier = Modifier.height(12.dp))

//////////////////////

        Text(
            text = "Det brede bånd giver støtte og stabilitet, så du føler dig tryg hele dagen.",
            fontSize = 18.sp,
            color = blackColor
        )

        Spacer(modifier = Modifier.height(12.dp))

        /////////////////



        Text(
            text = "Den bløde strikkede bøjle løfter og former skånsomt og fremhæver din silhuet uden ubehaget fra en traditionel metalbøjle.",
            fontSize = 18.sp,
            color = blackColor
        )

        Spacer(modifier = Modifier.height(12.dp))

        /////////////////



        Text(
            text = "De dobbeltlagede skåle har et ydre lag, der giver struktur og holdbarhed, mens det indre lag er blødt og åndbart.",
            fontSize = 18.sp,
            color = blackColor
        )

        Spacer(modifier = Modifier.height(12.dp))

        /////////////////



        Text(
            text = "Siderne og ryggen har en luftig struktur, som holder dig kølig og komfortabel.",
            fontSize = 18.sp,
            color = blackColor
        )

        Spacer(modifier = Modifier.height(12.dp))

        /////////////////



        Text(
            text = "Har justerbare stropper og tre hægtelukninger på ryggen.",
            fontSize = 18.sp,
            color = blackColor
        )

        Spacer(modifier = Modifier.height(60.dp))

    }//column

}