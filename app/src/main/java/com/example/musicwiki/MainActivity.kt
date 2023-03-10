package com.example.musicwiki

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.musicwiki.ui.screens.album.AlbumDetailScreen
import com.example.musicwiki.ui.screens.artist.ArtistDetailScreen
import com.example.musicwiki.ui.screens.genre.GenreDetailScreen
import com.example.musicwiki.ui.screens.welcome.WelcomeScreen
import com.example.musicwiki.ui.theme.MusicWikiTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MusicWikiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    NavHostComposable()
                }
            }
        }
    }
}


@Composable
fun NavHostComposable(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "welcome",
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable("welcome") {
            WelcomeScreen(
                onNavigation = { genreName -> navController.navigate("genreDetailScreen/$genreName") },
            )
        }

        composable("genreDetailScreen/{genreName}") {
            val genreName = it.arguments?.getString("genreName") ?: ""
            GenreDetailScreen(
                onNavigationToAlbumDetails = { albumName, mbid -> navController.navigate("albumDetailScreen/$albumName/$mbid") },
                onNavigationToArtistDetails = { artistName -> navController.navigate("artistDetailScreen/$artistName") },
                genreName = genreName
            )
        }

        composable("albumDetailScreen/{albumName}/{mbid}") {
            val albumName = it.arguments?.getString("albumName") ?: ""
            val mbid = it.arguments?.getString("mbid") ?: ""
            AlbumDetailScreen(
                onNavigationToGenreDetails = { genreName -> navController.navigate("genreDetailScreen/$genreName") },
                albumName = albumName,
                mbid = mbid
            )
        }

        composable("artistDetailScreen/{artistName}") {
            val artistName = it.arguments?.getString("artistName") ?: ""
            ArtistDetailScreen(
                onNavigationToAlbumDetails = { albumName -> navController.navigate("albumDetailScreen/$albumName") },
                onNavigationToGenreDetails = { genreName -> navController.navigate("genreDetailScreen/$genreName") },
                artistName = artistName
            )
        }
    }
}