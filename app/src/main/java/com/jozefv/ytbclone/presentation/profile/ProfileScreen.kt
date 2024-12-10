package com.jozefv.ytbclone.presentation.profile

import CustomScaffold
import CustomToolBar
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.jozefv.ytbclone.R
import com.jozefv.ytbclone.presentation.common.EventObserver
import com.jozefv.ytbclone.presentation.common.SpacerVerL
import com.jozefv.ytbclone.presentation.common.SpacerVerM
import com.jozefv.ytbclone.presentation.common.components.CustomAlertDialog
import com.jozefv.ytbclone.presentation.profile.components.ProfileSection
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileScreenRoot(onLogoutClick: () -> Unit, viewModel: ProfileViewModel = koinViewModel()) {
    EventObserver(flow = viewModel.channel) { event ->
        if (event is ProfileEvent.OnLogoutSuccess) {
            onLogoutClick()
        }
    }
    ProfileScreen(
        profileState = viewModel.state,
        onAction = { action ->
            if (action is ProfileAction.onLogoutClick) {
                viewModel.onAction(action)
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ProfileScreen(profileState: ProfileState, onAction: (ProfileAction) -> Unit) {
    var isDialogVisible by remember {
        mutableStateOf(false)
    }
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    profileState.profile?.let { profile ->
        CustomScaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topAppBar = {
                CustomToolBar(scrollBehavior = scrollBehavior, title = " Profile")
            }) { padding ->
            Box(
                Modifier
                    .fillMaxSize()
                    .padding(padding)
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
                        contentScale = ContentScale.Crop,
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
                    onClick = {
                        isDialogVisible = !isDialogVisible
                    }) {
                    Text(text = "Logout")
                }
            }

            if (isDialogVisible) {
                CustomAlertDialog(
                    dialogTitle = "Logout",
                    dialogText = "Are you sure you want to log out?",
                    dialogIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_logout_24),
                            contentDescription = null
                        )
                    },
                    onDismiss = { isDialogVisible = !isDialogVisible },
                    onConfirm = {
                        onAction(ProfileAction.onLogoutClick)
                        isDialogVisible = !isDialogVisible
                    })
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
            onAction = {}
        )
    }
}