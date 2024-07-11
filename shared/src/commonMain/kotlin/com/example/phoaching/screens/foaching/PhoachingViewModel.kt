package com.example.phoaching.screens.foaching

import com.example.phoaching.domain.models.phoaching.PhoachingTab
import com.example.phoaching.domain.usecases.phoaching.CopyPhoachingUseCase
import com.example.phoaching.domain.usecases.phoaching.DeletePhoachingUseCase
import com.example.phoaching.domain.usecases.phoaching.GetPhoachingsUseCase
import com.example.phoaching.domain.usecases.phoaching.UrlPhoachingUseCase
import com.example.phoaching.platform.BaseScreenModel
import com.example.phoaching.platform.shareUrl
import org.koin.core.component.inject
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce

internal class PhoachingViewModel :
    BaseScreenModel<PhoachingState, PhoachingEvent>(PhoachingState.InitState) {
    private val deletePhoachingsUseCase: DeletePhoachingUseCase by inject()
    private val copyPhoachingUseCase: CopyPhoachingUseCase by inject()
    private val urlPhoachingUseCase: UrlPhoachingUseCase by inject()
    private val getPhoachingUseCase: GetPhoachingsUseCase by inject()

    fun loadPhoaching(tab: PhoachingTab) = intent {
        launchOperation(
            operation = {
                getPhoachingUseCase.execute(GetPhoachingsUseCase.Params(PhoachingTab.My), it)
            },
            success = {
                reduceLocal {
                    state.copy(
                        myPhoachings = it
                    )
                }
            }
        )
    }

    fun changeTab(tab: PhoachingTab) = intent {
        reduceLocal {
            state.copy(currentTab = tab)
        }
        loadPhoaching(tab)
    }

    fun share(id: String) = intent {
        launchOperation(
            operation = {
                urlPhoachingUseCase.execute(UrlPhoachingUseCase.Params(id), it)
            },
            success = {
                println(it)
                println(it)
                shareUrl(it)
            }
        )
    }

    fun confirmCopy() = intent {
        launchOperation(operation = {
            copyPhoachingUseCase.execute(
                CopyPhoachingUseCase.Params(id = state.copyId.orEmpty()),
                it
            )
        }, success = {
            reduceLocal { state.copy(copyId = null) }
            changeTab(state.currentTab)
        })
    }

    fun copy(id: String?) = intent {
        reduce {
            state.copy(copyId = id)
        }
    }

    fun changeIsCode() = intent {
        reduce {
            state.copy(
                isCodeAlert = !state.isCodeAlert
            )
        }
    }

    fun changeCode(it: String) = intent {
        reduce {
            state.copy(
                code = it
            )
        }
    }

    fun confirmDelete() = intent {
        launchOperation(
            operation = {
                deletePhoachingsUseCase.execute(
                    DeletePhoachingUseCase.Params(id = state.deleteId.orEmpty()),
                    it
                )
            }, success = {
                reduceLocal { state.copy(deleteId = null) }
                changeTab(state.currentTab)
            }
        )
    }

    fun deletePhoaching(id: String?) = intent {
        reduce {
            state.copy(
                deleteId = id
            )
        }
    }

    fun sendCode() = intent {

    }
}