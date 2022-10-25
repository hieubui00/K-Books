package com.kma.kbooks.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kma.kbooks.data.local.model.StoryLocalModel

@Dao
interface StoryDao {

    @Query("SELECT * FROM stories LIMIT :limit OFFSET :offset")
    suspend fun getStories(limit: Int, offset: Int): List<StoryLocalModel>

    @Query("SELECT * FROM stories WHERE storyId = :storyId LIMIT 1")
    suspend fun getStory(storyId: Int): StoryLocalModel?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertStory(vararg stories: StoryLocalModel)

    @Delete
    suspend fun deleteStory(story: StoryLocalModel)
}
