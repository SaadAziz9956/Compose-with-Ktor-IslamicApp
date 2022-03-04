package com.example.islamicapp.ui.chapters.verse.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.islamicapp.response.local.book_response.Ayah
import com.example.islamicapp.response.local.book_response.Surah
import com.example.islamicapp.ui.chapters.verse.viewmodel.VerseViewModel
import kotlinx.coroutines.launch
import timber.log.Timber

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun VerseRecycler(
    chapter: Surah?,
    verseViewModel: VerseViewModel = viewModel()
) {

    var list = listOf<Ayah>()

    chapter?.let {

        list = it.ayahs

    }

    var ayah by remember {
        mutableStateOf(Ayah())
    }

    ayah = verseViewModel.verse.value

    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )
    val coroutineScope = rememberCoroutineScope()
    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = {
            BottomSheet(ayah)
        }, sheetPeekHeight = 0.dp
    ) {
        Column(modifier = Modifier.fillMaxSize()) {

            Timber.d("Ayah List size : ${list.size}")

            LazyColumn(Modifier.weight(1f)) {
                item {
                    TopCard(chapter)
                }
                itemsIndexed(items = list) { _, item ->
                    VerseColumn(item)
                }
            }

            val bottomSheet = verseViewModel.bottomSheet.value

            coroutineScope.launch {
                if (bottomSheet) {
                    bottomSheetScaffoldState.bottomSheetState.expand()
                } else {
                    bottomSheetScaffoldState.bottomSheetState.collapse()
                }
            }
        }
    }


}