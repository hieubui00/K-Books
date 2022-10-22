package com.kma.kbooks.search.ui.component

import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kma.kbooks.R
import com.kma.kbooks.resources.ui.theme.KBooksTheme

@OptIn(ExperimentalComposeUiApi::class)
@Composable
internal fun ActionBar(
    modifier: Modifier = Modifier,
    title: String = "",
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
            var text by rememberSaveable {
                mutableStateOf("")
            }
            val keyboardController = LocalSoftwareKeyboardController.current

            TextField(value = text, onValueChange = {
                text = it
                onTextChange(text)
            },
                placeholder = { Text(text = title) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Search,
                    keyboardType = KeyboardType.Text
                ),
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .absoluteOffset(-16.dp),
                keyboardActions = KeyboardActions(
                    onSearch = { keyboardController?.hide()}
                ),
                shape = CircleShape,
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.Black,
                    disabledTextColor = Color.Transparent,
                    backgroundColor = Color.Gray.copy(0.1f),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                )
            )
        },
        backgroundColor = MaterialTheme.colors.background,
        elevation = 8.dp
    )
}