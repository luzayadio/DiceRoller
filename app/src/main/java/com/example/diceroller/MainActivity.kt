package com.example.diceroller

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.diceroller.ui.theme.DiceRollerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiceRollerTheme {
                // A surface container using the 'background' color from the theme
                DiceWithButtonAndImage(
                    Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center))
            }
        }
    }
}

@Composable
fun DiceWithButtonAndImage(modifier: Modifier = Modifier) {
    var qtd by remember { mutableStateOf(1) }
    var result1 by remember { mutableStateOf(1) }
    var result2 by remember { mutableStateOf(1) }
    val imageResource1 = when (result1) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }
    val imageResource2 = when (result2) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }

    fun changeValue() {
        result1 = (1..6).random()
        if(qtd == 2)
            result2 = (1..6).random()
        /*if(qtd in 1..2) {
            val a = result1
            result1 = (1..6).random()
            if (a == result1) changeValue()
        }
        if(qtd == 2) {
            val a = result2
            result2 = (1..6).random()
            if (a == result2) changeValue()
        }*/
    }

    Column(modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Row {
            Image(
                painter = painterResource(imageResource1),
                contentDescription = "$result1"
            )
            if (qtd == 2) {
                Image(
                    painter = painterResource(imageResource2),
                    contentDescription = "$result2"
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { changeValue() }) {
            Text(stringResource(id = R.string.roll))
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = { qtd = if(qtd == 1) 2 else 1 }) {
            Text("${if(qtd == 1) stringResource(id = R.string.one) else stringResource(id = R.string.two)} " + stringResource(id = R.string.dices))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DiceRollerApp() {
    DiceRollerTheme {
        DiceWithButtonAndImage(
            Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center))
    }
}