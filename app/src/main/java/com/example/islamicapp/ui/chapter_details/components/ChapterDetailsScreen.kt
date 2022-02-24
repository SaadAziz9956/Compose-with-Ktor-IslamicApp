package com.example.islamicapp.ui.chapter_details.components

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.HtmlCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.expensemanagment.R
import com.example.expensemanagment.response.network.chapter_detail_response.ChapterInfo
import com.example.expensemanagment.ui.chapter_details.viewmodel.ChapterDetailsViewModel
import com.example.expensemanagment.ui.theme.CardBackgroundGradientTwo
import com.example.expensemanagment.ui.theme.IslamicAppTheme
import com.example.expensemanagment.ui.theme.MidGrey
import com.example.expensemanagment.ui.theme.ToolbarColor
import timber.log.Timber
import kotlin.math.min

@Composable
fun ChapterDetailScreen(
    chapterName: String?,
    error: String?,
    finish: () -> Unit
) {

    var chapName by remember {
        mutableStateOf("Chapter Name")
    }

    chapterName?.let {
        chapName = it
    }

    val viewModel: ChapterDetailsViewModel = hiltViewModel()

    var isData by remember {
        mutableStateOf(false)
    }

    val chapterInfo = viewModel.chapterInfo.value

    if (chapterInfo.chapter_id != null) {
        isData = true
    }

    val scrollState = rememberScrollState(0)


    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        when {
            isData -> {
                Timber.d("Data : $chapterInfo")
                DetailsScreen(chapterInfo, scrollState, chapName)
            }
            error != "" -> {
                Timber.d("Error: $error")
                Toast.makeText(
                    LocalContext.current,
                    "Exception: $error",
                    Toast.LENGTH_SHORT
                ).show()
            }
            else -> {
                Timber.d("Loading")
                Loading()
            }
        }
        AnimatedToolBar(chapterName = chapName, scrollState = scrollState) {
            finish()
        }

    }

}

@Composable
fun AnimatedToolBar(
    chapterName: String, scrollState: ScrollState,
    finish: () -> Unit
) {

    Box {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .alpha(((scrollState.value + 0.05f) / 1000).coerceIn(0f, 1f))
                .fillMaxWidth()
                .background(ToolbarColor)
                .padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            Text(
                text = chapterName,
                color = MaterialTheme.colors.onSurface,
                fontFamily = FontFamily(
                    Font(
                        R.font.ralewaymedium
                    )
                ),
                modifier = Modifier
                    .padding(
                        top = 9.dp,
                        start = 50.dp,
                        bottom = 15.dp
                    )
                    .alpha(((scrollState.value + 0.05f) / 1000).coerceIn(0f, 1f))
            )
        }
        Icon(
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = "",
            modifier = Modifier
                .padding(
                    top = 15.dp,
                    start = 15.dp,
                    bottom = 15.dp
                )
                .clickable { finish() }
        )
    }
}

@Composable
fun DetailsScreen(chapter: ChapterInfo, scrollState: ScrollState, chapterName: String) {

    var longText by remember {
        mutableStateOf("Text not found")
    }

    var source by remember {
        mutableStateOf("Source not found")
    }

    chapter.source?.let {
        source = it
    }

    chapter.text?.let {
        longText = it
    }

    Column(
        modifier = Modifier
            .verticalScroll(state = scrollState)
            .padding(horizontal = 15.dp)
    ) {

        Column(
            Modifier
                .alpha(min(1f, 1 - (scrollState.value / 200f)))
        ) {
            Spacer(
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
            )

            Text(
                text = "Surah",
                fontSize = 13.sp,
                color = MidGrey,
                fontFamily = FontFamily(
                    Font(
                        R.font.ralewayregular
                    )
                )
            )

            Text(
                modifier = Modifier.padding(
                    bottom = 10.dp
                ),
                text = chapterName,
                fontSize = 25.sp,
                color = Color.Black,
                fontFamily = FontFamily(
                    Font(
                        R.font.ralewaymedium
                    )
                )
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Column {
            val fromHtml = HtmlCompat.fromHtml(longText, HtmlCompat.FROM_HTML_MODE_LEGACY)

            Text(
                text = fromHtml.toString(),
                fontSize = 18.sp,
                color = Color.Black,
                fontFamily = FontFamily(
                    Font(
                        R.font.jameelnoori
                    )
                ),
                textAlign = TextAlign.End
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        bottom = 10.dp
                    ),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = source,
                    fontSize = 18.sp,
                    color = Color.Black,
                    fontFamily = FontFamily(
                        Font(
                            R.font.jameelnoori
                        )
                    )
                )
            }
        }
    }

}

@Composable
fun Loading() {
    Column(
        Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(
            color = CardBackgroundGradientTwo
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview3() {
    IslamicAppTheme {
        ChapterDetailScreen(null, null) {}
    }
}