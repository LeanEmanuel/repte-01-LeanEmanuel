package com.example.androidstudio_koala_template

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidstudio_koala_template.ui.theme.AndroidStudioKoalaTemplateTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidStudioKoalaTemplateTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Repte01(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Repte01(modifier: Modifier = Modifier) {
    val icons = listOf(
        Icons.Default.Add,
        Icons.Default.Notifications,
        Icons.Default.Email,
        Icons.Default.ThumbUp,
        Icons.Default.ShoppingCart,
        Icons.Default.AccountBox,
        Icons.Default.AccountCircle,
        Icons.Default.Notifications,
        Icons.Default.AddCircle,
        Icons.Default.DateRange
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
    )

    {


        Column(
            modifier = Modifier
                .fillMaxWidth()

        ) {
            Text(
                text = "Repte 01",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.Blue,
                    fontSize = 30.sp
                ),
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            //DropDown Iconos
            DropDownMenuIcon(icons)

            Spacer(modifier = Modifier.height(16.dp))

            SliderRange()


        }
    }
}

@Composable
fun DropDownMenuIcon(
    icons: List<Icons>
) {

    var selectedIcon by rememberSaveable { mutableStateOf(Icons) }
    var expanded by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(16.dp)
    ) {

        OutlinedTextField(
            value ="",
            onValueChange = { selectedIcon = it },
            modifier = Modifier
                .clickable { expanded = true }
                .fillMaxWidth(),
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()

        ) {
            icons.forEach { icons ->
                DropdownMenuItem(
                    text = { Text(text = "") },
                    onClick = {
                        expanded = false
                        selectedIcon = icons
                    }
                )
            }
        }
    }
}


@SuppressLint("DefaultLocale")
@Composable
fun SliderRange() {
    var currentRange by rememberSaveable() { mutableStateOf(2f..5f) }
    var rangoMin by rememberSaveable() { mutableStateOf("0") }
    var rangoMax by rememberSaveable { mutableStateOf("100") }

    Box(
        modifier = Modifier
            .padding(20.dp, 40.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                TextField(
                    value = rangoMin,
                    onValueChange = { rangoMin = it },

                ) {

                }
            }
        }

        RangeSlider(
            value = currentRange,
            onValueChange = { currentRange = it },
            valueRange = 0f..20f,
            enabled = true
        )
    }
}




@Preview(
    showBackground = true, showSystemUi = true
)
@Composable
fun GreetingPreview() {
    AndroidStudioKoalaTemplateTheme {
        Repte01()
    }
}