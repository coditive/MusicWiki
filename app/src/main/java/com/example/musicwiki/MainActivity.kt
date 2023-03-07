package com.example.musicwiki

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.musicwiki.album.AlbumDetailScreen
import com.example.musicwiki.artist.ArtistDetailScreen
import com.example.musicwiki.genre.GenreDetailScreen
import com.example.musicwiki.home.WelcomeScreen
import com.example.musicwiki.ui.theme.MusicWikiTheme
import com.example.musicwiki.viewmodel.WelcomeViewModel

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<WelcomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MusicWikiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    NavHostComposable(viewModel = viewModel)
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
    viewModel: WelcomeViewModel
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable("welcome") {
            WelcomeScreen(
                onNavigation = { navController.navigate("genreDetailScreen") },
                viewModel = viewModel
            )
        }

        composable("genreDetailScreen") {
            GenreDetailScreen(
                onNavigationToAlbumDetails = { navController.navigate("albumDetailScreen") },
                onNavigationToArtistDetails = { navController.navigate("artistDetailScreen") },
                viewModel = viewModel
            )
        }

        composable("albumDetailScreen") {
            AlbumDetailScreen(
                onNavigationToAlbumDetails = { navController.navigate("genreDetailScreen") },
                viewModel = viewModel
            )
        }

        composable("artistDetailScreen") {
            ArtistDetailScreen(
                onNavigation = { navController.navigate("albumDetailScreen") },
                viewModel = viewModel
            )
        }
    }
}