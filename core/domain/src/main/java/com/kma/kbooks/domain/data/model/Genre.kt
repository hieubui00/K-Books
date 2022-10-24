package com.kma.kbooks.domain.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Genre(
    val genreId: Int?,

    val name: String?
): Parcelable
