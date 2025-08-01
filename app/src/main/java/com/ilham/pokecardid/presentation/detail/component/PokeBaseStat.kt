package com.ilham.pokecardid.presentation.detail.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ilham.event.data.models.PokemonDetail
import com.ilham.pokecardid.ui.component.text.BaseText

@Composable
fun PokemonBaseStats(
    pokemonInfo: PokemonDetail,
    animDelayPerItem: Int = 100
) {

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        BaseText(
            text = "Base stats:",
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.height(4.dp))

        pokemonInfo.stats.forEachIndexed { i, stat ->
            PokemonStat(
                statName = stat.name,
                statValue = stat.value,
                statMaxValue = stat.maxValue,
                statColor = stat.color,
                animDelay = i * animDelayPerItem
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}
