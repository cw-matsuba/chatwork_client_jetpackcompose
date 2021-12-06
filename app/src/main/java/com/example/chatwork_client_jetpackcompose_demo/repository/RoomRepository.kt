package com.example.chatwork_client_jetpackcompose_demo.repository

import com.example.chatwork_client_jetpackcompose_demo.api.ChatworkApi
import com.example.chatwork_client_jetpackcompose_demo.api.NetworkCallHandler
import com.example.chatwork_client_jetpackcompose_demo.model.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RoomRepository @Inject constructor(private val chatworkApi: ChatworkApi) {

    suspend fun getRooms(): Result<List<Room>> = withContext(Dispatchers.IO) {
        object : NetworkCallHandler<List<Room>>() {
            override suspend fun getResult(): List<Room> {
                return chatworkApi.getRooms()
            }
        }.makeCall()
    }
}
