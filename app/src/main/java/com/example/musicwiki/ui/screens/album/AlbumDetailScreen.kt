package com.example.musicwiki.ui.screens.album

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Chip
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.musicwiki.R
import com.example.musicwiki.ui.model.UIState


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AlbumDetailScreen(
    modifier: Modifier = Modifier,
    onNavigationToGenreDetails: (String) -> Unit,
    albumName: String,
    mbid: String
) {

    val viewModel = hiltViewModel<AlbumDetailViewModel>()
    val uiState by viewModel.albumDetailsUIState.collectAsState()
    viewModel.getAlbumDetails(albumName, mbid)

    ConstraintLayout(modifier = modifier.fillMaxSize()) {
        val (topAppBar, chipRow, albumDescription, artistName) = createRefs()

        when (val state = uiState) {
            is UIState.Error -> {
                Log.e("AlbumDetailScreen", state.message)
            }

            UIState.Loading -> {

            }

            is UIState.Success -> {
                Box(
                    modifier = modifier
                        .fillMaxWidth()
                        .fillMaxHeight(.4f)
                        .constrainAs(topAppBar) {
                            top.linkTo(parent.top)
                        },
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(state.data.imageList[3].imageUrl).build(),
                        contentDescription = null,
                        modifier = modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        contentScale = ContentScale.FillBounds
                    )

                    TopAppBar(
                        modifier = modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        title = {},
                        navigationIcon = {
                            Image(
                                painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                                contentDescription = null
                            )
                        },
                        elevation = 0.dp,
                        backgroundColor = Color.Transparent
                    )
                }

                Text(text = state.data.name,
                    fontSize = 34.sp,
                    modifier = modifier.constrainAs(artistName) {
                        bottom.linkTo(topAppBar.bottom, 100.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    })

                val listOfChips = state.data.tagList.tag.map { it.name }

                Row(modifier = modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .constrainAs(chipRow) {
                        top.linkTo(topAppBar.bottom)
                    }) {
                    for (chip in listOfChips) {
                        Chip(onClick = { }) {
                            Text(text = chip)
                        }
                    }
                }

                Text(text = state.data.wiki.summary,
                    modifier = modifier
                        .constrainAs(albumDescription) {
                            top.linkTo(chipRow.bottom, 20.dp)
                        }
                        .padding(start = 10.dp, end = 10.dp))

            }
        }
    }
}