package com.example.eksamensprojekt_neveranother.ui.screens.home

import androidx.annotation.OptIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.navigation.NavController
import com.example.eksamensprojekt_neveranother.R
import com.example.eksamensprojekt_neveranother.ui.theme.backgroundColor
import com.example.eksamensprojekt_neveranother.ui.theme.ctaColor
import com.example.eksamensprojekt_neveranother.ui.theme.footerColor
import com.example.eksamensprojekt_neveranother.ui.theme.whiteColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward




// ===== HOMESCREEN =====
// Startskærmen - indeholder en scrollbar liste med alle sektioner
@Composable
fun HomeScreen (
    navController: NavController,
    isTailored: Boolean
    ) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            item {HeroSection(navController, isTailored) }
            item {VoresProduktSection(navController)  }
            item {OmOsSection() }
            item { Footer() }
        }
    }
}


// ===== HERO SECTION =====
// isTailored: true = brugeren har lavet onboarding, knap skifter til "Se din BH"
@OptIn(UnstableApi::class)
@Composable
fun HeroSection(
    navController: NavController,
    isTailored: Boolean
) {


    val btnText = if (isTailored) "Se din BH"
    else "Skræddersy BH"

    val btnRoute = if (isTailored) "produkt"
    else "measurementstart"



    // Video der afspilles automatisk i baggrunden på loop uden lyd
    val context = LocalContext.current
    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            setMediaItem(
                MediaItem.fromUri(
                    "android.resource://${context.packageName}/raw/homescreenstartvideo"
                )
            )
            repeatMode = Player.REPEAT_MODE_ALL
            volume = 0f
            prepare()
            playWhenReady = true
        }
    }

