package com.jozefv.ytbclone.presentation.subscriptions.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.jozefv.ytbclone.R
import com.jozefv.ytbclone.presentation.common.SpacerVerS
import com.jozefv.ytbclone.presentation.subscriptions.mappers.VideoResultUiParcelize

@Composable
fun VideoItem(
    videoResultUiParcelize: VideoResultUiParcelize,
    onCLick: () -> Unit
) {
    Column(
        Modifier
            .fillMaxWidth()
            .height(400.dp)
            .clickable {
                onCLick()
            }
    ) {
        SubcomposeAsyncImage(
            modifier = Modifier
                .height(300.dp)
                .fillMaxWidth(),
            model = videoResultUiParcelize.image,
            contentDescription = null,
            contentScale = ContentScale.Fit,
            loading = {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(modifier = Modifier.size(40.dp))
                }
            },
            error = {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Image couldn't be loaded")
                }
            }
        )
        SpacerVerS()
        AuthorSection(videoResultUiParcelize = videoResultUiParcelize)
    }
}

@Preview
@Composable
private fun VideoItemPreview() {
    MaterialTheme {
        VideoItem(
            videoResultUiParcelize = VideoResultUiParcelize(
                title = " This is really cool title",
                description = "Some description",
                author = "Anonym",
                publishedDate = "24.08.2024",
                authorThumbnail = R.drawable.image1,
                image = R.drawable.image1
            ),
            onCLick = {}
        )
    }
}