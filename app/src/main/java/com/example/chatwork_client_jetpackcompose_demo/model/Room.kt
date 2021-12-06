package com.example.chatwork_client_jetpackcompose_demo.model

import com.squareup.moshi.Json

data class Room(
    @Json(name = "room_id") val roomId: Long,
    val name: String,
    @Json(name = "unread_num") val unreadNum: Int,
    @Json(name = "icon_path") val iconPath: String,
    @Json(name = "last_update_time") val lastUpdateTime: Int
)
