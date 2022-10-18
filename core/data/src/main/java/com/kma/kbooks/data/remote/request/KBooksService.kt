package com.kma.kbooks.data.remote.request

import com.kma.kbooks.data.remote.response.StoriesResponse
import com.kma.kbooks.data.remote.response.StoryDetailsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface KBooksService {

    @GET("/api/stories")
    suspend fun getStories(
        @Query("status") status: String? = null,
        @Query("sort") sort: String? = null,
        @Query("page") page: Int? = 1
    ): StoriesResponse

    @GET("/api/stories/{storyId}")
    suspend fun getStoryDetails(@Path("storyId") storyId: Int): StoryDetailsResponse
}
