package com.ilham.pokecardid.presentation.auth.register

import androidx.compose.material3.Surface
import com.ilham.pokecardid.ui.theme.PokeCardIDTheme
import android.util.Log
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ilham.event.utils.Response
import com.ilham.pokecardid.presentation.auth.AuthViewModel
import com.ilham.pokecardid.ui.component.button.PrimaryButton
import com.ilham.pokecardid.ui.component.dialog.BaseDialog
import com.ilham.pokecardid.ui.component.dialog.BaseDialogButton
import com.ilham.pokecardid.ui.component.text.BaseText
import com.ilham.pokecardid.ui.component.textfield.BaseLargeTextField
import com.ilham.pokecardid.ui.component.textfield.BaseOutlinedTextField
import com.ilham.pokecardid.ui.component.textfield.BasePasswordTextField
import com.ilham.pokecardid.ui.theme.PokeCardIDTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@ExperimentalMaterial3Api
@Composable
fun RegisterScreen(
//    navController: NavController,
    authViewModel: AuthViewModel = hiltViewModel(),
    navigateToLoginScreen: () -> Unit = {}
) {

    val registrationState by authViewModel.registrationState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    var showUsernameError by remember { mutableStateOf(false) }
    var showEmailError by remember { mutableStateOf(false) }
    var showPasswordError by remember { mutableStateOf(false) }
    var showConfirmPasswordError by remember { mutableStateOf(false) }

    var showPasswordMismatchError by remember { mutableStateOf(false) }

    var errorDialogMessage by remember { mutableStateOf("") }
    var showDialogMessage by remember { mutableStateOf(false) }
    val hideDialog = { showDialogMessage = false }

    LaunchedEffect(registrationState) {
        when (registrationState) {
            is Response.Success -> {
                Log.d("RegistrationScreen", "Registration successful, navigating to login.")
                errorDialogMessage = "Register Berhasil, Silahkan Login"
                password = ""
                confirmPassword = ""
                showDialogMessage = true

                delay(2000)
                navigateToLoginScreen()

                authViewModel.resetRegistrationState()
            }
            is Response.Error -> {
                Log.e("RegistrationScreen", "Registration error: ${registrationState.message}")
                errorDialogMessage = registrationState.message ?: "Register Gagal"
                showDialogMessage = true
                authViewModel.resetRegistrationState()
            }
            else -> {}
        }
    }

    if (showDialogMessage) {
        BaseDialog(
            message = errorDialogMessage,
            positiveButton = BaseDialogButton(
                title = "Oke",
                onClick = hideDialog
            )
        )
    }

    val onRegisterClick: () -> Unit = {
        showUsernameError = username.isBlank()
        showEmailError = email.isBlank()
        showPasswordError = password.isBlank()
        showConfirmPasswordError = confirmPassword.isBlank()

        val isAllFilled = username.isNotBlank() && email.isNotBlank() && password.isNotBlank() && confirmPassword.isNotBlank()

        if (isAllFilled) {
            if (password != confirmPassword) {
                showPasswordMismatchError = true
            } else {
                showPasswordMismatchError = false
                authViewModel.register(username, email, password)
            }
        } else {
            showPasswordMismatchError = password != confirmPassword &&
                    password.isNotBlank() && confirmPassword.isNotBlank()
        }
    }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        BaseText(text = "Registrasi", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(32.dp))

        BaseLargeTextField(
            value = username,
            onValueChange = {
                username = it
                if (it.isNotBlank()) showUsernameError = false
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            placeholder = "Username",
            isError = showUsernameError,
            modifier = Modifier.fillMaxWidth(),
        )
        if (showUsernameError){
            BaseText(
                text = "Bagian ini tidak boleh kosong",
                color = MaterialTheme.colorScheme.error,
                fontSize = 12.sp,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(top = 4.dp, start = 8.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        BaseLargeTextField(
            value = email,
            onValueChange = {
                email = it
                if (it.isNotBlank()) showEmailError = false
            },
            placeholder = "Email",
            isError = showEmailError,
            modifier = Modifier.fillMaxWidth()
        )
        if (showEmailError){
            BaseText(
                text = "Bagian ini tidak boleh kosong",
                color = MaterialTheme.colorScheme.error,
                fontSize = 12.sp,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(top = 4.dp, start = 8.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        BasePasswordTextField(
            value = password,
            onValueChange = {
                password = it
                if (it.isNotBlank()) showPasswordError = false
            },
            placeholder = "Password",
            isError = showPasswordError,
            modifier = Modifier.fillMaxWidth(),
        )
        if (showPasswordError){
            BaseText(
                text = "Bagian ini tidak boleh kosong",
                color = MaterialTheme.colorScheme.error,
                fontSize = 12.sp,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(top = 4.dp, start = 8.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        BasePasswordTextField(
            value = confirmPassword,
            onValueChange = {
                confirmPassword = it
                if (it.isNotBlank())
                showConfirmPasswordError = false
                showPasswordMismatchError = false
            },
            placeholder = "Konfirmasi Password",
            isError = showConfirmPasswordError,
            modifier = Modifier.fillMaxWidth()
        )
        if (showConfirmPasswordError) {
            BaseText(
                text = "Bagian ini tidak boleh kosong",
                color = MaterialTheme.colorScheme.error,
                fontSize = 12.sp,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(top = 4.dp, start = 8.dp)
            )
        }
        if (showPasswordMismatchError && !showConfirmPasswordError) {
            BaseText(
                text = "Password dan konfirmasi password tidak cocok",
                color = MaterialTheme.colorScheme.error,
                fontSize = 12.sp,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(top = 4.dp, start = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
        PrimaryButton(
            text = "Register",
            onClick = {
                onRegisterClick()
            },
        )
        Spacer(modifier = Modifier.height(16.dp))

        TextButton(
            onClick = { navigateToLoginScreen() }
        ) {
            Text("Sudah punya akun? Login di sini.")
        }
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
private fun RegisterScreenPreview() {
    PokeCardIDTheme {
        Surface {
            RegisterScreen()
        }
    }
}