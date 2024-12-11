package com.jozefv.ytbclone.presentation.subscriptions.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.jozefv.ytbclone.R
import com.jozefv.ytbclone.presentation.common.SpacerHorM
import com.jozefv.ytbclone.presentation.common.SpacerVerXS
import com.jozefv.ytbclone.presentation.common.ui.theme.Typography
import com.jozefv.ytbclone.presentation.subscriptions.mappers.VideoUiParcelize

@Composable
fun AuthorSection(modifier: Modifier = Modifier, videoUiParcelize: VideoUiParcelize) {
    Row(modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        SubcomposeAsyncImage(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape),
            model = videoUiParcelize.authorThumbnail,
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            loading = {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(modifier = Modifier.size(16.dp))
                }
            },
            error = {
                Icon(
                    modifier = Modifier.size(16.dp),
                    imageVector = Icons.Default.Clear,
                    contentDescription = null
                )
            }
        )
        SpacerHorM()
        Column(Modifier.fillMaxWidth()) {
            Text(
                style = Typography.bodyLarge,
                text = videoUiParcelize.title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            SpacerVerXS()
            Text(
                style = Typography.headlineSmall,
                text = videoUiParcelize.author,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
@Preview
@Composable
private fun AuthorSectionPreview(){
    MaterialTheme {
        AuthorSection(videoUiParcelize =VideoUiParcelize(
            title = " This is really cool and looooooooooooooooooooooooooooong title",
            description = "Some description",
            author = "Anonym",
            publishedDate = "24.08.2024",
            authorThumbnail = R.drawable.image1,
            image = R.drawable.image1
        )
        )
    }
}