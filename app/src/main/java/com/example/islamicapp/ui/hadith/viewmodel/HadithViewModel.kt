package com.example.islamicapp.ui.hadith.viewmodel

import android.content.Intent
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.islamicapp.repository.DatabaseRepository
import com.example.islamicapp.response.local.hadess_book_response.HadeesBookItem
import com.example.islamicapp.response.local.hadess_book_response.Hadith
import com.example.islamicapp.util.Constants
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

    private val _volume = mutableStateOf(HadeesBookItem(emptyList(), ""))
    val volume: State<HadeesBookItem> = _volume

    private val _hadith = mutableStateOf(listOf<Hadith>())
    val hadith: State<List<Hadith>> = _hadith

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

    fun getSpecificVolume(intent: Intent) {

        viewModelScope.launch(Dispatchers.IO) {

            val volume = intent.extras?.getString(Constants.VOLUME)

            volume?.let {
                _volume.value = dbRepo.getSpecificHaidthVolume(it)
            }

        }

    }

    fun getSpecificBook(bookName: String?) {

        val allHadith = dbRepo.getAllHadiths()

        allHadith.onEach {

            it.forEach { hadithBookItem ->

                hadithBookItem.books.forEach { book ->

                    if (bookName == book.name) {
                        val hadith = book.hadiths
                        _hadith.value = hadith
                    }

                }

            }

        }.launchIn(viewModelScope)


    }

}