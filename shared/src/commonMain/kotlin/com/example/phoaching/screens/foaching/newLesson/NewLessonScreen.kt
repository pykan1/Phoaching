package com.example.phoaching.screens.foaching.newLesson

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.example.phoaching.domain.models.phoaching.CriterionAnswer
import com.example.phoaching.domain.models.phoaching.PhoachingTab
import com.example.phoaching.ext.isNotNullOrZero
import com.example.phoaching.ext.orEmpty
import com.example.phoaching.ext.text
import com.example.phoaching.strings.PhoachingResourceStrings
import com.example.phoaching.uikit.PhoachingButton
import com.example.phoaching.uikit.PhoachingCheckBox
import com.example.phoaching.uikit.PhoachingFieldMenu
import com.example.phoaching.uikit.PhoachingPageContainer
import com.example.phoaching.uikit.PhoachingTextField
import com.example.phoaching.uikit.PhoachingTopBar
import com.example.phoaching.uikit.theme.PhoachingTheme

class NewLessonScreen(private val lessonId: String? = null, private val phoachingId: String, private val phoachingTab: PhoachingTab) : Screen {

    @Composable
    override fun Content() {
        val viewModel = rememberScreenModel { NewLessonViewModel() }
        val state by viewModel.stateFlow.collectAsState()
        val navigator = LocalNavigator.currentOrThrow
        LaunchedEffect(viewModel) {
            viewModel.loadData(lessonId, phoachingId, tab = phoachingTab)
        }
        PhoachingPageContainer(header = {
            PhoachingTopBar(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                backIconClick = {
                    navigator.pop()
                },
                title = if (lessonId != null) PhoachingResourceStrings.editing else PhoachingResourceStrings.new_lesson,
                infoIconClick = {}
            )
        }) {
            Column(
                modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp).verticalScroll(
                    rememberScrollState()
                ), horizontalAlignment = Alignment.CenterHorizontally
            ) {

                PhoachingTextField(
                    value = state.title,
                    placeholder = PhoachingResourceStrings.heading,
                    onChangeValue = { viewModel.changeTitle(it) },
                    modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
                    label = { state, isFocused ->
                        Text(
                            text = PhoachingResourceStrings.named_title_task,
                            style = PhoachingTheme.typography.regular.copy(
                                color = PhoachingTheme.colors.uiKit.textColor,
                                fontSize = 14.sp,
                            ),
                            modifier = Modifier.padding(bottom = 10.dp)
                        )
                    }
                )



                PhoachingTextField(
                    value = state.textOfLesson,
                    placeholder = PhoachingResourceStrings.text_of_lesson,
                    onChangeValue = { viewModel.changeTextOfLesson(it) },
                    modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
                    label = { state, isFocused ->
                        Text(
                            text = PhoachingResourceStrings.enter_text_of_lesson,
                            style = PhoachingTheme.typography.regular.copy(
                                color = PhoachingTheme.colors.uiKit.textColor,
                                fontSize = 14.sp,
                            ),
                            modifier = Modifier.padding(bottom = 10.dp)
                        )
                    }
                )

                PhoachingTextField(
                    value = state.taskText,
                    placeholder = PhoachingResourceStrings.text_of_task,
                    onChangeValue = { viewModel.changeTaskText(it) },
                    modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
                    label = { state, isFocused ->
                        Text(
                            text = PhoachingResourceStrings.enter_task,
                            style = PhoachingTheme.typography.regular.copy(
                                color = PhoachingTheme.colors.uiKit.textColor,
                                fontSize = 14.sp,
                            ),
                            modifier = Modifier.padding(bottom = 10.dp)
                        )
                    }
                )

                Row(
                    modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = PhoachingResourceStrings.indicate_day_of_phoaching,
                        style = PhoachingTheme.typography.regular.copy(
                            color = PhoachingTheme.colors.uiKit.textColor,
                            fontSize = 14.sp,
                        ),
                        modifier = Modifier
                    )
                    PhoachingFieldMenu(
                        modifier = Modifier.padding(start = 20.dp).fillMaxWidth(),
                        value = state.selectDay?.toString().orEmpty(),
                        label = {

                        },
                        currentValue = state.selectDay.orEmpty(),
                        items = state.listOfDays,
                        itemConvertText = { it.toString() },
                        onClick = { viewModel.changeDayPhoaching(it) }
                    )
                }

                PhoachingFieldMenu(
                    modifier = Modifier.padding(top = 10.dp).fillMaxWidth(),
                    value = state.criterion?.text() ?: "",
                    label = {
                        Text(
                            text = PhoachingResourceStrings.select_criterion_check_answer,
                            style = PhoachingTheme.typography.regular.copy(
                                color = PhoachingTheme.colors.uiKit.textColor,
                                fontSize = 14.sp,
                            ),
                            modifier = Modifier.padding(bottom = 10.dp)
                        )
                    },
                    currentValue = state.criterion,
                    items = CriterionAnswer.entries,
                    itemConvertText = { it?.text() ?: "" },
                    onClick = { viewModel.changeCriterion(it) }
                )
                PhoachingTextField(
                    value = state.keyPhrase,
                    placeholder = PhoachingResourceStrings.enter_key_phrase,
                    onChangeValue = { viewModel.changeKeyPhrase(it) },
                    modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
                    label = { state, isFocused ->
                        Text(
                            text = PhoachingResourceStrings.key_phrase,
                            style = PhoachingTheme.typography.regular.copy(
                                color = PhoachingTheme.colors.uiKit.textColor,
                                fontSize = 14.sp,
                            ),
                            modifier = Modifier.padding(bottom = 10.dp)
                        )
                    }
                )

                PhoachingTextField(
                    value = state.pointsByTask?.toString().orEmpty(),
                    placeholder = PhoachingResourceStrings.count_of_points,
                    onChangeValue = { viewModel.changePointByTask(it) },
                    modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
                    label = { state, isFocused ->
                        Text(
                            text = PhoachingResourceStrings.enter_points_by_task,
                            style = PhoachingTheme.typography.regular.copy(
                                color = PhoachingTheme.colors.uiKit.textColor,
                                fontSize = 14.sp,
                            ),
                            modifier = Modifier.padding(bottom = 10.dp)
                        )
                    }
                )

                PhoachingFieldMenu(
                    modifier = Modifier.padding(top = 10.dp).fillMaxWidth(),
                    value = state.selectCheckList,
                    label = {
                        Text(
                            text = PhoachingResourceStrings.choose_points_checklist,
                            style = PhoachingTheme.typography.regular.copy(
                                color = PhoachingTheme.colors.uiKit.textColor,
                                fontSize = 14.sp,
                            ),
                            modifier = Modifier.padding(bottom = 10.dp)
                        )
                    },
                    currentValue = state.selectCheckList,
                    items = listOf(),
                    itemConvertText = { it },
                    onClick = { viewModel.changeSelectChecklist(it) }
                )
                PhoachingTextField(
                    value = state.urlImage,
                    placeholder = PhoachingResourceStrings.link,
                    onChangeValue = { viewModel.changeUrlImage(it) },
                    modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
                    label = { state, isFocused ->
                        Text(
                            text = PhoachingResourceStrings.enter_link_image,
                            style = PhoachingTheme.typography.regular.copy(
                                color = PhoachingTheme.colors.uiKit.textColor,
                                fontSize = 14.sp,
                            ),
                            modifier = Modifier.padding(bottom = 10.dp)
                        )
                    }
                )


                Text(
                    text = PhoachingResourceStrings.use_answer,
                    style = PhoachingTheme.typography.regular.copy(
                        color = PhoachingTheme.colors.uiKit.textColor,
                        fontSize = 14.sp,
                    ),
                    modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp)
                )

