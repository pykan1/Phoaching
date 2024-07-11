package com.example.phoaching.screens.foaching.detailPhoaching

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.phoaching.domain.models.phoaching.PhoachingDayUI
import com.example.phoaching.domain.models.phoaching.PhoachingLessonUI
import com.example.phoaching.domain.models.phoaching.PhoachingTab
import com.example.phoaching.ext.clickableBlank
import com.example.phoaching.images.PhoachingResourceImages
import com.example.phoaching.screens.foaching.SwipeableIcon
import com.example.phoaching.screens.foaching.detailLessonScreen.DetailLessonScreen
import com.example.phoaching.screens.foaching.newLesson.NewLessonScreen
import com.example.phoaching.strings.PhoachingResourceStrings
import com.example.phoaching.uikit.PhoachingPageContainer
import com.example.phoaching.uikit.PhoachingTopBar
import com.example.phoaching.uikit.theme.PhoachingTheme
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterialApi::class)
class DetailPhoachingScreen(
    private val phoachingId: String,
    private val phoachingTab: PhoachingTab?
) : Screen {

    @Composable
    override fun Content() {
        val viewModel = rememberScreenModel { DetailPhoachingViewModel() }
        val state by viewModel.stateFlow.collectAsState()
        val navigator = LocalNavigator.currentOrThrow
        LaunchedEffect(viewModel) {
            viewModel.loadPhoaching(id = phoachingId, tab = phoachingTab)
        }
        PhoachingPageContainer {
            Scaffold(
                containerColor = PhoachingTheme.colors.uiKit.background,
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = {
                            navigator.push(
                                NewLessonScreen(
                                    lessonId = null,
                                    phoachingId,
                                    phoachingTab ?: PhoachingTab.My
                                )
                            )
                        },
                        shape = CircleShape,
                        contentColor = PhoachingTheme.colors.uiKit.bottomBarColor,
                        containerColor = PhoachingTheme.colors.uiKit.bottomBarColor
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            tint = PhoachingTheme.colors.uiKit.white,
                            contentDescription = "add"
                        )
                    }
                }) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    PhoachingTopBar(
                        modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
                        title = state.phoachingUI.title,
                        subTitle = PhoachingResourceStrings.author_is.format(author = state.phoachingUI.author),
                        backIconClick = {
                            navigator.pop()
                        },
                        infoIconClick = {

                        }
                    )

                    if (state.phoachingUI.dayWithTitle.isEmpty()) {
                        Text(
                            text = PhoachingResourceStrings.this_list_of_lesson,
                            style = PhoachingTheme.typography.regular.copy(
                                color = PhoachingTheme.colors.uiKit.borderTextFieldColor,
                                fontSize = 14.sp
                            ),
                            modifier = Modifier.padding(top = 26.dp)
                        )
                    } else {
                        LazyColumn(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            contentPadding = PaddingValues(top = 16.dp)
                        ) {

                            itemsIndexed(state.phoachingUI.dayWithTitle) { index: Int, item: PhoachingDayUI ->
                                if(index == 0) {
                                    Text(
                                        text = PhoachingResourceStrings.day_number.format(
                                            number = (index+1).toString()
                                        ),
                                        style = PhoachingTheme.typography.regular.copy(
                                            color = PhoachingTheme.colors.uiKit.textColor,
                                            fontSize = 14.sp
                                        ),
                                        modifier = Modifier.padding(top = 10.dp)
                                    )
                                }
                                state.phoachingUI.dayWithTitle.getOrNull(index - 1)
                                    ?.let { phoachingDayUI ->
                                        println(phoachingDayUI.number)
                                        println(item.number)
                                        if (phoachingDayUI.number != item.number) {
                                            Text(
                                                text = PhoachingResourceStrings.day_number.format(
                                                    number = item.number.toString()
                                                ),
                                                style = PhoachingTheme.typography.regular.copy(
                                                    color = PhoachingTheme.colors.uiKit.textColor,
                                                    fontSize = 14.sp
                                                ),
                                                modifier = Modifier.padding(top = 10.dp)
                                            )
                                        }
                                    }
                                Spacer(modifier = Modifier.size(10.dp))
                                Column {
                                    state.tasks.filter { it.day.number == item.number }.forEach {
                                        LessonItem(
                                            modifier = Modifier,
                                            phoachingLessonUI = it,
                                            delete = { viewModel.deleteLesson(it.id) },
                                            message = {},
                                            copy = {},
                                            onClick = {
                                                navigator.push(
                                                    DetailLessonScreen(
                                                        phoachingId = phoachingId,
                                                        lessonId = it.id
                                                    )
                                                )
                                            },
                                            edit = {
                                                navigator.push(
                                                    NewLessonScreen(
                                                        lessonId = it.id,
                                                        phoachingId,
                                                        phoachingTab ?: PhoachingTab.My
                                                    )
                                                )
                                            })
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun LessonItem(
        modifier: Modifier = Modifier,
        phoachingLessonUI: PhoachingLessonUI,
        delete: (() -> Unit),
        copy: (() -> Unit) = {},
        message: (() -> Unit) = {},
        edit: (() -> Unit) = {},
        onClick: (() -> Unit) = {}
    ) {
        val size = 240.dp
        val swipeAbleState = rememberSwipeableState(initialValue = 0)
        val sizePx = with(LocalDensity.current) { size.toPx() }
        val anchors = mapOf(0f to 0, -sizePx to 2)
        val scope = rememberCoroutineScope()
        Box(
            modifier = Modifier.swipeable(
                state = swipeAbleState,
                anchors = anchors,
                thresholds = { _, _ ->
                    FractionalThreshold(0.3f)
                },
                orientation = Orientation.Horizontal
            )
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {

                SwipeableIcon(
                    color = PhoachingTheme.colors.uiKit.textErrorColor,
                    icon = PhoachingResourceImages.delete,
                    click = {
                        delete()
                        scope.launch { swipeAbleState.animateTo(0) }
                    }
                )

                SwipeableIcon(
                    color = PhoachingTheme.colors.uiKit.bgDefault3,
                    icon = PhoachingResourceImages.copy,
                    click = {
                        copy()
                        scope.launch { swipeAbleState.animateTo(0) }
                    }
                )


                SwipeableIcon(
                    color = PhoachingTheme.colors.uiKit.bgDefault2,
                    icon = PhoachingResourceImages.message,
                    click = {
                        message()
                        scope.launch { swipeAbleState.animateTo(0) }
                    }
                )

                SwipeableIcon(
                    color = PhoachingTheme.colors.uiKit.bottomBarColor,
                    icon = PhoachingResourceImages.edit,
                    click = {
                        edit()
                        scope.launch { swipeAbleState.animateTo(0) }
                    }
                )

            }
            Box(
                modifier = modifier.heightIn(min = 60.dp).offset {
                    IntOffset(
                        swipeAbleState.offset.value.roundToInt(), 0
                    )
                }.background(PhoachingTheme.colors.uiKit.background).clickableBlank { onClick() },
            ) {
                Column(modifier = Modifier.fillMaxWidth().align(Alignment.Center)) {
                    Text(
                        text = phoachingLessonUI.title,
                        style = PhoachingTheme.typography.regular.copy(
                            color = PhoachingTheme.colors.uiKit.textColor,
                            fontSize = 16.sp,
                        ),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )

                    Text(
                        text = "Дата",
                        style = PhoachingTheme.typography.regular.copy(
                            color = PhoachingTheme.colors.uiKit.lightTextColor,
                            fontSize = 14.sp,
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(top = 5.dp)
                    )

                }

                Divider(
                    modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter)
                        .padding(top = 11.dp),
                    thickness = 1.dp,
                    color = PhoachingTheme.colors.uiKit.borderTextFieldColor,
                )
            }
        }
    }
}