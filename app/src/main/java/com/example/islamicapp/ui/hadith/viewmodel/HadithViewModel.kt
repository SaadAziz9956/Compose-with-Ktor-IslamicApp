package com.example.islamicapp.ui.hadith.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.islamicapp.repository.DatabaseRepository
import com.example.islamicapp.response.local.hadess_book_response.HadeesBookItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HadithViewModel
@Inject
constructor(
    private val dbRepo: DatabaseRepository
) : ViewModel() {

    private val _books = mutableStateOf<List<HadeesBookItem>>(emptyList())
    val books: State<List<HadeesBookItem>> = _books

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getHadithBook()
        }
    }

    private fun getHadithBook() {
        Timber.d("getHadithBook")
        val allHadiths = dbRepo.getAllHadiths()

        allHadiths.onEach {
            _books.value = it
        }.launchIn(viewModelScope)

    }

}