                PhoachingCheckBox(
                    checked = state.insiteOfDay,
                    modifier = Modifier.padding(top = 7.dp).fillMaxWidth(),
                    onCheckedChange = { viewModel.changeInsiteOfDay() },
                    title = PhoachingResourceStrings.insite_of_day
                )
                PhoachingCheckBox(
                    checked = state.ourInsite,
                    modifier = Modifier.padding(top = 7.dp).fillMaxWidth(),
                    onCheckedChange = { viewModel.changeOurInsite() },
                    title = PhoachingResourceStrings.our_insite
                )

                PhoachingTextField(
                    value = state.textBeforeAnswer,
                    placeholder = PhoachingResourceStrings.enter_text_before_answer,
                    onChangeValue = { viewModel.changeTextBeforeAnswer(it) },
                    modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
                    label = { state, isFocused ->
                        Text(
                            text = PhoachingResourceStrings.text_before_answer,
                            style = PhoachingTheme.typography.regular.copy(
                                color = PhoachingTheme.colors.uiKit.textColor,
                                fontSize = 14.sp,
                            ),
                            modifier = Modifier.padding(bottom = 10.dp)
                        )
                    }
                )

                PhoachingTextField(
                    value = state.textAfterAnswer,
                    placeholder = PhoachingResourceStrings.enter_text_after_answer,
                    onChangeValue = { viewModel.changeTextAfterAnswer(it) },
                    modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
                    label = { state, isFocused ->
                        Text(
                            text = PhoachingResourceStrings.text_after_answer,
                            style = PhoachingTheme.typography.regular.copy(
                                color = PhoachingTheme.colors.uiKit.textColor,
                                fontSize = 14.sp,
                            ),
                            modifier = Modifier.padding(bottom = 10.dp)
                        )
                    }
                )

                PhoachingButton(
                    enabled = state.title.isNotBlank() &&
                            state.textOfLesson.isNotBlank() &&
                            state.taskText.isNotBlank() &&
                            state.selectDay.isNotNullOrZero() &&
                            state.criterion != null &&
                            state.keyPhrase.isNotBlank() &&
                            state.pointsByTask.isNotNullOrZero() &&
                            state.urlImage.isNotBlank() &&
                            (state.insiteOfDay || state.ourInsite) &&
                            state.textAfterAnswer.isNotBlank() &&
                            state.textBeforeAnswer.isNotBlank(),
                    modifier = Modifier.padding(bottom = 31.dp, top = 20.dp).height(46.dp),
                    onClick = { viewModel.save(lessonId, phoachingId) },
                    text = PhoachingResourceStrings.save,
                )
            }

        }
    }
}
