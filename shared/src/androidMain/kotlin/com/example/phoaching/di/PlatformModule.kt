package com.example.phoaching.di

import com.example.phoaching.platform.MultipartManager
import com.example.phoaching.platform.MultipartManagerImpl
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.bind
import org.koin.dsl.module

val platformModule = module {
    singleOf(::MultipartManagerImpl) { bind<MultipartManager>() }
}