package com.ilham.pokecardid.ui.component.searchbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ilham.pokecardid.ui.component.textfield.BaseOutlinedTextField
import com.ilham.pokecardid.ui.theme.PokeCardIDTheme


@ExperimentalMaterial3Api
val smallTextFieldColors @Composable get() = OutlinedTextFieldDefaults.colors(
    disabledBorderColor = Color.Transparent,
    unfocusedBorderColor = Color.Transparent,
    focusedBorderColor = Color.Yellow,
    disabledContainerColor = Color(0xFFF5F2F8),
    unfocusedContainerColor = Color(0xFFF5F2F8),
    focusedContainerColor = Color(0xFFF5F2F8)
)

val smallTextFieldStyle @Composable get() = MaterialTheme.typography.bodyMedium.copy(
    fontWeight = FontWeight.SemiBold,
    color = Color.DarkGray,
    fontSize = 14.sp,
)

@ExperimentalMaterial3Api
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    value: String,
    onValueChanged: (String) -> Unit,
    placeholder: String,
    colors: TextFieldColors = smallTextFieldColors,
    textStyle: TextStyle = smallTextFieldStyle,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default
) {

    BaseOutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .height(40.dp),
        value = value,
        onValueChange = onValueChanged,
        textStyle = textStyle,
        shape = RoundedCornerShape(12.dp),
        singleLine = true,
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
        placeholder = {
            if (placeholder.isNotBlank()){
                Text(
                    text = placeholder,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.LightGray,
                    fontSize = 14.sp,
                )
            }
        },
        leadingIcon = {
            Image(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                modifier = Modifier.size(18.dp)
            )
        },
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        colors = colors,
    )

}

@ExperimentalMaterial3Api
@Preview
@Composable
private fun SearchBardPreview() {
    PokeCardIDTheme {
        Surface {
            SearchBar(
                value = "Search",
                onValueChanged = {},
                placeholder = "Cari pokemon..",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}