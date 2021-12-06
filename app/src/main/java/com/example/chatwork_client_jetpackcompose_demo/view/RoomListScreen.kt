package com.example.chatwork_client_jetpackcompose_demo.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.chatwork_client_jetpackcompose_demo.model.Room
import com.example.chatwork_client_jetpackcompose_demo.ui.theme.ListTextStyle
import com.example.chatwork_client_jetpackcompose_demo.viewmodel.RoomListViewModel

@Composable
fun RoomListScreen(
    navController: NavController,
    roomListViewModel: RoomListViewModel = viewModel()
) {
    val uiState: RoomListViewModel.UiState by roomListViewModel.uiState

    Scaffold(
        topBar = {
            AppBar(onClickLogout = {
                roomListViewModel.logout()
            })
        }
    ) {
        when (uiState) {
            is RoomListViewModel.UiState.NoLogin -> {
                InputTokenDialog(setToken = {
                    roomListViewModel.confirmUser(it)
                })
            }
            is RoomListViewModel.UiState.Content -> {
                RoomListBody(
                    (uiState as RoomListViewModel.UiState.Content).roomList,
                    navController
                )
            }
            is RoomListViewModel.UiState.Loading -> {
            }
        }
    }
}

// ツールバー
@Composable
fun AppBar(onClickLogout: () -> Unit) {
    TopAppBar(
        title = {
            Box(
                Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "ルーム一覧",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .align(alignment = Alignment.CenterStart)
                )
                Button(
                    onClick = { onClickLogout() },
                    modifier = Modifier.align(alignment = Alignment.CenterEnd)
                ) {
                    Text(text = "ログアウト")
                }
            }
        },
    )
}

// ルーム一覧
@Composable
fun RoomListBody(
    roomList: List<Room>,
    navController: NavController,
) {
    LazyColumn(
        Modifier.fillMaxSize()
    ) {
        roomList.forEach {
            item {
                RoomItem(
                    it,
                    onClick = {
                        navController.navigate("timeline/${it.name}")
                    }
                )
            }
        }
    }
}

// ルーム一覧内アイテム
@Composable
fun RoomItem(
    room: Room,
    onClick: (room: Room) -> Unit,
) {
    Surface(
        Modifier
            .fillMaxWidth()
            .clickable { onClick(room) }
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        Row {
            Image(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape),
                painter = rememberImagePainter(room.iconPath),
                contentScale = ContentScale.FillBounds,
                contentDescription = "roomIcon",
            )
            Text(
                text = room.name,
                style = ListTextStyle,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .align(alignment = Alignment.CenterVertically)
                    .padding(start = 8.dp),
            )
        }
    }
}

// トークン入力ダイアログ
@Composable
fun InputTokenDialog(setToken: (token: String) -> Unit) {
    var token by remember { mutableStateOf("") }
    val openDialog = remember { mutableStateOf(true) }
    if (openDialog.value) {
        AlertDialog(
            title = {
                Text(text = "アクセストークンを入力してください")
            },
            text = {
                Column {
                    TextField(
                        value = token,
                        onValueChange = { token = it }
                    )
                }
            },
            buttons = {
                Row(
                    modifier = Modifier.padding(start = 12.dp, end = 12.dp, bottom = 12.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            openDialog.value = false
                            setToken(token)
                        }
                    ) {
                        Text(text = "OK")
                    }
                }
            },
            onDismissRequest = {}
        )
    }
}
