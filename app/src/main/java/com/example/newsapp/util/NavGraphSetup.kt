package com.example.newsapp.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.newsapp.ui.article_screen.ArticleScreen
import com.example.newsapp.ui.news_screen.NewsScreen
import com.example.newsapp.ui.news_screen.NewsScreenViewModel

@Composable
fun NavGraphSetup(
    modifier: Modifier = Modifier,
    navController: NavHostController
){
    NavHost(
        navController = navController,
        startDestination = "news_screen"
    ) {
        composable(route = "news_screen" ) {
            val viewModel: NewsScreenViewModel = hiltViewModel()
            NewsScreen(
                state = viewModel.state,
                onEvent = viewModel::onEvent,
                onReadFullStoryButtonClicked = {url ->
                    navController.navigate("article_screen?web_url=$url")
                }
            )
        }
        composable(
            route = "article_screen?web_url={web_url}",
            arguments = listOf(navArgument(name = "web_url"){
                type = NavType.StringType
            })
        ){backStackEntry ->
            ArticleScreen(
                url = backStackEntry.arguments?.getString("web_url"),
                onBackPressed = { navController.navigateUp() }
            )
        }
        
    }
}