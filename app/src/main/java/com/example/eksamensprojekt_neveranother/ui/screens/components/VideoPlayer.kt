package com.example.eksamensprojekt_neveranother.ui.screens.components

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

@OptIn(UnstableApi::class)
@Composable
fun VideoPlayer(videoResId: Int, modifier: Modifier = Modifier) {
    val context = LocalContext.current

    // 1. Initialize ExoPlayer
    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            // Build the URI for the raw resource
            val uri = "android.resource://${context.packageName}/$videoResId"
            setMediaItem(MediaItem.fromUri(uri))
            prepare()
            playWhenReady = true // Auto-play
            repeatMode = ExoPlayer.REPEAT_MODE_ALL
        }
    }

    // 2. Manage Lifecycle (Release player when Composable is destroyed)
    DisposableEffect(Unit) {
        onDispose {
            exoPlayer.release()
        }
    }

    // 3. Use AndroidView to host the PlayerView inflated from XML
    AndroidView(
        modifier = modifier,
        factory = { ctx ->
            val view = LayoutInflater.from(ctx).inflate(R.layout.texture_video_view, null) as PlayerView
            view.apply {
                player = exoPlayer
                resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
            }
        }
    )
}
