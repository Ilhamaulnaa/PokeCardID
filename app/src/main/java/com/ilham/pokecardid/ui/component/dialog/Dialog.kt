package com.ilham.pokecardid.ui.component.dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.ilham.pokecardid.ui.component.button.BaseButton
import com.ilham.pokecardid.ui.component.text.BaseText

data class BaseDialogButton(
    val title: String = "",
    val onClick: () -> Unit = {}
)

@Composable
fun BaseDialog(
    modifier: Modifier = Modifier,
    message: String,
    positiveButton: BaseDialogButton,
    negativeButton: BaseDialogButton? = null
) {
    Dialog(onDismissRequest = {}) {
        Card(
            modifier = modifier,
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 36.dp, bottom = 22.dp, start = 30.dp, end = 30.dp)
            ) {
                BaseText(
                    text = message,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(26.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    if (negativeButton != null) {
                        BaseButton(
                            buttonText = negativeButton.title,
                            onClick = negativeButton.onClick,
                            modifier = Modifier.weight(1f),
//                            isRounded = true
                        )
                        Spacer(modifier = Modifier.width(41.dp))
                    }
                    BaseButton(
                        buttonText = positiveButton.title,
                        onClick = positiveButton.onClick,
                        modifier = Modifier.weight(1f),
//                        isRounded = true
                    )
                }
            }
        }
    }
}