// ExoPlayer sættes op og frigives når screen lukkes (DisposableEffect)
    DisposableEffect(Unit) {
        onDispose { exoPlayer.release() }
    }

    Spacer(modifier = Modifier.height(50.dp))

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(380.dp)
    ) {

        AndroidView(
            factory = {
                PlayerView(it).apply {
                    player = exoPlayer
                    useController = false
                    resizeMode =
                        androidx.media3.ui.AspectRatioFrameLayout.RESIZE_MODE_ZOOM
                }
            },
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(// Logo øverst i hero
                painter = painterResource(id = R.drawable.nalogowhite700),
                contentDescription = "NeverAnother Logo",
                modifier = Modifier
                    .width(280.dp)
                    .height(70.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(50.dp))

            Text(// Titel-tekst med blandede farver (orange + hvid) via buildAnnotatedString
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = ctaColor))
                    {
                        append("SKRÆDDERSY ")
                    }

                    withStyle(style = SpanStyle(color = whiteColor))
                    {
                        append("DIN\n")
                    }

                    withStyle(style = SpanStyle(color = whiteColor))
                    {
                        append("HELT ")
                    }

                    withStyle(style = SpanStyle(color = ctaColor))
                    {
                        append("EGEN BH")
                    }
                },
                textAlign = TextAlign.Center,
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(50.dp))

            Button(// CTA-knap: navigerer til onboarding eller produktside afhængigt af isTailored
                onClick = { navController.navigate(btnRoute) },
                colors = ButtonDefaults.buttonColors(containerColor = ctaColor),
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .height(52.dp)
            ) {
                Text(
                    text = btnText,
                    color = whiteColor,
                    fontSize = 22.sp
                )
            }
        }
    }
}
// ===== VORES PRODUKT SECTION =====
@Composable
fun VoresProduktSection (navController: NavController) {

    val billeder = listOf(
        R.drawable.voresproduktfirstpic,
        R.drawable.voresproduktsecondpic,
        R.drawable.voresproduktthirdpic,
        R.drawable.voresproduktfourthpic,
        R.drawable.voresproduktfifthpic
    )
    val pagerState = rememberPagerState(pageCount = { billeder.size })

    Column(
        modifier = Modifier
            .background(backgroundColor)
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {

Text(
    text = "Vores produkt",
    fontSize = 28.sp,
    modifier = Modifier.align(Alignment.CenterHorizontally)
)

        Spacer(modifier = Modifier.height(10.dp))

//Lille Orange Streg under Vores Produkt.
Box(//Orange Streg
    modifier = Modifier
        .width(50.dp)
        .height(2.dp)
        .background(ctaColor)
        .align(Alignment.CenterHorizontally)
) {}

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
Text(// OneBra™ er klikbar og navigerer til produktsiden
    text = "OneBra™",

    textDecoration = TextDecoration.Underline,
    fontSize = 28.sp,
    modifier = Modifier
        .clickable {navController.navigate("product")}
)
            Text(
                text = "799,00 kr.",

                fontSize = 12.sp,
                modifier = Modifier
                    .padding(top =18.dp)
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

HorizontalPager(// HorizontalPager = swipebar billedkarussel med 5 produktbilleder
    state = pagerState,
    modifier = Modifier
        .fillMaxWidth()
        .height(220.dp),
    contentPadding = PaddingValues(horizontal = 16.dp),
    pageSpacing = 16.dp,
    pageSize = PageSize.Fixed(150.dp)
) {
    page ->

    Image(painter = painterResource(id = billeder[page]),
        contentDescription = "Model billeder til karrusel",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxHeight()
    )
        }
    }
}


// ===== OM OS SECTION =====
@Composable
fun OmOsSection () {

    Column(
        modifier = Modifier
            .background(backgroundColor)
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {

        Text(
            text = "Om os",
            fontSize = 28.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Box(//Orange Streg
            modifier = Modifier
                .width(50.dp)
                .height(2.dp)
                .background(ctaColor)
                .align(Alignment.CenterHorizontally)
        ) {}

        Spacer(modifier = Modifier.height(26.dp))

        Row(// 5 rækker med billede + tekst side om side (skifter venstre/højre)
            modifier = Modifier
                .fillMaxWidth()

        ) {

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(12.dp)
            ) {
                Text(
                    text = "The digital tailor",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(text = "Vores teknologi fungerer som en digital skrædder: Du måler dig selv med vores guide, og en størrelsesalgoritme tilpasser designet for optimal pasform.")

                Spacer(modifier = Modifier.height(8.dp))

                Text(text = "Bh’en 3D-strikkes sømløst på en digital strikkemaskine, hvilket giver mindre spild og overproduktion.")


            } //Column i row 1

            Image(
                painter = painterResource(id = R.drawable.homescreenomosfirstpic),
                contentDescription = "Første billede af model under beskrivelsen:*om os*",
                contentScale = ContentScale.Crop,
                modifier = Modifier.weight(1f)
            )
        }//Row 1

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()

        ) {

            Image(
                painter = painterResource(id = R.drawable.homescreenomossecondpic),
                contentDescription = "Andet billede af model under beskrivelsen:*om os*",
                contentScale = ContentScale.Crop,
                modifier = Modifier.weight(1f)
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(12.dp)
            ) {
                Text(
                    text = "Why tailored",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(text = "Standardstørrelser passer kun få: kun 12% har den kropstype, de er designet til.")

                Spacer(modifier = Modifier.height(8.dp))

                Text(text = "Bh’er masseproduceres, måles ofte for simpelt, og mange ender med dårlig pasform.")

                Spacer(modifier = Modifier.height(8.dp))

                Text(text = "NEVER ANOTHER bruger moderne teknologi til at lave skræddersyede produkter, der passer dig.")
            } //Column i row 2
        }//Row 2

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()

        ) {

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(12.dp)
            ) {
                Text(
                    text = "Mission",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(text = "Vores mission er at lave digitalt skræddersyede bh’er, så alle kan få en komfortabel, perfekt pasform.")

                Spacer(modifier = Modifier.height(8.dp))

                Text(text = "Diversitet kræver tilpasning til individet. Vi starter med ét design og udvider hurtigt sortimentet.")
            } //Column i row 3

            Image(
                painter = painterResource(id = R.drawable.homescreenomosthirdpic),
                contentDescription = "Tredje billede af model under beskrivelsen:*om os*",
                contentScale = ContentScale.Crop,
                modifier = Modifier.weight(1f)
            )
        }//Row 3

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()

        ) {

            Image(
                painter = painterResource(id = R.drawable.homescreenomosfourthpic),
                contentDescription = "Fjerde billede af model under beskrivelsen:*om os*",
                contentScale = ContentScale.Crop,
                modifier = Modifier.weight(1f)
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(12.dp)
            ) {
                Text(
                    text = "Community",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(text = "Vores community er lige så inkluderende som vores produkter. ")

                Spacer(modifier = Modifier.height(8.dp))

                Text(text = "Vi skaber et rum for sårbarhed, diversitet og autenticitet gennem workshops, events og indhold, der styrker fællesskab.")

                Spacer(modifier = Modifier.height(8.dp))

                Text(text = "Vær med: tilmeld dig nyhedsbrevet eller følg os på Instagram @neveranother_na.")
            } //Column i row 4
        }//Row 4

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()

        ) {

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(12.dp)
            ) {
                Text(
                    text = "Design & Production",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(text = "NEVER ANOTHER-bh’en er designet i Danmark med input fra workshops, surveys og testpiloter. Efter 2 års udvikling og 50+ prototyper landede vi det rette design, materiale og funktion.")

                Spacer(modifier = Modifier.height(8.dp))

                Text(text = "Bh’en strikkes i ét stykke og produceres i Holland af en partner, vi har arbejdet med fra start—med fokus på kvalitet og ansvarlig produktion.")
            } //Column i row 5

            Image(
                painter = painterResource(id = R.drawable.homescreenomosfifthpic),
                contentDescription = "Tredje billede af model under beskrivelsen:*om os*",
                contentScale = ContentScale.Crop,
                modifier = Modifier.weight(1f)
            )
        }//Row 5
    } //column
}


// ===== FOOTER =====
@Composable
fun Footer () {

    var email by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(footerColor)
    ) {

        Spacer(modifier = Modifier.height(12.dp))

        Image(
            painter = painterResource(id = R.drawable.na_logo_black700),
            contentDescription = "NeverAnother Logo",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .width(280.dp)
                .height(70.dp),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Sign up for news and happenings",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
            )

        Spacer(modifier = Modifier.height(18.dp))

      Row(// Email-felt til nyhedsbrev med orange send-knap
          modifier = Modifier
              .fillMaxWidth()
              .background(backgroundColor, shape = RoundedCornerShape(4.dp)),
          verticalAlignment = Alignment.CenterVertically
      ) {
          BasicTextField(value = email,
              onValueChange = { email = it },
              modifier = Modifier
                  .weight(1f)
                  .padding(horizontal = 12.dp, vertical = 14.dp),
              decorationBox = {innerTextField -> if (email.isEmpty()) {
                  Text("E-mail", color = Color.Gray)
              }
                  innerTextField()
              }
          )

          Box(
              modifier = Modifier
                  .background(ctaColor)
                  .padding(14.dp)
                  .clickable{}

          ) {
              Icon(
                  imageVector = Icons.Default.ArrowForward,
                  contentDescription = "Send",
                  tint = backgroundColor
              )
          }
      }

        Spacer(modifier = Modifier.height(52.dp))

    }
}