package com.example.phoaching.di

import com.example.phoaching.data.api.AuthApi
import com.example.phoaching.data.api.PhoachingApi
import com.example.phoaching.data.api.UserApi
import de.jensklingenberg.ktorfit.Ktorfit
import org.koin.dsl.module

val apiModule = module {
    factory { get<Ktorfit>().create<AuthApi>() }
    factory { get<Ktorfit>().create<UserApi>() }
    factory { get<Ktorfit>().create<PhoachingApi>() }
}