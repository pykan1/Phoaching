package com.example.phoaching.di

import com.example.phoaching.platform.permission.platformPermissionsModule
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.module.Module

object KoinInjector {

    val koinApp = startKoin {
        loadKoinModules(
            listOf(
                apiModule,
                managerModule,
                networkModule,
                useCaseModule,
                repositoryModule,
                preferencesModule,
                platformPermissionsModule
            )
        )
    }

    val koin = koinApp.koin

    fun loadModules(modules: List<Module>) {
        koinApp.koin.loadModules(modules)
    }



}