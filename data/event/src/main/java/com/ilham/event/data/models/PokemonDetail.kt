package com.ilham.event.data.models

import androidx.compose.ui.graphics.Color

data class PokemonDetail(
    val name: String,
    val id: Int,
    val height: Int,
    val weight: Int,
    val imageUrl: String,
    val types: List<String>,
    val abilities: List<String>,
    val stats: List<PokemonStat>)

data class PokemonStat(
    val name: String,
    val value: Int,
    val maxValue: Int,
    val color: Color
)
