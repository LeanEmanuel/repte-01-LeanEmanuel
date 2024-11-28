package com.example.androidstudio_koala_template

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidstudio_koala_template.ui.theme.AndroidStudioKoalaTemplateTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
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
    var valorMin by rememberSaveable { mutableStateOf("") }
    var valorMax by rememberSaveable { mutableStateOf("") }
    var sliderValue by rememberSaveable { mutableFloatStateOf(0f) }
    var selectedIcon by remember { mutableStateOf(Icons.Default.Add) }


    Box(
        modifier = modifier
            .fillMaxSize()
    )
    {
        Column(
            modifier = modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "Repte 01",
                style = MaterialTheme.typography.titleMedium.copy(
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
            CustomDropDownMenu(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp, 16.dp),
                onIconSelected = { selectedIcon = it }
            )

            Spacer(modifier = modifier.height(32.dp))

            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = modifier
                ) {
                    Text(text = "Min:")
                    OutlinedTextField(
                        value = valorMin,
                        onValueChange = { valorMin = it },
                        modifier = modifier
                            .width(100.dp)
                            .height(50.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)

                    )
                }

                Column {
                    Text(text = "Max:")
                    OutlinedTextField(
                        value = valorMax,
                        onValueChange = { valorMax = it },
                        modifier = modifier
                            .width(100.dp)
                            .height(50.dp)
                    )
                }
            }
            CustomSlider(
                modifier = modifier,
                valorMin,
                valorMax,
                sliderValue = sliderValue,
                onSliderValueChange = { sliderValue = it }

            )

            CustomBadgeBox(
                modifier = modifier,
                sliderValue = sliderValue,
                icon = selectedIcon
            )

            HorizontalDivider(
                modifier = Modifier
                    .padding(16.dp, 16.dp),
                thickness = 1.dp,
                color = Color.Black
            )
        }
    }
}

@Composable
fun CustomDropDownMenu(
    modifier: Modifier = Modifier,
    onIconSelected: (ImageVector) -> Unit
) {
    var selectedIcon: String by rememberSaveable { mutableStateOf("") }
    var expanded: Boolean by rememberSaveable { mutableStateOf(false) }


    val icons = mapOf(
        "Add" to Icons.Default.Add,
        "Notifications" to Icons.Default.Notifications,
        "Email" to Icons.Default.Email,
        "ThumbUp" to Icons.Default.ThumbUp,
        "ShoppingCart" to Icons.Default.ShoppingCart,
        "AccountBox" to Icons.Default.AccountBox,
        "AccountCircle" to Icons.Default.AccountCircle,
        "AddCircle" to Icons.Default.AddCircle,
        "DateRange" to Icons.Default.DateRange
    )

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = selectedIcon,
            onValueChange = { selectedIcon = it },
            enabled = false,
            readOnly = true,
            modifier = Modifier
                .clickable { expanded = true }
                .fillMaxWidth()
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 0.dp)

        ) {
            icons.forEach { (name, icon) ->
                DropdownMenuItem(
                    text = { Text(text = name) },
                    onClick = {
                        expanded = false
                        selectedIcon = name
                        onIconSelected(icon)
                    }
                )
            }
        }
    }
}

@Composable
fun CustomSlider(
    modifier: Modifier = Modifier,
    valorMin: String,
    valorMax: String,
    sliderValue: Float,
    onSliderValueChange: (Float) -> Unit
) {

    var valorMinFloat = valorMin.toFloatOrNull() ?: 0f
    var valorMaxFloat = valorMax.toFloatOrNull() ?: 1f

    Slider(
        value = sliderValue,
        onValueChange = { newValue -> onSliderValueChange(newValue) },
        valueRange = valorMinFloat..valorMaxFloat,
        steps = 10,
        modifier = modifier.padding(16.dp)
    )
}

@Composable
fun CustomBadgeBox(
    modifier: Modifier,
    sliderValue: Float,
    icon: ImageVector
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        BadgedBox(modifier = modifier
            .padding(20.dp),
            badge = {
                Badge {
                    Text(sliderValue.toString())
                }
            },
            // Exemple d'ús de l'atribut content del BadgedBox enlloc de posar-ho a fora dels parèntisis del component
            content = {
                Icon(
                    imageVector = icon,
                    contentDescription = null
                )
            }
        )
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    AndroidStudioKoalaTemplateTheme {
        Repte01()
    }
}