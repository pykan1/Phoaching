package com.example.phoaching.di

import com.example.phoaching.domain.manager.AuthManager
import com.example.phoaching.domain.manager.AuthManagerImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val managerModule = module {
    singleOf(::AuthManagerImpl) { bind<AuthManager>() }
}