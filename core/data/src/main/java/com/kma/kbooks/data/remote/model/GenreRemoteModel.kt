package com.kma.kbooks.data.remote.model

import com.kma.kbooks.domain.data.model.Genre

data class GenreRemoteModel(
    val genreId: Int?,

    val name: String?
)

internal fun GenreRemoteModel.asEntity(): Genre = Genre(
    genreId = this.genreId,
    name = this.name
)