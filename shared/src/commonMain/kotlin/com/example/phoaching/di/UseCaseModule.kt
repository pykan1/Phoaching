package com.example.phoaching.di

import com.example.phoaching.domain.usecases.auth.FastAuthorizationUseCase
import com.example.phoaching.domain.usecases.auth.GetCodeUseCase
import com.example.phoaching.domain.usecases.auth.LoginUseCase
import com.example.phoaching.domain.usecases.auth.RegisterUseCase
import com.example.phoaching.domain.usecases.auth.VerifyCodeUseCase
import com.example.phoaching.domain.usecases.phoaching.CopyPhoachingUseCase
import com.example.phoaching.domain.usecases.phoaching.CreatePhoachingUseCase
import com.example.phoaching.domain.usecases.phoaching.DeletePhoachingUseCase
import com.example.phoaching.domain.usecases.phoaching.GetDetailPhoachingUseCase
import com.example.phoaching.domain.usecases.phoaching.GetPhoachingsUseCase
import com.example.phoaching.domain.usecases.phoaching.UpdatePhoachingUseCase
import com.example.phoaching.domain.usecases.phoaching.UrlPhoachingUseCase
import com.example.phoaching.domain.usecases.user.ChangeAvatarUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val useCaseModule = module {
    factoryOf(::FastAuthorizationUseCase)
    factoryOf(::GetCodeUseCase)
    factoryOf(::LoginUseCase)
    factoryOf(::RegisterUseCase)
    factoryOf(::ChangeAvatarUseCase)
    factoryOf(::VerifyCodeUseCase)

    /**
     * Phoachings
     * */
    factoryOf(::CopyPhoachingUseCase)
    factoryOf(::CreatePhoachingUseCase)
    factoryOf(::DeletePhoachingUseCase)
    factoryOf(::GetDetailPhoachingUseCase)
    factoryOf(::GetPhoachingsUseCase)
    factoryOf(::UpdatePhoachingUseCase)
    factoryOf(::UrlPhoachingUseCase)

}