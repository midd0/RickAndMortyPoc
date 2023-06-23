package com.rickandmortypoc.ui.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rickandmortypoc.ui.SplashScreen
import com.rickandmortypoc.ui.detail.CharacterDetailScreen
import com.rickandmortypoc.ui.list.CharactersListScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = ScreenNav.Splash.route
    ) {
        composable(ScreenNav.Splash.route) {
            SplashScreen {
                navController.navigate(ScreenNav.Home.route) {
                    popUpTo(ScreenNav.Splash.route) { inclusive = true }
                }
            }
        }
        composable(ScreenNav.Home.route) {
            CharactersListScreen {
                navController.navigate(ScreenNav.Detail.route + "/$it")
            }
        }
        composable(ScreenNav.Detail.route + "/{characterId}") {
            CharacterDetailScreen(characterId = it.arguments?.getString("characterId").orEmpty()) {
                navController.popBackStack()
            }
        }
    }
}