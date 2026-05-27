package com.example.eksamensprojekt_neveranother.ui.components

import android.view.LayoutInflater
import androidx.annotation.OptIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import com.example.eksamensprojekt_neveranother.R

// ===== VIDEO PLAYER KOMPONENT =====
// Denne komponent er en "wrapper" omkring Androids Media3/ExoPlayer bibliotek.
// Formålet er at skabe en genanvendelig videokomponent, der kan bruges på tværs af appen,
// som f.eks. i MeasurementTemplate eller på forsiden.

@OptIn(UnstableApi::class) // Påkrævet da visse Media3 APIs stadig er under udvikling af Google.
@Composable
fun VideoPlayer(videoResId: Int, modifier: Modifier = Modifier) {
    val context = LocalContext.current

    // 1. INITIALISER EXOPLAYER
    // Vi bruger 'remember' for at sikre, at ExoPlayer-instansen overlever "recomposition".
    // Det betyder, at videoen ikke genstarter hver gang UI'en opdateres.
    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            // Vi bygger en URI til videofilen, som ligger i 'raw' mappen.
            val uri = "android.resource://${context.packageName}/$videoResId"
            setMediaItem(MediaItem.fromUri(uri))
            prepare() // Gør videoen klar i hukommelsen.
            playWhenReady = true // Auto-play: Videoen starter så snart den er klar.
            repeatMode = ExoPlayer.REPEAT_MODE_ALL // Looper videoen uendeligt.
        }
    }

    // 2. LIVSCYKLUS-STYRING (DisposableEffect)
    // Dette er kritisk for ydeevnen! En videoafspiller bruger tunge systemressourcer (hardware-acceleration).
    // DisposableEffect sikrer, at vi rydder op (release), så snart komponenten ikke længere er på skærmen.
    // Det forhindrer "memory leaks" og at appen bruger strøm i baggrunden.
    DisposableEffect(Unit) {
        onDispose {
            exoPlayer.release() // Slukker helt for afspilleren og frigiver hardware-ressourcerne.
        }
    }

    // 3. ANDROIDVIEW (Broen til det gamle system)
    // Da Jetpack Compose (det nye system) ikke har en indbygget video-komponent, 
    // bruger vi AndroidView til at "hoste" et traditionelt PlayerView.
    AndroidView(
        modifier = modifier,
        factory = { ctx ->
            // Vi bruger LayoutInflater til at hente vores specialdesignede layout (texture_video_view.xml).
            // Valget af TextureView fremfor SurfaceView giver bedre understøttelse for 
            // effekter som .blur() og animationer i Compose.
            val view = LayoutInflater.from(ctx).inflate(R.layout.texture_video_view, null) as PlayerView
            view.apply {
                player = exoPlayer // Forbinder motoren (ExoPlayer) med vinduet (PlayerView).
                resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM // Zoomer ind så videoen fylder hele arealet (beskærer kanter).
            }
        }
    )
}
