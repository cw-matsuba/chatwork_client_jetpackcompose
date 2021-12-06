package com.example.chatwork_client_jetpackcompose_demo.di

import com.example.chatwork_client_jetpackcompose_demo.api.ChatworkApi
import com.example.chatwork_client_jetpackcompose_demo.repository.RoomRepository
import com.example.chatwork_client_jetpackcompose_demo.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun provideRoomRepository(chatworkApi: ChatworkApi) = RoomRepository(chatworkApi)

    @Provides
    @Singleton
    fun provideUserRepository(chatworkApi: ChatworkApi) = UserRepository(chatworkApi)
}
