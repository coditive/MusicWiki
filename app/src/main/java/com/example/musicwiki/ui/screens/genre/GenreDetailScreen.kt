package com.example.musicwiki.ui.screens.genre

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.musicwiki.R
import com.example.musicwiki.ui.model.UIState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenreDetailScreen(
    modifier: Modifier = Modifier,
    onNavigationToAlbumDetails: () -> Unit,
    onNavigationToArtistDetails: () -> Unit, genreName: String
) {
    val viewModel = hiltViewModel<GenreDetailViewModel>()
    val uiState by viewModel.genreDetailsUIState.collectAsState()
    viewModel.getGenreDetailsFromTag(genreName)

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                        contentDescription = null
                    )
                },
                title = {},
            )
        },
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Spacer(modifier = modifier.size(10.dp))

            when (val state = uiState) {
                is UIState.Error -> {
                    Log.e("GenreDetail", state.message)
                }

                UIState.Loading -> {

                }

                is UIState.Success -> {
                    Text(
                        text = genreName.replaceFirstChar { char ->
                            if (char.isLowerCase()) char.titlecase() else it.toString()
                        },
                        fontSize = 42.sp,
                        textAlign = TextAlign.Center,
                        modifier = modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                    )
                    Spacer(modifier = modifier.size(12.dp))
                    Text(
                        text = state.data.summary,
                        modifier = modifier.padding(start = 16.dp, end = 16.dp)
                    )

                    Spacer(modifier = modifier.size(8.dp))

                    val tabList = listOf("Albums", "Artists", "Tracks")

                    val selectedTabIndex by remember {
                        mutableStateOf(0)
                    }
                    TabRow(selectedTabIndex = selectedTabIndex) {
                        tabList.forEachIndexed { index, s ->
                            Tab(selected = selectedTabIndex == index, onClick = { }) {
                                Text(
                                    text = s, modifier = modifier.padding(10.dp)
                                )
                            }
                        }
                    }

                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2), modifier = Modifier.fillMaxWidth()
                    ) {
                        items(state.data.albumList.size) { item ->
                            Card(
                                modifier = Modifier
                                    .padding(8.dp)
                                    .wrapContentSize()
                            ) {
                                AsyncImage(
                                    model = ImageRequest.Builder(LocalContext.current)
                                        .data(state.data.albumList[item].imageList[2].imageUrl)
                                        .crossfade(true).build(),
                                    placeholder = painterResource(R.drawable.ic_launcher_foreground),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.clip(CircleShape)
                                )

                            }
                        }
                    }

                }
            }
        }
    }

}