package com.example.phoaching.di

import com.example.phoaching.data.repository.AuthRepositoryImpl
import com.example.phoaching.data.repository.PhoachingRepositoryImpl
import com.example.phoaching.data.repository.UserRepositoryImpl
import com.example.phoaching.domain.repository.AuthRepository
import com.example.phoaching.domain.repository.PhoachingRepository
import com.example.phoaching.domain.repository.UserRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val repositoryModule = module {
    factoryOf(::AuthRepositoryImpl) { bind<AuthRepository>() }
    factoryOf(::UserRepositoryImpl) { bind<UserRepository>() }
    factoryOf(::PhoachingRepositoryImpl) { bind<PhoachingRepository>() }
}