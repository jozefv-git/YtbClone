package com.jozefv.ytbclone.di

import com.jozefv.ytbclone.data.videos.VideoDataSourceImp
import com.jozefv.ytbclone.data.videos.VideoRepositoryImpl
import com.jozefv.ytbclone.data.videos.videoSource
import com.jozefv.ytbclone.domain.VideoDataSource
import com.jozefv.ytbclone.domain.VideoRepository
import com.jozefv.ytbclone.presentation.subscriptions.SubscriptionViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val coreModule = module {
    single { videoSource }
    singleOf(::VideoDataSourceImp).bind<VideoDataSource>()
    singleOf(::VideoRepositoryImpl).bind<VideoRepository>()

    viewModelOf(::SubscriptionViewModel)
}