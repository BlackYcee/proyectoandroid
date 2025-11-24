package com.inacap.colocolo

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.inacap.colocolo.data.AuthApi
import com.inacap.colocolo.data.AuthRepository
import com.inacap.colocolo.nav.appNavGraph
import com.inacap.colocolo.ui.AuthViewModel
import com.inacap.colocolo.ui.theme.ColocoloTheme
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Crear Retrofit y la API
        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api = retrofit.create(AuthApi::class.java)

        val repository = AuthRepository(api)

        val viewModel = AuthViewModel(repository)

        setContent {
            val navController = rememberNavController()
            ColocoloTheme {
                NavHost(navController = navController, startDestination = "login") {
                    appNavGraph(viewModel, navController)
                }
            }
        }

        if (Environment.isExternalStorageManager()) {
        } else {
            val intent = Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION)
            intent.data = Uri.parse("package:" + packageName)
            startActivity(intent)
        }

    }
}

