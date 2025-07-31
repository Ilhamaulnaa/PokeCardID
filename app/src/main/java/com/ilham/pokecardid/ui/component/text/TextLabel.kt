package com.ilham.pokecardid.ui.component.text

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ilham.pokecardid.ui.theme.LightBlue
import com.ilham.pokecardid.ui.theme.PokeCardIDTheme
import com.ilham.pokecardid.ui.theme.TypeWater

enum class TextLabelType {
    FilledBlue,

}

enum class TextLabelSize {
    Small
}

@Composable
fun TextLabel(
    modifier: Modifier = Modifier,
    text: String,
    textSize: TextUnit = 9.sp,
    type: TextLabelType = TextLabelType.FilledBlue,
    size: TextLabelSize = TextLabelSize.Small
) {

    val textColor = when (type) {
        TextLabelType.FilledBlue -> TypeWater
    }
    val borderColor = when (type) {
        TextLabelType.FilledBlue -> TypeWater
    }
    val textPadding = when (size) {
        TextLabelSize.Small -> 5.dp
    }

    BaseText(
        text = text,
        lineHeight = 20.sp,
        fontSize = textSize,
        fontWeight = FontWeight.Medium,
        color = textColor,
        textAlign = TextAlign.Center,
        modifier = modifier
            .border(
                width = 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(size = 10.dp)
            )
            .clip(RoundedCornerShape(size = 10.dp))
            .padding(horizontal = textPadding, vertical = 2.dp)
    )

}

@Preview
@Composable
private fun TextLabelPreview() {
    PokeCardIDTheme {
        Surface{
            TextLabel(
                text = "Pokemon!",
                type = TextLabelType.FilledBlue,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}