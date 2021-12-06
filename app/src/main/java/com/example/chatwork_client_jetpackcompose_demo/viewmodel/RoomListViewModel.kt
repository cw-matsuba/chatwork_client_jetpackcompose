package com.example.chatwork_client_jetpackcompose_demo.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatwork_client_jetpackcompose_demo.model.Room
import com.example.chatwork_client_jetpackcompose_demo.pref.AccountPref
import com.example.chatwork_client_jetpackcompose_demo.repository.RoomRepository
import com.example.chatwork_client_jetpackcompose_demo.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class RoomListViewModel @Inject constructor(
    private val roomRepository: RoomRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    val uiState: MutableState<UiState> = mutableStateOf(UiState.Loading)

    init {
        fetchData()
    }

    private fun fetchData() {
        if (AccountPref.isLogin()) {
            viewModelScope.launch {
                roomRepository.getRooms()
                    .onSuccess {
                        uiState.value = UiState.Content(it)
                    }
                    .onFailure {
                        // 認証に失敗していたら未ログイン状態にする
                        if (it is HttpException && it.code() == 401) {
                            AccountPref.chatworkApiToken = ""
                            uiState.value = UiState.NoLogin
                        }
                    }
            }
        } else {
            uiState.value = UiState.NoLogin
        }
    }

    fun confirmUser(token: String) {
        uiState.value = UiState.Loading
        AccountPref.chatworkApiToken = token
        viewModelScope.launch {
            userRepository.getMe()
                .onSuccess {
                    fetchData()
                }
                .onFailure {
                    uiState.value = UiState.NoLogin
                }
        }
    }

    fun logout() {
        AccountPref.chatworkApiToken = ""
        uiState.value = UiState.NoLogin
    }

    sealed class UiState {
        object Loading : UiState()
        object NoLogin : UiState()
        data class Content(val roomList: List<Room> = emptyList()) : UiState()
    }
}
