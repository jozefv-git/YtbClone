package com.jozefv.ytbclone.presentation.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.jozefv.ytbclone.presentation.common.SpacerVerL
import com.jozefv.ytbclone.presentation.common.SpacerVerM
import com.jozefv.ytbclone.presentation.profile.components.ProfileSection
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileScreenRoot(onLogoutClick: () -> Unit, viewModel: ProfileViewModel = koinViewModel()) {
    ProfileScreen(
        profileState = viewModel.state,
        onLogoutClick = { action ->
            when (action) {
                is ProfileAction.onLogoutClick -> {
                    viewModel.onAction(action)
                    onLogoutClick()
                }
            }
        }
    )
}

@Composable
private fun ProfileScreen(profileState: ProfileState, onLogoutClick: (ProfileAction) -> Unit) {
    profileState.profile?.let { profile ->
        Box(
            Modifier
                .fillMaxSize()
                .padding(vertical = 32.dp, horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
        ) {
            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                SubcomposeAsyncImage(
                    modifier = Modifier
                        .size(200.dp)
                        .clip(CircleShape),
                    model = profile.image,
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    loading = {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(modifier = Modifier.size(40.dp))
                        }
                    },
                    error = {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "Image couldn't be loaded")
                        }
                    }
                )
                SpacerVerL()
                ProfileSection(title = "Email", description = profile.email)
                SpacerVerM()
                ProfileSection(title = "Account name", description = profile.accountName)
                SpacerVerM()
                ProfileSection(title = "Bio", description = profile.bio)
            }
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter),
                onClick = { onLogoutClick(ProfileAction.onLogoutClick) }) {
                Text(text = "Logout")
            }
        }

    }
}

@Preview
@Composable
private fun ProfileScreenPreview() {
    MaterialTheme {
        ProfileScreen(
            profileState = ProfileState(),
            onLogoutClick = {}
        )
    }
}