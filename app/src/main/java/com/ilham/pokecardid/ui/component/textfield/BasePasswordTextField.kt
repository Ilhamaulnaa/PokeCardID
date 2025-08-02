package com.ilham.pokecardid.ui.component.textfield

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ilham.pokecardid.ui.component.text.BaseText
import com.ilham.pokecardid.ui.theme.PokeCardIDTheme

@ExperimentalMaterial3Api
@Composable
fun BasePasswordTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    keyboardOption: KeyboardOptions = KeyboardOptions.Default,
    placeholder: String = "",
    isError: Boolean = false,
    supportingText: String = "",
    colors: TextFieldColors = largeTextFieldColors,
    textStyle: TextStyle = largeTextFieldStyle
) {

    var passwordVisible by remember { mutableStateOf(false) }
    val visualTransformation = remember(key1 = passwordVisible) {
        if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation()
    }
    val togglePasswordIcon = remember(key1 = passwordVisible) {
        if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
    }

    val togglePasswordVisibility: () -> Unit = {
        passwordVisible = !passwordVisible
    }

    Column(modifier = modifier) {
        BaseOutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            textStyle = textStyle,
            shape = RoundedCornerShape(12.dp),
            colors = colors,
            singleLine = true,
            isError = isError,
            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 14.dp),
            modifier = Modifier.height(50.dp).fillMaxWidth(),
            trailingIcon = {
                IconButton(onClick = togglePasswordVisibility) {
                    Icon(
                        imageVector = togglePasswordIcon,
                        contentDescription = "Toggle password"
                    )
                }
            },
            placeholder = {
                if (placeholder.isNotBlank()) {
                    Text(
                        text = placeholder,
                        color = Color(0xFFA4A4A4).copy(alpha = 0.7f),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            },
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOption
        )
        AnimatedVisibility(visible = supportingText.isNotEmpty()) {
            BaseText(
                text = supportingText,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewBasePasswordTextField() {
    PokeCardIDTheme {
        Surface {
            BasePasswordTextField(
                value = "Search",
                onValueChange = {},
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}