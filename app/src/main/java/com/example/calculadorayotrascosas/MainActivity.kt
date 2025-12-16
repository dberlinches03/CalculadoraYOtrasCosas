package com.example.calculadorayotrascosas

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Dialpad
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults // para el color del boton
import androidx.compose.material3.DismissibleDrawerSheet
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
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
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.calculadorayotrascosas.ui.theme.CalculadoraYOtrasCosasTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculadoraYOtrasCosasTheme {
                Navigaton()
            }
        }
    }
}

@Composable
fun CalculatorContent(
    modifier: Modifier = Modifier,
    primerValor: String,
    segundoValor: String,
    resultado: String,
    onPrimerValorChange: (String) -> Unit,
    onSegundoValorChange: (String) -> Unit,
    onResultadoChange: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = primerValor,
            onValueChange = onPrimerValorChange
        )
        Spacer(modifier = Modifier.padding(8.dp))
        TextField(
            value = segundoValor,
            onValueChange = onSegundoValorChange
        )
        Spacer(modifier = Modifier.height(16.dp))

        val num1 = primerValor.toDoubleOrNull() ?: 0.0
        val num2 = segundoValor.toDoubleOrNull() ?: 0.0

        Spacer(modifier = Modifier.height(16.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Button(
                onClick = { onResultadoChange((num1 + num2).toString()) },
                modifier = Modifier.size(width = 100.dp, height = 50.dp),
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray)
            ) {
                Text("SUMAR", color = Color.Black)
            }
            Button(
                onClick = { onResultadoChange((num1 - num2).toString()) },
                modifier = Modifier.size(width = 100.dp, height = 50.dp),
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray)
            ) {
                Text("RESTAR", color = Color.Black)
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Button(
                onClick = { onResultadoChange((num1 * num2).toString()) },
                modifier = Modifier.size(width = 100.dp, height = 50.dp),
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray)
            ) {
                Text("MULTI", color = Color.Black)
            }
            Button(
                onClick = {
                    onResultadoChange(if (num2 != 0.0) (num1 / num2).toString() else "Error división por 0")
                },
                modifier = Modifier.size(width = 100.dp, height = 50.dp),
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray)
            ) {
                Text("DIVIDIR", color = Color.Black)
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppToBar(snackbarHostState: SnackbarHostState) {
    val scope = rememberCoroutineScope()

    TopAppBar(
        title = {
            Text("Calculadora Básica") },
        navigationIcon = {
            IconButton(onClick = {
                scope.launch { snackbarHostState.showSnackbar("Menú pulsado") }
            }) {
                Icon(Icons.Filled.Menu, contentDescription = "Menú")
            }
        },
        actions = {
            IconButton(onClick = {
                scope.launch { snackbarHostState.showSnackbar("Acción pulsada") }
            }) {
                Icon(Icons.Filled.Info, contentDescription = "Info")
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Cyan,
            titleContentColor = Color.White,
            navigationIconContentColor = Color.White,
            actionIconContentColor = Color.White,
        )
    )
}

@Composable
fun AppBottomBar(navController: NavController) {
    BottomAppBar {
        IconButton(onClick = { navController.navigate("presentacion") }) {
            Icon(Icons.Filled.Home, contentDescription = "Presentación")
        }
        IconButton(onClick = { navController.navigate("calculadora") }) {
            Icon(Icons.Filled.Dialpad, contentDescription = "Calculadora")
        }
        Spacer(Modifier.weight(1f))
        IconButton(onClick = { navController.navigate("aspecto") }) {
            Icon(Icons.Filled.Settings, contentDescription = "Aspecto")
        }
    }
}

@Composable
fun AppScaffoldWidthFab(
    navController: NavController,
    showTopBar: Boolean,
    showBottomBar: Boolean,
    content: @Composable (PaddingValues) -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    Scaffold(
        topBar = { if (showTopBar) AppToBar(snackbarHostState) },
        bottomBar = { if (showBottomBar) AppBottomBar(navController) },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                scope.launch { snackbarHostState.showSnackbar("FAB Pulsado") }
            }) {
                Icon(Icons.Filled.Add, contentDescription = "Acción principal")
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->
        content(innerPadding)
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun Navigaton() {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()

    var primerValor by rememberSaveable { mutableStateOf("") }
    var segundoValor by rememberSaveable { mutableStateOf("") }
    var resultado by rememberSaveable { mutableStateOf("") }

    var showTopBar by rememberSaveable { mutableStateOf(true) }
    var showBottomBar by rememberSaveable { mutableStateOf(true) }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DismissibleDrawerSheet {
                DrawerItem("Presentacion") { navController.navigate("presentacion");
                    scope.launch { drawerState.close() }
                }
                DrawerItem("Resultado") {
                    navController.navigate("resultado");
                    scope.launch { drawerState.close() }
                }
                DrawerItem("Calculadora") { navController.navigate("calculadora");
                    scope.launch { drawerState.close() }
                }
                DrawerItem("Aspecto") { navController.navigate("aspecto");
                    scope.launch { drawerState.close() }
                }
            }
        }
    ) {
        AppScaffoldWidthFab(
            navController = navController,
            showTopBar = showTopBar,
            showBottomBar = showBottomBar
        ) { padding ->
            NavHost(
                navController = navController,
                startDestination = "calculadora",
                modifier = Modifier.padding(padding)
            ) {
                composable("presentacion") { PresentacionScreen() }
                composable("resultado") { ResultadoScreen(resultado) }
                composable("calculadora") { CalculatorContent(
                    primerValor = primerValor,
                    segundoValor = segundoValor,
                    resultado = resultado,
                    onPrimerValorChange = { primerValor = it },
                    onSegundoValorChange = { segundoValor = it },
                    onResultadoChange = { resultado = it }
                ) }
                composable("aspecto") { AspectoScreen(
                    showTopBar = showTopBar,
                    showBottomBar = showBottomBar,
                    onShowTopBarChange = { showTopBar = it },
                    onShowBottomBarChange = { showBottomBar = it }
                ) }
            }
        }
    }
}

@Composable fun PresentacionScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center )
    {
        Text("Bienvenido a la app", fontSize = 24.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun AspectoScreen(
    showTopBar: Boolean,
    showBottomBar: Boolean,
    onShowTopBarChange: (Boolean) -> Unit,
    onShowBottomBarChange: (Boolean) -> Unit
) {
    var backgroundColorValue by rememberSaveable { mutableStateOf(Color.White.value) }

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Aspecto", fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Mostrar barra superior", modifier = Modifier.weight(1f))
            Switch(checked = showTopBar, onCheckedChange = onShowTopBarChange)
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Mostrar barra inferior", modifier = Modifier.weight(1f))
            Switch(checked = showBottomBar, onCheckedChange = onShowBottomBarChange)
        }

        Spacer(Modifier.height(16.dp))
        Button(onClick = { backgroundColorValue = Color(0xFFE0F7FA).value }) {
            Text("Cambiar color de fondo")
        }

        Spacer(Modifier.height(16.dp))
        Box(Modifier.fillMaxSize().background(Color(backgroundColorValue)))
    }
}

@Composable
fun ResultadoScreen(resultado: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Último resultado", fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = if (resultado.isNotBlank()) resultado else "Sin operaciones",
            fontSize = 32.sp,
            color = Color.Blue
        )
    }
}

@Composable fun DrawerItem(text: String, onClick: () -> Unit) {
    Text(
        text = text,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onClick() }
    )
}
