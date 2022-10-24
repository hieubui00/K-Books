package com.kma.kbooks.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kma.kbooks.data.local.model.ChapterLocalModel

@Dao
interface ChapterDao {

    @Query("SELECT * FROM chapters WHERE storyId = :storyId LIMIT :limit OFFSET :offset")
    suspend fun getChapterByStoryId(storyId: Int, limit: Int, offset: Int): List<ChapterLocalModel>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertChapter(vararg chapters: ChapterLocalModel)
}