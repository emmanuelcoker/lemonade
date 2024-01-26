package com.wema.lemonadeapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wema.lemonadeapp.ui.theme.LemonadeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Lemonade()
                }
            }
        }
    }
}


@Composable
fun Lemonade(modifier: Modifier = Modifier) {
    var lemonadeState by remember { mutableStateOf(1) }
    var lemonadeSqueezeState by remember { mutableStateOf(0) }

    val imageResource = getLemonDetails(lemonadeState)[1]
    val text = getLemonDetails(lemonadeState)[0]

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = modifier.background(colorResource(id = R.color.white)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Surface(
                modifier = Modifier.size(200.dp),
                shape = CircleShape,
                shadowElevation = 4.dp,
                onClick = {
                    if (lemonadeState < 4) {
                        if(lemonadeState == 2 && lemonadeSqueezeState < 3) {
                            lemonadeSqueezeState++
                        }else{
                            lemonadeState++
                        }
                    } else {
                        lemonadeSqueezeState = 0
                        lemonadeState = 1
                    }
                }
            ){
                Image(painter = painterResource(imageResource),
                    contentDescription = stringResource(id = text),
                    contentScale = ContentScale.Inside,
                    modifier = Modifier
                        .width(200.dp)
                        .height(200.dp)
                        .clip(CircleShape)
                        .background(colorResource(id = R.color.teal_700))
                        .padding(40.dp)
                )
            }
            Spacer(modifier = modifier.height(16.dp))
            Text(text = stringResource(id = text), fontSize = 18.sp, color = colorResource(id = R.color.black))
        }
    }
}

private fun getLemonDetails(lemonadeState: Int) : List<Int> {
    return when(lemonadeState) {
        1 -> {
            listOf(R.string.lemon_tree, R.drawable.lemon_tree)
        }
        2 -> {
            listOf(R.string.squeeze_lemon, R.drawable.lemon_squeeze)
        }
        3 -> {
            listOf(R.string.drink_lemonade, R.drawable.lemon_drink)
        }
        else -> {
            listOf(R.string.empty_glass, R.drawable.lemon_restart)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun LemonadeAppPreview() {
    LemonadeAppTheme {
        Lemonade()
    }
}