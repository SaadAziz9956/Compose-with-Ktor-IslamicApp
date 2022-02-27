package com.example.islamicapp.ui.chapter_details

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.islamicapp.response.network.chapter_detail_response.ChapterInfo
import com.example.islamicapp.ui.chapter_details.components.ChapterDetailScreen
import com.example.islamicapp.ui.chapter_details.viewmodel.ChapterDetailsViewModel
import com.example.islamicapp.ui.theme.IslamicAppTheme
import com.example.islamicapp.util.EventHandler
import com.example.islamicapp.util.Constants.CHAPTER_NAME
import com.example.islamicapp.util.Constants.CHAPTER_NO
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class ChapterDetail : ComponentActivity() {

    private val viewModel by viewModels<ChapterDetailsViewModel>()
    private var error: String by mutableStateOf("")
    private var details: ChapterInfo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IslamicAppTheme {

                val chapterNo = intent?.extras?.getInt(CHAPTER_NO)
                val chapterName = intent?.extras?.getString(CHAPTER_NAME)

                viewModel.initViewModel(chapterNo)

                startCollecting()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ChapterDetailScreen(chapterName, error) {
                        this.finish()
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

                   when(event) {
                       is EventHandler.Error -> {
                           error = event.error.toString()
                       }
                       else -> Unit
                   }

               }

       }
    }
}


