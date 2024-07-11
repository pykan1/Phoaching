package com.example.phoaching.di

import com.russhwolf.settings.Settings
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


internal val preferencesModule = module {
    singleOf(::Settings)
}