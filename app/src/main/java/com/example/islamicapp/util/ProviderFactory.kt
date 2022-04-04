package com.example.islamicapp.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.islamicapp.App
import com.example.islamicapp.repository.DatabaseRepository
import com.example.islamicapp.repository.PrayerTimingRepository
import com.example.islamicapp.ui.home.viewmodel.MainViewModel
import javax.inject.Inject

class ProviderFactory
@Inject
constructor(
    private val repo: PrayerTimingRepository,
    private val dbRepo: DatabaseRepository,
    private val context: App
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repo, dbRepo, context) as T
    }
}