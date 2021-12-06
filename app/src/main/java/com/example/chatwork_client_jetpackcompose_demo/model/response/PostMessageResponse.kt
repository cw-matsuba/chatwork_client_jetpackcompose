package com.example.chatwork_client_jetpackcompose_demo.model.response

import com.squareup.moshi.Json

data class PostMessageResponse(
    @Json(name = "message_id") val messageId: String
)
