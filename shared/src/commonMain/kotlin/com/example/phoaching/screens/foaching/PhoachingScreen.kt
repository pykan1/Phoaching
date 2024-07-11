package com.example.phoaching.screens.foaching

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.phoaching.domain.models.phoaching.PhoachingTab
import com.example.phoaching.domain.models.phoaching.PhoachingTab.Author
import com.example.phoaching.domain.models.phoaching.PhoachingTab.My
import com.example.phoaching.domain.models.phoaching.PhoachingUI
import com.example.phoaching.ext.clickableBlank
import com.example.phoaching.ext.text
import com.example.phoaching.images.PhoachingResourceImages
import com.example.phoaching.screens.foaching.createPhoaching.CreatePhoachingScreen
import com.example.phoaching.screens.foaching.detailPhoaching.DetailPhoachingScreen
import com.example.phoaching.strings.PhoachingResourceStrings
import com.example.phoaching.uikit.PhoachingAlertDialog
import com.example.phoaching.uikit.PhoachingButton
import com.example.phoaching.uikit.PhoachingPageContainer
import com.example.phoaching.uikit.PhoachingTextField
import com.example.phoaching.uikit.theme.PhoachingTheme
import io.github.skeptick.libres.compose.painterResource
import io.github.skeptick.libres.images.Image
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

class PhoachingScreen(private val tab: PhoachingTab) : Screen {

    @Composable
    override fun Content() {
        val viewModel = rememberScreenModel { PhoachingViewModel() }
        val state by viewModel.stateFlow.collectAsState()
        val navigator = LocalNavigator.currentOrThrow
        LaunchedEffect(viewModel) {
            viewModel.loadPhoaching(tab = tab)
        }
        PhoachingPageContainer {
            Scaffold(
                containerColor = PhoachingTheme.colors.uiKit.background,
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = { navigator.push(CreatePhoachingScreen(null, tab)) },
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
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TabRow(
                        selectedTabIndex = state.currentTab.ordinal,
                        backgroundColor = PhoachingTheme.colors.uiKit.background,
                        contentColor = PhoachingTheme.colors.uiKit.dividerColor
                    ) {
                        state.tabs.forEachIndexed { index, phoachingTab ->
                            Tab(
                                modifier = Modifier.fillMaxWidth().height(48.dp),
                                selected = index == state.currentTab.ordinal,
                                onClick = { viewModel.changeTab(phoachingTab) },
                                text = {
                                    Text(
                                        text = phoachingTab.text(),
                                        style = PhoachingTheme.typography.semiBold.copy(
                                            color = PhoachingTheme.colors.uiKit.textColor,
                                            fontSize = 18.sp
                                        ),
                                    )
                                },
                                selectedContentColor = PhoachingTheme.colors.uiKit.background,
                                unselectedContentColor = PhoachingTheme.colors.uiKit.background
                            )
                        }
                    }

                    LazyColumn(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        items(
                            when (state.currentTab) {
                                My -> state.myPhoachings
                                Author -> state.authorPhoachings
                            }
                        ) {
                            PhoachingItem(
                                modifier = Modifier.fillMaxWidth(),
                                phoachingUI = it,
                                tab = state.currentTab,
                                share = { viewModel.share(it.id) },
                                delete = { viewModel.deletePhoaching(it.id) },
                                copy = { viewModel.copy(it.id) },
                                edit = { navigator.push(CreatePhoachingScreen(it.id, tab)) },
                                checklist = {},
                            ) {
                                navigator.push(DetailPhoachingScreen(it.id, tab))
                            }
                        }

                        if (state.currentTab == Author) {
                            item {
                                PhoachingButton(
                                    modifier = Modifier.fillMaxWidth()
                                        .padding(horizontal = 25.dp, vertical = 35.dp),
                                    text = PhoachingResourceStrings.enter_promotion_code,
                                ) {
                                    viewModel.changeIsCode()
                                }
                            }
                        }
                    }
                }
            }
        }

