package com.example.musicwiki.home

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
import androidx.compose.foundation.lazy.grid.GridCells.Fixed
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Chip
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.musicwiki.R
import com.example.musicwiki.ui.screens.welcome.WelcomeViewModel


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun WelcomeScreen(
    modifier: Modifier = Modifier,
    onNavigation: () -> Unit,
    viewModel: WelcomeViewModel
) {
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

        val chipValues = listOf(
            "Chip 1",
            "Chip 2",
            "Chip 3",
            "Chip 4",
            "Chip 5",
            "Chip 6",
            "Chip 7",
            "Chip 8",
            "Chip 9"
        )

        LazyVerticalGrid(
            columns = Fixed(3),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(chipValues.size) { item ->
                Card(
                    modifier = Modifier.padding(8.dp)
                ) {
                    Chip(onClick = {  }) {
                        Text(text = chipValues[item])
                    }
                }
            }
        }
    }
}