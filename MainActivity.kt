package com.example.cseca

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cseca.ui.theme.CSECATheme
import kotlin.jvm.java

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CSECATheme {
                    formPage()
                }
            }
        }
    }


@Composable
fun formPage() {

    val context = LocalContext.current

    var subName by remember { mutableStateOf("") }
    var studHours by remember { mutableStateOf("") }
    var checked by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TextField(
            modifier = Modifier.fillMaxWidth(0.8f),
            value = subName,
            onValueChange = { subName = it },
            label = { Text("Subject Name") }
        )

        TextField(
            modifier = Modifier.fillMaxWidth(0.8f),
            value = studHours,
            onValueChange = { studHours = it },
            label = { Text("Study Hours") }
        )

        Checkbox(
            checked = checked,
            onCheckedChange = {
                checked = it

                val hours = studHours.toIntOrNull() ?: 0
                if (hours > 10) {
                    val intent = Intent(context, SecondActivity::class.java)
                    intent.putExtra("subject", subName)
                    context.startActivity(intent)
                }
            }
        )

        if (checked && (studHours.toIntOrNull() ?: 0) < 10) {
            Text("Enter valid credentials")
        }
    }
}


