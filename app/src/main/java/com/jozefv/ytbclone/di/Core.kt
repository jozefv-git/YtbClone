package com.jozefv.ytbclone.di

import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.jozefv.ytbclone.data.EncryptedSessionStorage
import com.jozefv.ytbclone.data.videos.VideoDataSourceImp
import com.jozefv.ytbclone.data.videos.VideoRepositoryImpl
import com.jozefv.ytbclone.data.videos.videoSource
import com.jozefv.ytbclone.domain.SessionStorage
import com.jozefv.ytbclone.domain.VideoDataSource
import com.jozefv.ytbclone.domain.VideoRepository
import com.jozefv.ytbclone.presentation.subscriptions.SubscriptionViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val coreModule = module {
    single { videoSource }
    singleOf(::VideoDataSourceImp).bind<VideoDataSource>()
    singleOf(::VideoRepositoryImpl).bind<VideoRepository>()

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
}