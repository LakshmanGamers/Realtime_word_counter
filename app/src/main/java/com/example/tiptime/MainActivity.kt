package com.example.tiptime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection.Companion.Out
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tiptime.ui.theme.TiptimeTheme
import java.text.NumberFormat
import java.text.NumberFormat.getCurrencyInstance


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TiptimeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    tipcalc()
                }
            }
        }
    }
}

@Composable
fun EditNumberField(
    value : String,
    onvaluechange : (String) -> Unit
){


    OutlinedTextField(
        value = value,
        onValueChange = onvaluechange,
        modifier = Modifier.fillMaxWidth()

        ,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        label = {
            Text(stringResource(id = R.string.cost_of_service))
        }
    )
}

private fun CalculateTip(
   str : String
) : String{
    return str.length.toString()
}

private fun Calcword(
    str : String
) : String{

if(str==""){
    return "0"
}
    val count = Regex("""(\s+|(\r\n|\r|\n))""").findAll(str.trim()).count() + 1
    return count.toString()

}

@Composable
fun tipcalc() {
    var str by remember {
        mutableStateOf("")
    }

    val tip = CalculateTip(str)
    val c = Calcword(str)
    Column (
        modifier = Modifier.padding(32.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
           )
    {
 Text(
     stringResource(id = R.string.calculate_tip), fontSize = 24.sp,
     modifier = Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.height(16.dp))
        EditNumberField(value = str, {str=it})
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(R.string.tip_amount, tip),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(R.string.word, c),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TiptimeTheme {
        tipcalc()
    }
}