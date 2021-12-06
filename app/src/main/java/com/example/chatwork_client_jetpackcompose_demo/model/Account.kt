package com.example.chatwork_client_jetpackcompose_demo.model

import com.squareup.moshi.Json

data class Account(
    @Json(name = "account_id") val accountId: Long,
    val name: String,
    @Json(name = "avatar_image_url") val avatarImageUrl: String
)
