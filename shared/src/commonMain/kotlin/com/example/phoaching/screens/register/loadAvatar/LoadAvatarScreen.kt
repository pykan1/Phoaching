package com.example.phoaching.screens.register.loadAvatar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.phoaching.ext.granted
import com.example.phoaching.platform.FilePicker
import com.example.phoaching.platform.permission.model.Permission
import com.example.phoaching.screens.register.createPassword.CreatePasswordScreen
import com.example.phoaching.strings.PhoachingResourceStrings
import com.example.phoaching.uikit.PhoachingButton
import com.example.phoaching.uikit.PhoachingButtonType
import com.example.phoaching.uikit.PhoachingImage
import com.example.phoaching.uikit.PhoachingPageContainer
import com.example.phoaching.uikit.theme.PhoachingTheme
import kotlinx.coroutines.launch

class LoadAvatarScreen(
    private val name: String,
    private val lastName: String,
    private val phone: String,
    private val email: String
) : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel = rememberScreenModel {
            LoadAvatarViewModel()
        }
        val state by viewModel.stateFlow.collectAsState()
        val scope = rememberCoroutineScope()
        var pickerShow by remember { mutableStateOf(false) }
        var pickerPermission by remember { mutableStateOf(false) }
        viewModel.permissionsService.checkPermissionFlow(Permission.READ_EXTERNAL_STORAGE)
            .collectAsState(viewModel.permissionsService.checkPermission(Permission.READ_EXTERNAL_STORAGE))
            .granted {
                if (pickerPermission) {
                    pickerShow = true
                }
            }

        PhoachingPageContainer {
            FilePicker(pickerShow, fileExtensions = listOf("jpg", "png")) { file ->
                val pathChosen = file?.path ?: return@FilePicker
                viewModel.changeImage(pathChosen)
                pickerPermission = false
                pickerShow = false
            }


//            viewModel.permissionsService.checkPermissionFlow(Permission.READ_EXTERNAL_STORAGE)
//                .collectAsState(viewModel.permissionsService.checkPermission(Permission.READ_EXTERNAL_STORAGE))
//                .granted {
//                    if (pickerPermission) {
//                        pickerShow = true
//                    }
//                }
            Column(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(200.dp))
                if (state.image.isBlank()) {
                    Text(
                        text = PhoachingResourceStrings.load_avatar,
                        style = PhoachingTheme.typography.regular.copy(
                            color = PhoachingTheme.colors.uiKit.textColor,
                            fontSize = 18.sp,
                        ),
                        textAlign = TextAlign.Center,
                    )
                }

                if (state.image.isBlank()) {
                    Image(
                        imageVector = Icons.Default.Person,
                        modifier = Modifier.size(128.dp),
                        contentDescription = null
                    )
                } else {
                    PhoachingImage(
                        modifier = Modifier.size(128.dp).clip(CircleShape),
                        contentScale = ContentScale.Crop,
                        url = state.image,
                    )
                }

                PhoachingButton(
                    modifier = Modifier.padding(top = 10.dp).width(200.dp).height(50.dp),
                    text = PhoachingResourceStrings.load,
                    onClick = {
                        scope.launch {
                            if (viewModel.permissionsService.checkPermission(Permission.READ_EXTERNAL_STORAGE)
                                    .granted()
                            ) {
                                pickerShow = true
                            } else {
                                pickerPermission = true
                                viewModel.permissionsService.providePermission(Permission.READ_EXTERNAL_STORAGE)
                            }

                        }
                    })

                PhoachingButton(
                    type = PhoachingButtonType.Outlined,
                    modifier = Modifier.padding(top = 15.dp).width(200.dp).height(50.dp),
                    text = PhoachingResourceStrings.next,
                    onClick = {
                        navigator.push(
                            CreatePasswordScreen(
                                name = name,
                                lastName = lastName,
                                email = email,
                                phone = phone,
                                avatar = state.image
                            )
                        )
                    })

            }
        }
    }
}