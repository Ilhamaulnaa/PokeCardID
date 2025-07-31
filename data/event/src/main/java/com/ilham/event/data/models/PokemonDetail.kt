package com.ilham.event.data.models

data class PokemonDetail(
    val name: String,
    val id: Int,
    val height: Int,
    val weight: Int,
    val imageUrl: String,
    val types: List<String>,
    val abilities: List<String>
)
