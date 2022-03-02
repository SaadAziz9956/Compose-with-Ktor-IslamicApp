package com.example.islamicapp.ui.hadith.volumn_detail.viewmodel

import android.content.Intent
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.islamicapp.repository.DatabaseRepository
import com.example.islamicapp.response.local.hadess_book_response.HadeesBookItem
import com.example.islamicapp.util.Constants.VOLUME
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VolumeDetailViewModel
@Inject
constructor(
    private val dbRepo: DatabaseRepository
): ViewModel() {

    private val _volume = mutableStateOf(HadeesBookItem(emptyList(), ""))
    val volume: State<HadeesBookItem> = _volume

    fun initViewModel(intent: Intent) {

        viewModelScope.launch(Dispatchers.IO) {

            val volume = intent.extras?.getString(VOLUME)

            volume?.let {
                _volume.value = dbRepo.getSpecificHaidthVolume(it)
            }

        }

    }
}