package com.example.chatwork_client_jetpackcompose_demo.api

import android.util.Log
import com.example.chatwork_client_jetpackcompose_demo.pref.AccountPref
import okhttp3.Interceptor
import okhttp3.Response
import java.lang.Exception

class ApiRequestInterceptor : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()

        if (AccountPref.chatworkApiToken.isNotEmpty()) {
            try {
                builder.addHeader("X-ChatWorkToken", AccountPref.chatworkApiToken)
            } catch (e: Exception) {
                Log.e("bad header", e.message ?: "unknown error")
            }
        }

        val request = builder.build()
        return chain.proceed(request)
    }
}
