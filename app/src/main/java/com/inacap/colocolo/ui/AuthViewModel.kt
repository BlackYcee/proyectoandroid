package com.inacap.colocolo.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inacap.colocolo.data.AuthRepository
import com.inacap.colocolo.data.LoginResponse
import com.inacap.colocolo.data.ProfileResponse
import kotlinx.coroutines.launch

class AuthViewModel(private val repo: AuthRepository) : ViewModel() {

    private var token: String? = null

    fun login(
        email: String,
        password: String,
        onSuccess: (LoginResponse) -> Unit,
        onFail: (String) -> Unit
    ) {
        viewModelScope.launch {
            val result = repo.login(email, password)
            result.fold(
                onSuccess = { resp ->
                    if (resp.success) {
                        token = resp.token
                        onSuccess(resp)
                    } else {
                        onFail(resp.message ?: "Error en login")
                    }
                },
                onFailure = { e ->
                    onFail(e.message ?: "Error desconocido de conexión")
                }
            )
        }
    }

    fun register(
        name: String,
        email: String,
        pass: String,
        onSuccess: () -> Unit,
        onFail: (String) -> Unit
    ) {
        viewModelScope.launch {
            val res = repo.register(name, email, pass)
            res.fold(
                onSuccess = { onSuccess() },
                onFailure = { onFail(it.message ?: "Error desconocido al registrar") }
            )
        }
    }

    fun getProfile(
        onSuccess: (ProfileResponse) -> Unit,
        onFail: (String) -> Unit
    ) {
        viewModelScope.launch {
            if (token == null) {
                onFail("No hay token, inicia sesión primero")
                return@launch
            }

            val result = repo.getProfile(token!!)
            result.fold(
                onSuccess = { profileResp ->
                    onSuccess(profileResp)
                },
                onFailure = { e ->
                    onFail(e.message ?: "Error desconocido de conexión")
                }
            )
        }
    }
}
