package com.kma.kbooks.story.details.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.kma.kbooks.domain.data.model.StoryDetails
import com.kma.kbooks.resources.ui.theme.KBooksTheme
import com.kma.kbooks.story.details.R
import com.kma.kbooks.story.details.util.toString
import java.util.Date

@Composable
internal fun MetadataSection(
    modifier: Modifier = Modifier,
    storyDetails: StoryDetails?
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        val publishedAt = storyDetails?.publishedAt?.toString("yyyy-MM-dd").orEmpty()
        val rating = "${storyDetails?.rating ?: 0}/10"
        val view = "${storyDetails?.view ?: 0}"

        Metadata(metadata = stringResource(R.string.publish_date) to publishedAt)

        Metadata(metadata = stringResource(R.string.rating) to rating)

        Metadata(metadata = stringResource(R.string.view) to view)

        Metadata(metadata = stringResource(R.string.status) to "Đã hoàn thành")
    }
}

@Preview
@Composable
private fun MetadataSectionPreview() {
    KBooksTheme {
        MetadataSection(
            modifier = Modifier.background(Color.White),
            storyDetails = StoryDetails(
                storyId = 1,
                title = "Tôi thấy hoa vàng trên cỏ xanh",
                author = "Nguyễn Nhật Ánh",
                thumbnail = null,
                summary = null,
                view = 5555,
                rating = 8.0f,
                status = "COMPLETED",
                genres = null,
                publishedAt = Date()
            )
        )
    }
}
