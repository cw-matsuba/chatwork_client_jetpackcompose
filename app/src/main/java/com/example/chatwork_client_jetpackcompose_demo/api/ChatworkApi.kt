package com.example.chatwork_client_jetpackcompose_demo.api

import com.example.chatwork_client_jetpackcompose_demo.model.Account
import com.example.chatwork_client_jetpackcompose_demo.model.Room
import com.example.chatwork_client_jetpackcompose_demo.model.response.PostMessageResponse
import retrofit2.http.*

interface ChatworkApi {
    @GET("v2/me")
    suspend fun me(): Account

    @GET("v2/rooms")
    suspend fun getRooms(): List<Room>

    @FormUrlEncoded
    @POST
    suspend fun postMessage(
        @Path("roomId") roomId: Long,
        @Field("body") body: String
    ): PostMessageResponse
}
