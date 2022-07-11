package com.relsellglobal.playingvideojp

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.PlayerView
import com.relsellglobal.playingvideojp.ui.theme.PlayingVideoJPTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlayingVideoJPTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    playVideo(this)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}


@Composable
fun playVideo(con: Context) {
    val videoUrl = "https://storage.googleapis.com/exoplayer-test-media-0/play.mp3"
    val exoplayer = remember(con) {
        ExoPlayer.Builder(con.applicationContext).build().apply {
            val mediaItem = MediaItem.fromUri(Uri.parse(videoUrl))
            // Set the media item to be played.
            setMediaItem(mediaItem)
            // Prepare the player.
            prepare()
//            play()

        }
    }
    AndroidView(factory = {context ->
        PlayerView(context).apply {
            player = exoplayer
        }
    })


}
