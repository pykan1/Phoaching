package com.example.phoaching.screens.foaching.createPhoaching

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.phoaching.domain.models.phoaching.PhoachingTab
import com.example.phoaching.ext.isNotNullOrZero
import com.example.phoaching.strings.PhoachingResourceStrings
import com.example.phoaching.uikit.PhoachingButton
import com.example.phoaching.uikit.PhoachingPageContainer
import com.example.phoaching.uikit.PhoachingTextField
import com.example.phoaching.uikit.PhoachingTopBar
import com.example.phoaching.uikit.theme.PhoachingTheme
import kotlinx.coroutines.launch

/**
 * [id] - если null, то создание, если не null, то реадактирование
 * */
class CreatePhoachingScreen(private val id: String?, private val phoachingTab: PhoachingTab) : Screen {

    @Composable
    override fun Content() {
        PhoachingPageContainer {
            val viewModel = rememberScreenModel { CreatePhoachingViewModel() }
            val state by viewModel.stateFlow.collectAsState()
            val navigator = LocalNavigator.currentOrThrow
            LaunchedEffect(viewModel) {
                viewModel.loadPhoachingId(id, tab = phoachingTab)
                launch {
                    viewModel.container.sideEffectFlow.collect { event ->
                        when (event) {
                            CreatePhoachingEvent.Success -> {
                                navigator.pop()
                            }
                        }

                    }
                }
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                PhoachingTopBar(
                    modifier = Modifier.fillMaxWidth().padding(top = 13.dp),
                    title = if (id == null) PhoachingResourceStrings.create_phoaching else PhoachingResourceStrings.edit_phoaching,
                    backIcon = true,
                    infoIcon = true,
                    backIconClick = {
                        navigator.pop()
                    },
                    infoIconClick = {}
                )
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    contentPadding = PaddingValues(top = 13.dp)
                ) {
                    item {
                        PhoachingTextField(
                            value = state.title,
                            placeholder = PhoachingResourceStrings.title,
                            onChangeValue = { viewModel.changeTitle(it) },
                            modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
                            label = { state, isFocused ->
                                Text(
                                    text = PhoachingResourceStrings.named_phoaching,
                                    style = PhoachingTheme.typography.regular.copy(
                                        color = PhoachingTheme.colors.uiKit.textColor,
                                        fontSize = 14.sp,
                                    ),
                                    modifier = Modifier.padding(bottom = 10.dp)
                                )
                            }
                        )
                    }

                    item {
                        PhoachingTextField(
                            value = state.description,
                            placeholder = PhoachingResourceStrings.description,
                            onChangeValue = { viewModel.changeDescription(it) },
                            modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
                            label = { state, isFocused ->
                                Text(
                                    text = PhoachingResourceStrings.create_short_description,
                                    style = PhoachingTheme.typography.regular.copy(
                                        color = PhoachingTheme.colors.uiKit.textColor,
                                        fontSize = 14.sp,
                                    ),
                                    modifier = Modifier.padding(bottom = 10.dp)
                                )
                            }
                        )
                    }

                    item {
                        PhoachingTextField(
                            value = state.author,
                            onChangeValue = { viewModel.changeAuthor(it) },
                            placeholder = PhoachingResourceStrings.author,
                            modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
                            label = { state, isFocused ->
                                Text(
                                    text = PhoachingResourceStrings.provide_author,
                                    style = PhoachingTheme.typography.regular.copy(
                                        color = PhoachingTheme.colors.uiKit.textColor,
                                        fontSize = 14.sp,
                                    ),
                                    modifier = Modifier.padding(bottom = 10.dp)
                                )
                            }
                        )
                    }

                    item {
                        PhoachingTextField(
                            value = state.wrongUntilRepeat?.toString().orEmpty(),
                            onChangeValue = { viewModel.changeWrongUtilRepeat(it) },
                            modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
                            label = { state, isFocused ->
                                Text(
                                    text = PhoachingResourceStrings.wrong_until_repeat,
                                    style = PhoachingTheme.typography.regular.copy(
                                        color = PhoachingTheme.colors.uiKit.textColor,
                                        fontSize = 14.sp,
                                    ),
                                    modifier = Modifier.padding(bottom = 10.dp)
                                )
                            }
                        )
                    }

                    item {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth().padding(top = 10.dp)
                        ) {
                            PhoachingTextField(
                                value = state.duration?.toString().orEmpty(),
                                onChangeValue = { viewModel.changeDuration(it) },
                                modifier = Modifier.weight(1f),
                                label = { state, isFocused ->
                                    Text(
                                        text = PhoachingResourceStrings.provide_phoaching_duration,
                                        style = PhoachingTheme.typography.regular.copy(
                                            color = PhoachingTheme.colors.uiKit.textColor,
                                            fontSize = 14.sp,
                                        ),
                                        modifier = Modifier.padding(bottom = 10.dp)
                                    )
                                }
                            )

                            Text(
                                text = PhoachingResourceStrings.days,
                                style = PhoachingTheme.typography.regular.copy(
                                    color = PhoachingTheme.colors.uiKit.textColor,
                                    fontSize = 14.sp
                                ),
                                modifier = Modifier.padding(start = 18.dp)
                            )
                        }
                    }

                    item {
                        Text(
                            text = PhoachingResourceStrings.provide_days_title,
                            style = PhoachingTheme.typography.regular.copy(
                                color = PhoachingTheme.colors.uiKit.textColor,
                                fontSize = 14.sp
                            ),
                            modifier = Modifier.fillMaxWidth().padding(top = 10.dp)
                        )
                    }

                    items(
                        state.days
                    ) { dayUI ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth().padding(top = 10.dp)
                        ) {
                            Text(
                                text = PhoachingResourceStrings.day_number.format(dayUI.number.toString()),
                                style = PhoachingTheme.typography.regular.copy(
                                    color = PhoachingTheme.colors.uiKit.textColor,
                                    fontSize = 14.sp
                                ),
                                modifier = Modifier.padding(end = 15.dp)
                            )

                            PhoachingTextField(
                                value = dayUI.title,
                                onChangeValue = { viewModel.changeDayTitle(dayUI.number, it) },
                                placeholder = PhoachingResourceStrings.day_title,
                                modifier = Modifier.weight(1f),
                            )
                        }
                    }

                    item {
                        PhoachingButton(
                            enabled = state.author.isNotBlank() &&
                                    state.days.isNotEmpty() &&
                                    state.days.all { it.title.isNotBlank() } &&
                                    state.title.isNotBlank() &&
                                    state.duration.isNotNullOrZero() &&
                                    state.description.isNotBlank() &&
                                    state.wrongUntilRepeat.isNotNullOrZero(),
                            modifier = Modifier.padding(bottom = 31.dp, top = 20.dp).height(46.dp),
                            onClick = { viewModel.create() },
                            text = PhoachingResourceStrings.save
                        )
                    }
                }
            }
        }


    }
}