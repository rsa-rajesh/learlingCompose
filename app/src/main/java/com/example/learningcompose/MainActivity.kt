package com.example.learningcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.learningcompose.ui.theme.LearningComposeTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp { MyScreenContent() }

        }
    }
}

@Composable
fun NameList(names: List<String>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(items = names) { name ->
            Greeting(name = name)
            Divider(color = Color.Black)
        }
    }
}

@Composable
fun MyScreenContent(names: List<String> = List(1000) { "Hello Android #$it" }) {
    val counterState = remember { mutableStateOf(0) }

    Column(modifier = Modifier.fillMaxHeight()) {
        NameList(names, Modifier.weight(1f))
        Counter(
            count = counterState.value,
            updateCount = { newCount ->
                counterState.value = newCount
            }
        )
    }
}

@Composable
private fun MyApp(content: @Composable () -> Unit) {
    val counterState = remember { mutableStateOf(0) }

    LearningComposeTheme {
        Surface(color = Color.Yellow) {
            content()
        }

    }
}


@Composable
fun Greeting(name: String) {

    var isSelected by remember { mutableStateOf(false) }
    val backgroundColor by animateColorAsState(if (isSelected) Color.Green else Color.Transparent)


    Text(
        text = name,
        color = Color.Red,

        style = MaterialTheme.typography.h3,
        modifier = Modifier
            .padding(24.dp)
            .background(color = backgroundColor)
            .clickable (onClick = { isSelected = !isSelected })
            .fillMaxWidth(1f),
        textAlign = TextAlign.Center
    )
}

@Preview("test preview", showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp { MyScreenContent() }
}

@Composable
fun Counter(count: Int, updateCount: (Int) -> Unit) {
    Button(
        onClick = { updateCount(count + 1) },
        modifier = Modifier
            .fillMaxWidth(1f)
            .padding(12.dp), colors = ButtonDefaults.buttonColors(
            backgroundColor = if (count > 5) Color.Green else Color.Blue,
            contentColor = if (count > 5) Color.Black else Color.White,
        )
    ) {
        Text("I've been clicked $count times")
    }

}