package com.example.musicwiki.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.musicwiki.R


@Composable
fun ImageCarousel(modifier: Modifier = Modifier, carouselItemList: List<ImageCarousel>) {
    LazyRow(modifier = modifier) {
        items(carouselItemList.size) {item ->
            ImageCarouselItem(modifier = modifier, carouselItem = carouselItemList[item] )
        }
    }
}


@Composable
fun ImageCarouselItem(modifier: Modifier = Modifier, carouselItem: ImageCarousel) {
    Box(
        modifier = modifier
            .wrapContentWidth()
            .wrapContentHeight()
    ) {
        Image(
            modifier = modifier
                .wrapContentWidth()
                .wrapContentHeight(),
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )

        Column(modifier = modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom) {
            Text(
                text = carouselItem.title,
                fontSize = 16.sp,
                modifier = modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                textAlign = TextAlign.Center
            )

            Text(
                text = carouselItem.subTitle,
                fontSize = 14.sp,
                modifier = modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                textAlign = TextAlign.Center
            )
        }
    }
}


data class ImageCarousel(
    val title: String,
    val subTitle: String,
    val imageArt: String
)