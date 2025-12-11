package com.example.calculadorayotrascosas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults // para el color del boton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape // forma rectangular del boton
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculadorayotrascosas.ui.theme.CalculadoraYOtrasCosasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculadoraYOtrasCosasTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                ) { innerPadding ->
                    Calcular(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Calcular(modifier: Modifier = Modifier) {
    var primerValor by remember { mutableStateOf("") }
    var segundoValor by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("") }


    Text(
        text = "Calculadora_Basica",
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        color = Color.White,
        modifier = Modifier
            .background(Color.Cyan)
            .fillMaxWidth()
            .padding(16.dp),
        textAlign = TextAlign.Center
    )
    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        TextField(
            value = primerValor,
            onValueChange = { primerValor = it },
        )
        Spacer(modifier = Modifier.padding(8.dp))
        TextField(
            value = segundoValor,
            onValueChange = { segundoValor = it },
        )
        Spacer(modifier = Modifier.height(16.dp))

        val num1 = primerValor.toDoubleOrNull() ?: 0.0
        val num2 = segundoValor.toDoubleOrNull() ?: 0.0

        Spacer(modifier = Modifier.height(16.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Button(onClick = { resultado = (num1 + num2).toString() },
                modifier = Modifier.size(width = 100.dp, height = 50.dp),
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray)
            ) {
                Text(
                    "SUMAR",
                    color = Color.Black
                )
            }
            Button(onClick = { resultado = (num1 - num2).toString() },
                modifier = Modifier.size(width = 100.dp, height = 50.dp),
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray)
            ) {
                Text(
                    "RESTAR",
                    color = Color.Black
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Button(onClick = { resultado = (num1 * num2).toString() },
                modifier = Modifier.size(width = 100.dp, height = 50.dp),
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray)
            ) {
                Text(
                    "MULTI",
                    color = Color.Black
                )
            }
            Button(onClick = {
                resultado = if (num2 != 0.0) (num1 / num2).toString() else "Error división por 0"
            },
                modifier = Modifier.size(width = 100.dp, height = 50.dp),
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray)
            ) {
                Text(
                    "DIVIDIR",
                    color = Color.Black
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Resultado: $resultado",
            fontSize = 32.sp,
            color = Color.Gray,
            modifier = modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
            )
    }
}

@Preview(showBackground = true)
@Composable
fun CalcularPreview() {
    CalculadoraYOtrasCosasTheme {
        Calcular()
    }
}