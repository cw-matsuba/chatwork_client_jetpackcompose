package com.example.chatwork_client_jetpackcompose_demo.api

abstract class NetworkCallHandler<ResultType> {

    suspend fun makeCall(): Result<ResultType> {
        return try {
            val response = getResult()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    protected abstract suspend fun getResult(): ResultType
}
