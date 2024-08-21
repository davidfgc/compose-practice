package com.solucionespruna.composepractice.ui.accesibility

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.solucionespruna.composepractice.ui.theme.ComposePracticeTheme

@Composable
fun AccessibilityScreen(modifier: Modifier = Modifier) {
    var text by remember {
        mutableStateOf("")
    }

    Column(
        modifier
            .safeContentPadding()
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = "this is the title")
        TextField(
            value = text,
            label = { Text("Label") },
            placeholder = { Text("Placeholder") },
            onValueChange = { text = it },
        )
        CustomTextField(text)
        Button(onClick = { /*TODO*/ }) {
            Text("This is my button")
        }
    }
}

@Composable
private fun CustomTextField(text: String, onTextChange: (String) -> Unit = {}) {
    Column(Modifier.semantics(mergeDescendants = true) {}) {
        BasicTextField(
            value = text,
            onValueChange = onTextChange,
            Modifier
                .border(1.dp, Color.Black)
                .padding(16.dp),
            decorationBox = { innerTextField ->
                Box {
                    if (text.isEmpty()) {
                        Text(
                            text = "This is the hint",
                            color = Color.Gray,
                            modifier = Modifier.background(Color.White)
                        )
                    }
                    innerTextField()
                }
            }
        )
        Text(text = "My support text")
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun AccessibilityScreenPreview() {
    ComposePracticeTheme {
        AccessibilityScreen()
    }
}