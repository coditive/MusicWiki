package com.example.musicwiki.ui.screens.album

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.musicwiki.R


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AlbumDetailScreen(
    modifier: Modifier = Modifier,
    onNavigationToAlbumDetails: () -> Unit,
) {

    val viewModel = hiltViewModel<AlbumDetailViewModel>()

    ConstraintLayout(modifier = modifier.fillMaxSize()) {
        val (topAppBar, chipRow, albumDescription, artistName) = createRefs()

        Box(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight(.4f)
                .constrainAs(topAppBar) {
                    top.linkTo(parent.top)
                },
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = null,
                modifier = modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                contentScale = ContentScale.FillBounds
            )

            TopAppBar(modifier = modifier
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

        Text(text = "ColdPlay", fontSize = 34.sp, modifier = modifier.constrainAs(artistName) {
            bottom.linkTo(topAppBar.bottom, 100.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        })

        val listOfChips = listOf("Chip 1", "Chip 2", "Chip 3", "Chip 4", "Chip 5")

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

        Text(text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce et semper sapien, et dictum ligula. Donec fringilla eros non lorem accumsan, non dictum leo tempor. Suspendisse lobortis eu nibh nec faucibus. Interdum et malesuada fames ac ante ipsum primis in faucibus. Etiam nunc purus, pellentesque ac nunc sed, efficitur tempus est. Fusce dictum at diam eget volutpat. In eleifend est at molestie dictum. Aliquam pharetra, eros ut vestibulum iaculis, sem lacus auctor ex, sit amet ullamcorper mi mi vitae metus. Vivamus ac tellus fermentum, volutpat quam vehicula, vulputate ligula. Vestibulum et pulvinar nibh, ut congue enim. "
        , modifier = modifier
                .constrainAs(albumDescription) {
                    top.linkTo(chipRow.bottom, 20.dp)
                }
                .padding(start = 10.dp, end = 10.dp))


    }

}