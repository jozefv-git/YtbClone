package com.jozefv.ytbclone.di

import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.jozefv.ytbclone.MainViewModel
import com.jozefv.ytbclone.data.AuthRepositoryImp
import com.jozefv.ytbclone.data.EncryptedSessionStorage
import com.jozefv.ytbclone.data.profile.data_source.ProfileDataSourceImpl
import com.jozefv.ytbclone.data.profile.ProfileRepositoryImpl
import com.jozefv.ytbclone.data.profile.data_source.profileSource
import com.jozefv.ytbclone.data.videos.data_source.VideoDataSourceImp
import com.jozefv.ytbclone.data.videos.VideoRepositoryImpl
import com.jozefv.ytbclone.data.videos.data_source.videoSource
import com.jozefv.ytbclone.domain.repository.AuthRepository
import com.jozefv.ytbclone.domain.data_source.ProfileDataSource
import com.jozefv.ytbclone.domain.repository.ProfileRepository
import com.jozefv.ytbclone.domain.SessionStorage
import com.jozefv.ytbclone.domain.data_source.VideoDataSource
import com.jozefv.ytbclone.domain.repository.VideoRepository
import com.jozefv.ytbclone.presentation.login.LoginScreenViewModel
import com.jozefv.ytbclone.presentation.profile.ProfileViewModel
import com.jozefv.ytbclone.presentation.subscriptions.SubscriptionViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val coreModule = module {
    // Videos data
    single { videoSource }
    singleOf(::VideoDataSourceImp).bind<VideoDataSource>()
    singleOf(::VideoRepositoryImpl).bind<VideoRepository>()
    // Profile data
    single {
        profileSource
    }
    singleOf(::ProfileDataSourceImpl).bind<ProfileDataSource>()
    singleOf(::ProfileRepositoryImpl).bind<ProfileRepository>()
    // Auth
    singleOf(::AuthRepositoryImp).bind<AuthRepository>()

    // This will be provided by koin to our EncryptedSessionStorage
    single<SharedPreferences> {
        EncryptedSharedPreferences(
            context = androidApplication(), // Application context from koin
            fileName = "auth_prefs",
            masterKey = MasterKey(context = androidApplication()),
            // What algorithms we want for the data encryption
            prefKeyEncryptionScheme = EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            prefValueEncryptionScheme = EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    singleOf(::EncryptedSessionStorage).bind<SessionStorage>()

    viewModelOf(::SubscriptionViewModel)
    viewModelOf(::ProfileViewModel)
    viewModelOf(::LoginScreenViewModel)
    viewModelOf(::MainViewModel)
}