package com.example.islamicapp.ui.chapters.verse

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.islamicapp.response.local.book_response.Surah
import com.example.islamicapp.ui.theme.IslamicAppTheme
import com.example.islamicapp.ui.chapters.verse.screens.MainScreenVerse
import com.example.islamicapp.ui.chapters.verse.viewmodel.VerseViewModel
import com.example.islamicapp.util.EventHandler
import com.example.islamicapp.util.Constants.CHAPTER
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber


@AndroidEntryPoint
class VerseActivity : ComponentActivity() {

    private val viewModel by viewModels<VerseViewModel>()
    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IslamicAppTheme {

                val chapter = intent?.extras?.getParcelable<Surah>(CHAPTER)

                startCollecting()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreenVerse(
                        chapter
                    ) {
                        finish()
                    }
                }
            }
        }
    }


    private fun startCollecting() {

        lifecycleScope.launchWhenCreated {

            viewModel.event
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collectLatest { event ->

                    when (event) {

                        is EventHandler.MoveForward -> {
                            startActivity(event.intent)
                        }

                        is EventHandler.PlayAudio -> {

                            val selectedVerse = event.ayah

                            lifecycleScope.launch(Dispatchers.IO) {
                                selectedVerse?.audio?.let { onPlayAudio(it) }
                            }

                        }

                        is EventHandler.PauseAudio -> {
                            lifecycleScope.launch(Dispatchers.IO) {
                                releaseMediaPlayer()
                            }
                        }

                        else -> Unit
                    }

                }

        }

    }

    private fun onPlayAudio(url: String) {
        try {
            releaseMediaPlayer()
            mediaPlayer = MediaPlayer().apply {
                setAudioAttributes(
                    AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
                )
                setDataSource(url)
                prepare()
                start()
            }
            mediaPlayer?.setOnCompletionListener {
                Timber.d("Audio finished")
            }
        } catch (e: Exception) {
            lifecycleScope.launch(Dispatchers.Main) {
                Toast.makeText(
                    this@VerseActivity,
                    "Network error : " + e.message,
                    Toast.LENGTH_LONG
                )
                    .show()
            }
        }

    }

    private fun releaseMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer?.pause()
            mediaPlayer?.seekTo(0)
            mediaPlayer = null
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        releaseMediaPlayer()
    }
}

@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    IslamicAppTheme {
        MainScreenVerse(
            null
        ) {}
    }
}