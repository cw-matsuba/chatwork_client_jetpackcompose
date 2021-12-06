package com.example.chatwork_client_jetpackcompose_demo.repository

import com.example.chatwork_client_jetpackcompose_demo.api.ChatworkApi
import com.example.chatwork_client_jetpackcompose_demo.api.NetworkCallHandler
import com.example.chatwork_client_jetpackcompose_demo.model.Account
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRepository @Inject constructor(private val chatworkApi: ChatworkApi) {

    suspend fun getMe(): Result<Account> = withContext(Dispatchers.IO) {
        object : NetworkCallHandler<Account>() {
            override suspend fun getResult(): Account {
                return chatworkApi.me()
            }
        }.makeCall()
    }
}
