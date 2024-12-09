package com.jozefv.ytbclone.presentation.subscriptions.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.jozefv.ytbclone.R
import com.jozefv.ytbclone.presentation.common.SpacerVerL
import com.jozefv.ytbclone.presentation.common.SpacerVerM
import com.jozefv.ytbclone.presentation.common.ui.theme.Typography
import com.jozefv.ytbclone.presentation.subscriptions.mappers.VideoResultUiParcelize

@Composable
fun VideoDetail(videoResultUiParcelize: VideoResultUiParcelize) {
    val screenHeight = LocalConfiguration.current.screenHeightDp
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        SubcomposeAsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height((screenHeight * 0.6).dp),
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
        SpacerVerL()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            AuthorSection(
                modifier = Modifier
                    .border(1.dp, Color.Blue, RoundedCornerShape(8.dp)),
                videoResultUiParcelize = videoResultUiParcelize
            )
            SpacerVerM()
            Text(
                style = Typography.headlineSmall,
                text = "Published: ${videoResultUiParcelize.publishedDate}"
            )
            SpacerVerM()
            Text(style = Typography.bodyLarge, text = videoResultUiParcelize.description)
        }
    }

}

@Preview
@Composable
private fun VideoDetailPreview() {
    MaterialTheme {
        VideoDetail(
            VideoResultUiParcelize(
                title = "This is title",
                description = "Some cool description",
                author = "Anonym",
                publishedDate = "22.04.2024",
                authorThumbnail = R.drawable.image1,
                image = R.drawable.image1
            )
        )
    }
}