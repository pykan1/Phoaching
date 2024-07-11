package com.example.phoaching.screens.foaching.detailPhoaching

import com.example.phoaching.data.repository.PhoachingRepositoryImpl
import com.example.phoaching.domain.models.phoaching.PhoachingTab
import com.example.phoaching.domain.usecases.phoaching.GetDetailPhoachingUseCase
import com.example.phoaching.platform.BaseScreenModel
import org.koin.core.component.inject
import org.orbitmvi.orbit.syntax.simple.intent

internal class DetailPhoachingViewModel :
    BaseScreenModel<DetailPhoachingState, DetailPhoachingEvent>(DetailPhoachingState.InitState) {

    private val detailPhoachingUseCase: GetDetailPhoachingUseCase by inject()
    private val phoachingRepositoryImpl: PhoachingRepositoryImpl by inject()

    fun loadPhoaching(id: String, tab: PhoachingTab?) = intent {
        launchOperation(
            operation = {
                detailPhoachingUseCase.execute(
                    GetDetailPhoachingUseCase.Params(
                        id,
                        tab ?: PhoachingTab.My
                    ), it
                )
            },
            success = {
                reduceLocal {
                    state.copy(
                        phoachingUI = it
                    )
                }
            }
        )
        launchOperation(operation = {
            phoachingRepositoryImpl.getPhoachingTasks(phoachingId = id)
        }, success = {
            reduceLocal {
                state.copy(
                    tasks = it
                )
            }
        })
    }

    fun deleteLesson(id: String) = intent {
        launchOperation(
            operation = {
                phoachingRepositoryImpl.deleteTask(phoachingId = state.phoachingUI.id, taskId = id)
            },
            success = {
                launchOperation(operation = {
                    phoachingRepositoryImpl.getPhoachingTasks(phoachingId = state.phoachingUI.id)
                }, success = {
                    reduceLocal {
                        state.copy(
                            tasks = it
                        )
                    }
                })
            }
        )
    }

}