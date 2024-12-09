package com.jozefv.ytbclone.presentation.profile.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.jozefv.ytbclone.presentation.common.SpacerVerS
import com.jozefv.ytbclone.presentation.common.ui.theme.Typography

@Composable
fun ProfileSection(modifier: Modifier = Modifier, title: String, description: String) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(style = Typography.headlineSmall, textAlign = TextAlign.Center, text = title)
        SpacerVerS()
        Text(style = Typography.bodyLarge, textAlign = TextAlign.Center, text = description)
    }
}

@Preview
@Composable
private fun ProfileSectionPreview() {
    MaterialTheme {
        ProfileSection(title = "Account name", description = "John Doe")
    }
}