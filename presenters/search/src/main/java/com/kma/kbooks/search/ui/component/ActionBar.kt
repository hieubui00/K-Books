package com.kma.kbooks.search.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.kma.kbooks.search.R

@OptIn(ExperimentalComposeUiApi::class)
@Composable
internal fun ActionBar(
    modifier: Modifier = Modifier,
    onNavigationClick: () -> Unit,
    onTextChange: (String) -> Unit,
) {
    TopAppBar(
        modifier = modifier,
        navigationIcon = {
            IconButton(onClick = onNavigationClick) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    tint = MaterialTheme.colors.onBackground,
                    contentDescription = "Back"
                )
            }
        },
        title = {
            var query by rememberSaveable { mutableStateOf("") }
            val keyboardController = LocalSoftwareKeyboardController.current

            SearchField(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .height(height = 40.dp)
                    .absoluteOffset((-16).dp)
                    .background(
                        color = Color.Gray.copy(0.1f),
                        shape = RoundedCornerShape(size = 16.dp)
                    ),
                value = query,
                placeholder = stringResource(R.string.hint_enter_your_story_name),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Search,
                    keyboardType = KeyboardType.Text
                ),
                keyboardActions = KeyboardActions(onSearch = { keyboardController?.hide() }),
                textStyle = MaterialTheme.typography.body1,
                onValueChange = {
                    query = it
                    onTextChange(query)
                },
            )
        },
        backgroundColor = MaterialTheme.colors.background,
        elevation = 8.dp
    )
}