package com.example.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.ui.news_screen.NewsScreen
import com.example.newsapp.ui.news_screen.NewsScreenViewModel
import com.example.newsapp.ui.theme.NewsAppTheme
import com.example.newsapp.util.NavGraphSetup
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsAppTheme {
                Surface(
                    color = MaterialTheme.colorScheme.onBackground
                ){
                    val navController = rememberNavController()
                    NavGraphSetup(navController = navController)
                }
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun Prev1(){
//    NewsScreen()
//}