        when {

            state.deleteId != null -> {
                PhoachingAlertDialog(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                    title = PhoachingResourceStrings.delete_phoaching,
                    cancelText = PhoachingResourceStrings.cancel,
                    acceptText = PhoachingResourceStrings.delete,
                    accept = {
                        viewModel.confirmDelete()
                    },
                    cancel = {
                        viewModel.deletePhoaching(null)
                    },
                    onDismiss = {
                        viewModel.deletePhoaching(null)
                    }
                )
            }

            state.copyId != null -> {
                PhoachingAlertDialog(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                    title = PhoachingResourceStrings.make_phoaching_copy,
                    cancelText = PhoachingResourceStrings.cancel,
                    acceptText = PhoachingResourceStrings.create,
                    accept = {
                        viewModel.confirmCopy()
                    },
                    cancel = {
                        viewModel.copy(null)
                    },
                    onDismiss = {
                        viewModel.copy(null)
                    }
                )
            }

            state.isCodeAlert -> {
                PhoachingAlertDialog(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                    title = PhoachingResourceStrings.enter_promotion_code,
                    cancelText = PhoachingResourceStrings.cancel,
                    acceptText = PhoachingResourceStrings.create,
                    content = {
                        PhoachingTextField(
                            value = state.code,
                            onChangeValue = {
                                viewModel.changeCode(it)
                            },
                            modifier = Modifier.fillMaxWidth()
                                .padding(top = 16.dp, bottom = 20.dp, start = 16.dp, end = 16.dp)
                        )
                    },
                    accept = {
                        viewModel.sendCode()
                    },
                    cancel = {
                        viewModel.changeIsCode()
                    },
                    onDismiss = {
                        viewModel.changeIsCode()
                    }
                )
            }
        }
    }

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    private fun PhoachingItem(
        modifier: Modifier = Modifier,
        phoachingUI: PhoachingUI,
        tab: PhoachingTab,
        delete: (() -> Unit),
        share: (() -> Unit) = {},
        copy: (() -> Unit) = {},
        checklist: (() -> Unit) = {},
        edit: (() -> Unit) = {},
        onClick: (() -> Unit) = {}
    ) {
        val size = (if (tab == Author) 60 else 300).dp
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
                if (tab == My) {
                    SwipeableIcon(
                        color = PhoachingTheme.colors.uiKit.bgDefault5,
                        icon = PhoachingResourceImages.share,
                        click = {
                            share()
                            scope.launch { swipeAbleState.animateTo(0) }
                        }
                    )
                }
                if (tab == My) {
                    SwipeableIcon(
                        color = PhoachingTheme.colors.uiKit.bgDefault3,
                        icon = PhoachingResourceImages.copy,
                        click = {
                            copy()
                            scope.launch { swipeAbleState.animateTo(0) }
                        }
                    )
                }
                if (tab == My) {
                    SwipeableIcon(
                        color = PhoachingTheme.colors.uiKit.bgDefault2,
                        icon = PhoachingResourceImages.checklist_tab,
                        click = {
                            checklist()
                            scope.launch { swipeAbleState.animateTo(0) }
                        }
                    )
                }
                if (tab == My) {
                    SwipeableIcon(
                        color = PhoachingTheme.colors.uiKit.bottomBarColor,
                        icon = PhoachingResourceImages.edit,
                        click = {
                            edit()
                            scope.launch { swipeAbleState.animateTo(0) }
                        }
                    )
                }
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
                        text = phoachingUI.title, style = PhoachingTheme.typography.regular.copy(
                            color = PhoachingTheme.colors.uiKit.textColor,
                            fontSize = 16.sp,
                        ), maxLines = 2, overflow = TextOverflow.Ellipsis
                    )
                    if (tab == Author) {
                        Text(
                            text = phoachingUI.title,
                            style = PhoachingTheme.typography.regular.copy(
                                color = PhoachingTheme.colors.uiKit.lightTextColor,
                                fontSize = 14.sp,
                            ),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.padding(top = 5.dp)
                        )
                    }
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

@Composable
internal fun SwipeableIcon(icon: Image, color: Color, click: () -> Unit) {
    Box(
        modifier = Modifier.size(60.dp).background(color).clickableBlank {
            click()
        }, contentAlignment = Alignment.Center
    ) {
        Image(
            contentDescription = null,
            painter = icon.painterResource(),
            modifier = Modifier.size(24.dp)
        )
    }
}