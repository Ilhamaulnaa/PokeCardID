package com.ilham.pokecardid.ui.component.button

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ilham.pokecardid.ui.theme.PokeCardIDTheme
import com.ilham.pokecardid.ui.theme.poppinsFontFamily

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: TextUnit = 16.sp,
    onClick: () -> Unit = {},
    enabled: Boolean = true,
    loading: Boolean = false
) {
    val buttonText = if (loading) "Loading..." else text
    val buttonEnabled = if (loading) false else enabled

    Button(
        onClick = onClick,
        modifier = modifier.height(50.dp),
        enabled = buttonEnabled,
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = Color.White
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 5.dp, 5.dp, 5.dp
        ),
        contentPadding = PaddingValues(
            vertical = 14.dp,
            horizontal = 28.dp
        ),
    ) {
        Text(
            text = buttonText,
            fontSize = fontSize,
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.Medium,
        )
    }
}

@Preview
@Composable
fun PreviewPrimaryButton() {
    PokeCardIDTheme {
        Surface {
            PrimaryButton(text = "Click Me")
        }
    }
}
