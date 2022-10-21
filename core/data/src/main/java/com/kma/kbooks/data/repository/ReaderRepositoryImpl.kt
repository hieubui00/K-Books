package com.kma.kbooks.data.repository

import com.kma.kbooks.data.manager.ReaderManager
import com.kma.kbooks.domain.data.repository.ReaderRepository
import javax.inject.Inject

class ReaderRepositoryImpl @Inject constructor(
    private val readerManager: ReaderManager
) : ReaderRepository {

    override fun getFontSize(): Float = readerManager.fontSize

    override fun setFontSize(fontSize: Float) {
        readerManager.fontSize = fontSize
    }
}