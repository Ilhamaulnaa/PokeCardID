package com.ilham.pokecardid.presentation.detail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ilham.event.data.models.PokemonDetail
import com.ilham.event.utils.Response
import com.ilham.pokecardid.ui.component.text.BaseText

@Composable
fun PokemonDetailStateWrapper(
    pokemonInfo: Response<PokemonDetail>,
    modifier: Modifier = Modifier,
    loadingModifier: Modifier = Modifier
) {
    when(pokemonInfo) {
        is Response.Success -> {
            pokemonInfo.data?.let { detail ->
                Column(modifier = modifier.padding(top = 56.dp)) {
                    BaseText(
                        text = detail.name,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface,
                        maxLines = 1
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    if (detail.types.isNotEmpty()) {
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            detail.types.forEach { typeName ->
                                BaseText(
                                    text = typeName,
                                    modifier = Modifier
                                        .background(Color.LightGray, RoundedCornerShape(4.dp))
                                        .padding(horizontal = 6.dp, vertical = 2.dp),
                                    color = Color.Black
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                    }

                    PokemonBaseStats(pokemonInfo = detail)
                    Spacer(modifier = Modifier.height(16.dp))

                    if (detail.abilities.isNotEmpty()) {
                        BaseText(
                            text = "Abilities:",
                            fontSize = 20.sp,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        detail.abilities.forEach { ability ->
                            BaseText(
                                text = "- $ability",
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
        }
        is Response.Error -> {
            BaseText(
                text = pokemonInfo.message ?: "An unknown error occurred",
                color = Color.Red,
                modifier = modifier
            )
        }
        is Response.Loading -> {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.primary,
                modifier = loadingModifier
            )
        }
    }
}

@Preview
@Composable
private fun PokemonDetailStateWrapperPreview() {
    Surface {
//        PokemonDetailStateWrapper()
    }
}