package com.ilham.pokecardid.ui.component.textfield

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ilham.pokecardid.ui.component.text.BaseText
import com.ilham.pokecardid.ui.theme.PokeCardIDTheme

@ExperimentalMaterial3Api
val largeTextFieldColors @Composable get() = OutlinedTextFieldDefaults.colors(
    disabledBorderColor = Color.Transparent,
    unfocusedBorderColor = Color.Transparent,
    focusedBorderColor = MaterialTheme.colorScheme.primary,
    disabledContainerColor = Color(0xFFF5F2F8),
    unfocusedContainerColor = Color(0xFFF5F2F8),
    focusedContainerColor = Color(0xFFF5F2F8)
)

val largeTextFieldStyle @Composable get() = MaterialTheme.typography.bodyMedium.copy(
    fontWeight = FontWeight.Medium,
    color = Color.Black.copy(0.7f),
    fontSize = 14.sp,
)

@ExperimentalMaterial3Api
@Composable
fun BaseLargeTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String = "",
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
    isError: Boolean = false,
    supportingText: String = "",
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    colors: TextFieldColors = largeTextFieldColors,
    textStyle: TextStyle = largeTextFieldStyle
) {
    Column(modifier = modifier) {
        BaseOutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            textStyle = textStyle,
            shape = RoundedCornerShape(12.dp),
            colors = colors,
            singleLine = true,
            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 14.dp),
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth(),
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            keyboardOptions = keyboardOptions,
            isError = isError,
            placeholder = {
                if (placeholder.isNotBlank()) {
                    BaseText(
                        text = placeholder,
                        color = Color(0xFFA4A4A4).copy(alpha = 0.7f),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
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
fun PreviewBaseLargeTextField() {
    PokeCardIDTheme {
        Surface {
            BaseLargeTextField(
                value = "Search",
                onValueChange = {},
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}