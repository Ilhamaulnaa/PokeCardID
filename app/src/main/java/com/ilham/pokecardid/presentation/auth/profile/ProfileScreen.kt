package com.ilham.pokecardid.presentation.auth.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ilham.pokecardid.presentation.auth.AuthViewModel
import com.ilham.pokecardid.ui.component.text.BaseText
import com.ilham.pokecardid.ui.theme.PokeCardIDTheme

@Composable
fun ProfileScreen(
    authViewModel: AuthViewModel = hiltViewModel()
) {
    val loggedInUser by authViewModel.loggedInUser.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        BaseText(text = "Profil Pengguna", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(32.dp))

        loggedInUser?.let { user ->
            BaseText(text = "Username: ${user.username}", fontSize = 18.sp)
            Spacer(modifier = Modifier.height(8.dp))
            BaseText(text = "Email: ${user.email}", fontSize = 18.sp)
        } ?: run {
            BaseText(text = "Tidak ada pengguna yang login.", color = Color.Gray)
        }
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
private fun ProfileScreenPreview() {
    PokeCardIDTheme {
        Surface {
            ProfileScreen()
        }
    }
}