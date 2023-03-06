package com.example.musicwiki.genre

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
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.musicwiki.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenreDetailScreen(modifier: Modifier = Modifier) {
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
            Spacer(modifier = modifier.size(20.dp))
            Text(
                text = "Rock",
                fontSize = 42.sp,
                textAlign = TextAlign.Center,
                modifier = modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            )
            Spacer(modifier = modifier.size(12.dp))
            Text(
                text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce et semper sapien, et dictum ligula. Donec fringilla eros non lorem accumsan, non dictum leo tempor. Suspendisse lobortis eu nibh nec faucibus. Interdum et malesuada fames ac ante ipsum primis in faucibus. Etiam nunc purus, pellentesque ac nunc sed, efficitur tempus est. Fusce dictum at diam eget volutpat. In eleifend est at molestie dictum. Aliquam pharetra, eros ut vestibulum iaculis, sem lacus auctor ex, sit amet ullamcorper mi mi vitae metus. Vivamus ac tellus fermentum, volutpat quam vehicula, vulputate ligula. Vestibulum et pulvinar nibh, ut congue enim. ",
                modifier = modifier.padding(start = 16.dp, end = 16.dp)
            )

            Spacer(modifier = modifier.size(8.dp))

            val tabList = listOf("Tab 1", "Tab 2", "Tab3")

            val selectedTabIndex by remember {
                mutableStateOf(0)
            }
            TabRow(selectedTabIndex = selectedTabIndex) {
                tabList.forEachIndexed { index, s ->
                    Tab(selected = selectedTabIndex == index, onClick = { }) {
                        Text(
                            text = s,
                            modifier = modifier.padding(10.dp)
                        )
                    }
                }
            }

            val artistName = listOf("Artist 1", "Artist 2", "Artist 3", "Artist 4")

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(artistName.size) { item ->
                    Card(
                        modifier = Modifier
                            .padding(8.dp)
                            .wrapContentSize()
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_launcher_foreground),
                            contentDescription = null
                        )
                    }
                }
            }


        }
    }

}