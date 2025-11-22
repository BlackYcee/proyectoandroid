package com.inacap.colocolo.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.ui.tooling.preview.Preview
import com.inacap.colocolo.nav.Route
import com.inacap.colocolo.ui.theme.ColocoloTheme
import com.inacap.colocolo.R


@Composable
fun LoginContent(
    user: String,
    pass: String,
    onUserChange: (String) -> Unit,
    onPassChange: (String) -> Unit,
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.colo_colo),
            contentDescription = "Logo Colo Colo",
            modifier = Modifier
                .size(240.dp)
                .padding(bottom = 16.dp)
        )


        Text(
            text = "Bienvenido",
            fontSize = 23.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(Modifier.height(12.dp))
        OutlinedTextField(
            value = user,
            onValueChange = onUserChange,
            label = { Text("Usuario") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = pass,
            onValueChange = onPassChange,
            label = { Text("Contraseña") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(16.dp))
        Button(
            onClick = onLoginClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ingresar")
        }
        TextButton(
            onClick = onRegisterClick,
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("¿No tienes cuenta? Regístrate")
        }
    }
}

@Composable
fun LoginScreen(nav: NavController) {
    var user by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    LoginContent(
        user = user,
        pass = pass,
        onUserChange = { user = it },
        onPassChange = { pass = it },
        onLoginClick = { nav.navigate(Route.Home.path) },
        onRegisterClick = { nav.navigate(Route.Register.path) }
    )
}

@Preview(showBackground = true)
@Composable
fun LoginContentPreview() {
    ColocoloTheme {
        LoginContent(
            user = "nicolas",
            pass = "123",
            onUserChange = {},
            onPassChange = {},
            onLoginClick = {},
            onRegisterClick = {}
        )
    }
}
