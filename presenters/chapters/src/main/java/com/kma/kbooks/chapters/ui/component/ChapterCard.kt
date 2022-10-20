package com.kma.kbooks.chapters.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.kma.kbooks.domain.data.model.Chapter

@Composable
internal fun ChapterCard(
    modifier: Modifier = Modifier,
    chapter: Chapter,
    onClick: (Chapter) -> Unit
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(size = 8.dp),
        elevation = 8.dp
    ) {
        Text(
            modifier = Modifier
                .clickable { onClick(chapter) }
                .padding(all = 16.dp),
            text = chapter.name.orEmpty(),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.body1
        )
    }
}
