package com.kma.kbooks.domain.data.repository

interface ReaderRepository {

    fun getFontSize(): Float

    fun setFontSize(fontSize: Float)
}
