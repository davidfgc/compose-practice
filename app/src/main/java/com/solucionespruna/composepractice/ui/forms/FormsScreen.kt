package com.solucionespruna.composepractice.ui.forms

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.Surface
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.solucionespruna.composepractice.ui.theme.ComposePracticeTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FormsScreen(
    modifier: Modifier = Modifier,
    viewModel: FormsScreenViewModel = viewModel()
) {
    var value by remember {
        mutableStateOf("With value")
    }
    val onValueChange: (String) -> Unit = {
        value = it
    }

    FormsLayout(
        value,
        onValueChange,
        viewModel.textFieldState,
        modifier.safeContentPadding()
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FormsLayout(
    value: String,
    onValueChange: (String) -> Unit,
    textFieldState: TextFieldState,
    modifier: Modifier = Modifier
) {
//    val textFieldState = rememberTextFieldState()
    var textFieldValue by remember {
        mutableStateOf(TextFieldValue("With text field value"))
    }
    val onTextFieldValueChange: (TextFieldValue) -> Unit = {
        textFieldValue = it
    }

    Column(
        modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TextField(value = value, onValueChange = onValueChange)
        TextField(value = textFieldValue, onValueChange = onTextFieldValueChange)
        BasicTextField(
            state = textFieldState,
            modifier = Modifier
                .background(Color.LightGray)
                .widthIn(min = TextFieldDefaults.MinWidth)
                .padding(16.dp)
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun FormsLayoutPreview() {
    val textFieldState = TextFieldState("from preview")
    val value = "With value"
    val onValueChange: (String) -> Unit = {}

    ComposePracticeTheme {
        Surface {
            FormsLayout(value, onValueChange, textFieldState)
        }
    }
}