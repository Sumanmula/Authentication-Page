package com.example.firstapplication.appnavigation

import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.*
import com.example.firstapplication.view.*
import com.example.firstapplication.viewmodel.MainViewModel

@Composable
fun AppNavigation(
    navController: NavHostController,
    viewModel: MainViewModel
) {
    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(onLoginSuccess = {
                navController.navigate("main") {
                    popUpTo("login") {
                        inclusive = true
                    }
                }
            })
        }
        composable("main") {
            MainScreen(
                viewModel = viewModel,
                onPersonClick = { index ->
                    navController.navigate("playerDetails/$index")
                }
            )
        }
        composable(
            route = "playerDetails/{index}",
            arguments = listOf(navArgument("index") { type = NavType.IntType })
        ) { backStackEntry ->
            val index = backStackEntry.arguments?.getInt("index") ?: 0
            val personList = viewModel.playerList.value
            personList?.getOrNull(index)?.let { person ->
                PlayerDetailsScreen(person = person)
            }
        }
    }
}