package com.inacap.colocolo.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.inacap.colocolo.ui.theme.ColocoloTheme
import com.inacap.colocolo.ui.AuthViewModel
import com.inacap.colocolo.data.ProfileResponse
import android.widget.Toast
import androidx.compose.material3.MaterialTheme

@Composable
fun HomeScreen(viewModel: AuthViewModel = viewModel(), navController: NavController) {
    var profileData by remember { mutableStateOf<ProfileResponse?>(null) }

    var isLoading by remember { mutableStateOf(true) }
    val context = LocalContext.current


    LaunchedEffect(key1 = Unit) {
        viewModel.getProfile(
            onSuccess = { resp ->
                profileData = resp
                isLoading = false
            },
            onFail = { errorMsg ->
                Toast.makeText(context, "Error al cargar perfil: $errorMsg", Toast.LENGTH_LONG).show()
                isLoading = false
            }
        )
    }

    Box(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        when {
            isLoading -> {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    CircularProgressIndicator()
                    Spacer(Modifier.height(8.dp))
                    Text("Cargando datos del perfil...")
                }
            }
            profileData != null -> {
                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier.fillMaxWidth().align(Alignment.Center)
                ) {
                    Text(
                        text = "¡Bienvenido, ${profileData!!.name}!",
                        fontSize = 24.sp
                    )
                    Spacer(Modifier.height(16.dp))
                    Text(text = "ID de Usuario: ${profileData!!.id}", fontSize = 18.sp)
                    Text(text = "Correo: ${profileData!!.email}", fontSize = 18.sp)

                }
            }
            else -> {
                Text("No se pudo cargar la información del usuario.", color = MaterialTheme.colorScheme.error)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    ColocoloTheme {
        Box(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.Start) {
                Text(
                    text = "¡Bienvenido, Usuario de Prueba!",
                    fontSize = 24.sp
                )
                Spacer(Modifier.height(16.dp))
                Text(text = "ID de Usuario: 999", fontSize = 18.sp)
                Text(text = "Correo: prueba@inacap.cl", fontSize = 18.sp)
            }
        }
    }
}
