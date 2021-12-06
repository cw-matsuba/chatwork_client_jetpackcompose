package com.example.chatwork_client_jetpackcompose_demo.view

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.chatwork_client_jetpackcompose_demo.ui.compose.AppBar
import com.example.chatwork_client_jetpackcompose_demo.viewmodel.TimelineViewModel

@Composable
fun TimelineScreen(
    timelineViewModel: TimelineViewModel = viewModel(),
    title: String
) {
    Scaffold(
        topBar = { AppBar(title) }
    ) {

    }
}