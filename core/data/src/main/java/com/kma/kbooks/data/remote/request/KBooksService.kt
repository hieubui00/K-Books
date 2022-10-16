package com.kma.kbooks.data.remote.request

import com.kma.kbooks.data.remote.response.StoryDetailsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface KBooksService {

    @GET("/api/stories/{storyId}")
    suspend fun getStoryDetails(@Path("storyId") storyId: Int): StoryDetailsResponse
}