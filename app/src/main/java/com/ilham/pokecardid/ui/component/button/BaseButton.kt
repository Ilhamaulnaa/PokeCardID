package com.ilham.pokecardid.ui.component.button

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ilham.pokecardid.ui.theme.PokeCardIDTheme

@Composable fun BaseButton(
    modifier: Modifier,
    isLoading: Boolean = false,
    enabled: Boolean = true,
    buttonColor: Color = MaterialTheme.colorScheme.primary,
    loadingIndicatorColor: Color = Color.White,
    contentColor: Color = Color.White,
    buttonText: String,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier
            .height(39.dp)
            .clip(RoundedCornerShape(12.dp)),
        onClick = onClick,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            contentColor = contentColor,
            containerColor = buttonColor,
            disabledContainerColor = MaterialTheme.colorScheme.secondary,
            disabledContentColor = Color.Black
        )
    ) {
        BaseButtonContent(
            buttonText = buttonText,
            isLoading = isLoading,
            indicatorColor = loadingIndicatorColor
        )
    }
}

@Composable fun BaseButtonContent(
    buttonText: String,
    isLoading: Boolean,
    indicatorColor: Color
) {
    Layout(
        content = {
            Text(
                modifier = Modifier.layoutId("textButton"),
                text = buttonText,
                style = MaterialTheme.typography.labelLarge.copy(
                    fontWeight = FontWeight.SemiBold
                )
            )
            CircularProgressIndicator(
                modifier = Modifier
                    .size(28.dp)
                    .layoutId("loadingIndicator"),
                color = indicatorColor
            )
        }
    ) { measureables, constraints ->
        val textPlaceable = measureables.first { it.layoutId == "textButton" }.measure(constraints)
        val loadingIndicatorPlaceable = measureables.first { it.layoutId == "loadingIndicator" }.measure(constraints)

        val layoutWidth = textPlaceable.width.coerceAtLeast(loadingIndicatorPlaceable.width)
        val layoutHeight = textPlaceable.height.coerceAtLeast(loadingIndicatorPlaceable.height)

        layout(layoutWidth, layoutHeight) {
            if (isLoading) {
                val indicatorX = (layoutWidth - loadingIndicatorPlaceable.width) / 2
                val indicatorY = (layoutHeight - loadingIndicatorPlaceable.height) / 2
                loadingIndicatorPlaceable.placeRelative(x = indicatorX, y = indicatorY)
            } else {
                val textX = (layoutWidth - textPlaceable.width) / 2
                val textY = (layoutHeight - textPlaceable.height) / 2
                textPlaceable.placeRelative(x = textX, y = textY)
            }
        }
    }
}

@Preview
@Composable
private fun BaseButtonPreview() {
    PokeCardIDTheme {
        Surface {
            BaseButton(
                modifier = Modifier.padding(16.dp),
                buttonText = "Touch Me",
                onClick = {}
            )
        }
    }
}