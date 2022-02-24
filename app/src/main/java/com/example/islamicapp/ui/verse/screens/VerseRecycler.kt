package com.example.islamicapp.ui.verse.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.expensemanagment.response.local.book_response.Ayah
import com.example.expensemanagment.response.local.book_response.Surah
import com.example.expensemanagment.ui.verse.viewmodel.VerseViewModel
import kotlinx.coroutines.launch
import timber.log.Timber

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun VerseRecycler(
    chapter: Surah?,
) {

    var list = listOf<Ayah>()

    chapter?.let {

        list = it.ayahs

    }

    var ayah by remember {
        mutableStateOf(Ayah())
    }

    val viewModel: VerseViewModel = hiltViewModel()

    ayah = viewModel._verse.value

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
                    VerseColoum(item)
                }
            }

            val bottomSheet = viewModel._bottomSheet.value

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