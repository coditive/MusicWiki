package com.example.musicwiki.ui.screens.welcome

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Chip
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.musicwiki.R
import com.example.musicwiki.ui.model.UIState
import timber.log.Timber


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun WelcomeScreen(
    modifier: Modifier = Modifier,
    onNavigation: () -> Unit,
) {
    Log.d("welcome", "Welcome screen reached!!")
    val viewModel = hiltViewModel<WelcomeViewModel>()
    val uiState by viewModel.welcomeUIState.collectAsState()
    Timber.d("Viewmodel created!!!!!")
    viewModel.getTopTagList()

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = modifier.size(60.dp))
        Text(
            text = LocalContext.current.getString(R.string.app_name),
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            textAlign = TextAlign.Center,
            fontSize = 24.sp
        )
        Spacer(modifier = modifier.size(20.dp))
        Text(
            text = "Welcome!",
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            textAlign = TextAlign.Center,
            fontSize = 18.sp
        )
        Spacer(modifier = modifier.size(14.dp))
        Row(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.Center
        ) {

            Text(text = "Choose a genre to start with")

            Image(painter = painterResource(id = R.drawable.baseline_expand_circle_down_24),
                contentDescription = null,
                modifier = modifier
                    .padding(start = 8.dp)
                    .clickable {

                    })
        }

        when(val state = uiState) {
            is UIState.Error -> {
                Timber.e(state.message)
            }
            UIState.Loading -> {
                Timber.e("Loading")
            }
            is UIState.Success -> {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(state.data.tagList.size) { item ->
                        Card(
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Chip(onClick = {  }) {
                                Text(text = state.data.tagList[item].name)
                            }
                        }
                    }
                }
            }
        }

    }
}