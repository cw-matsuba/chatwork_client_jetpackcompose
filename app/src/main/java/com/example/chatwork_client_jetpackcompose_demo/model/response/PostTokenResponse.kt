package com.example.chatwork_client_jetpackcompose_demo.model.response

import com.squareup.moshi.Json

data class PostTokenResponse(
    @Json(name = "access_token") val accessToken: String
)
