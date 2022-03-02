package com.example.islamicapp.ui.onboarding.viewmodel

import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.islamicapp.App
import com.example.islamicapp.repository.JsonRepository
import com.example.islamicapp.ui.main.MainActivity
import com.example.islamicapp.util.EventHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel
@Inject
constructor(
    private val repo: JsonRepository,
    private val context: App
) : ViewModel() {

    private val _events = MutableSharedFlow<EventHandler>()
    val events = _events.asSharedFlow()

    fun initViewModel() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getDataFromJson()
            if(repo.intent.value) {
                intentLauncher()
            }
        }
    }


    private fun intentLauncher() {
        viewModelScope.launch {
            Intent(context, MainActivity::class.java).also { intent ->
                _events.emit(
                    EventHandler.MoveForward(intent)
                )
            }
        }
    }

}