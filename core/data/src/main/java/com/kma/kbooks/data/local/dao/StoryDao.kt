package com.kma.kbooks.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kma.kbooks.data.local.model.StoryLocalModel

@Dao
interface StoryDao {

    @Query("SELECT * FROM stories")
    suspend fun getStories(): List<StoryLocalModel>

    @Query("SELECT * FROM stories WHERE storyId = :storyId LIMIT 1")
    suspend fun getStory(storyId: Int): StoryLocalModel?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertStory(vararg stories: StoryLocalModel)
}