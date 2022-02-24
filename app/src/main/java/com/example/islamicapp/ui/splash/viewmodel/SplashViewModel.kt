package com.example.islamicapp.ui.splash.viewmodel

import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensemanagment.App
import com.example.expensemanagment.repository.JsonRepository
import com.example.expensemanagment.ui.main.MainActivity
import com.example.expensemanagment.util.EventHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel
@Inject
constructor(
    private val repo: JsonRepository,
    private val context: App
) : ViewModel() {

    private val _events = MutableSharedFlow<EventHandler>()
    val events = _events.asSharedFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val data = repo.getData()
            if(data) {
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