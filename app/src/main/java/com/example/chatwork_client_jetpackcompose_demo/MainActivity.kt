package com.example.chatwork_client_jetpackcompose_demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.chatwork_client_jetpackcompose_demo.ui.theme.Chatwork_client_jetpackcompose_demoTheme
import com.example.chatwork_client_jetpackcompose_demo.view.RoomListScreen
import com.example.chatwork_client_jetpackcompose_demo.view.TimelineScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Chatwork_client_jetpackcompose_demoTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    App()
                }
            }
        }
    }
}

@Composable
fun App() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "roomList") {
        composable("roomList") {
            RoomListScreen(navController, hiltViewModel())
        }
        composable(
            "timeline/{roomName}",
            arguments = listOf(navArgument("roomName") { type = NavType.StringType })
        ) {
            TimelineScreen(hiltViewModel(), it.arguments?.getString("roomName") ?: "")
        }
    }
}
