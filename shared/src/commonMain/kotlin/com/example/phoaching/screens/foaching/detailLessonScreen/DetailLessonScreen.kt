package com.example.phoaching.screens.foaching.detailLessonScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import com.example.phoaching.uikit.PhoachingPageContainer

class DetailLessonScreen(private val lessonId: String, private val phoachingId: String): Screen {

    @Composable
    override fun Content() {
        val viewModel = rememberScreenModel { DetailLessonViewModel() }
        val state = viewModel.stateFlow.collectAsState()
        LaunchedEffect(viewModel) {
            viewModel.loadLesson(lessonId, phoachingId)
        }

        PhoachingPageContainer {

        }
    }
}