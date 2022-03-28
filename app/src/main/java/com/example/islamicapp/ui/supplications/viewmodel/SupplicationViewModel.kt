package com.example.islamicapp.ui.supplications.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.islamicapp.repository.DatabaseRepository
import com.example.islamicapp.response.local.duaas.Supplication
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SupplicationViewModel
@Inject
constructor(
    private val dbRepo: DatabaseRepository
) : ViewModel() {

    private val _dua = mutableStateOf(listOf<Supplication>())
    val dua: State<List<Supplication>> = _dua

    init {
        Timber.d("INIT")
        viewModelScope.launch(Dispatchers.IO) {
            getData()
        }
    }

    private fun getData() {
        val duas = dbRepo.getAllDuas()

        duas.onEach { dua ->

            _dua.value = dua

        }.launchIn(viewModelScope)
    }

}