package com.example.chatwork_client_jetpackcompose_demo.pref

import com.chibatching.kotpref.KotprefModel

object AccountPref : KotprefModel() {
    var chatworkApiToken by stringPref(default = "")

    fun isLogin() = chatworkApiToken.isNotEmpty()
}
