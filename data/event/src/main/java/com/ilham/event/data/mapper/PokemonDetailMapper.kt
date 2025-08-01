package com.ilham.event.data.mapper

import androidx.compose.ui.graphics.Color
import com.ilham.event.data.models.PokemonDetail
import com.ilham.event.data.models.PokemonStat
import com.ilham.event.data.remote.responses.PokemonResponse
import java.util.Locale

fun PokemonResponse.toPokemonDetail(): PokemonDetail {
    val id = this.id ?: 0
    val name = this.name?.capitalize(Locale.ROOT) ?: "Unknown"
    val height = this.height ?: 0
    val weight = this.weight ?: 0
    val imageUrl = this.sprites?.frontDefault ?: "https://placehold.co/100x100/CCCCCC/FFFFFF?text=No+Image"

    val types = this.types
        ?.filterNotNull()
        ?.mapNotNull { typesItem ->
            typesItem.type?.name?.capitalize(Locale.ROOT)
        } ?: emptyList()

    val abilities = this.abilities
        ?.filterNotNull()
        ?.mapNotNull { abilitiesItem ->
            abilitiesItem.ability?.name?.capitalize(Locale.ROOT)
        } ?: emptyList()

    val defaultMaxStat = 255
    val maxBaseStatFromData = this.stats?.filterNotNull()?.maxOfOrNull { it.baseStat ?: 0 } ?: 0
    val finalMaxStat = maxOf(defaultMaxStat, maxBaseStatFromData)


    val stats = this.stats
        ?.filterNotNull()
        ?.mapNotNull { statsItem ->
            val statName = statsItem.stat?.name ?: return@mapNotNull null
            val baseStatValue = statsItem.baseStat ?: 0

            PokemonStat(
                name = parseStatToAbbr(statName),
                value = baseStatValue,
                maxValue = finalMaxStat,
                color = parseStatToColor(statName)
            )
        } ?: emptyList()

    return PokemonDetail(
        id = id,
        name = name,
        height = height,
        weight = weight,
        imageUrl = imageUrl,
        types = types,
        abilities = abilities,
        stats = stats
    )
}

fun parseStatToAbbr(statName: String): String {
    return when(statName) {
        "hp" -> "HP"
        "attack" -> "Atk"
        "defense" -> "Def"
        "special-attack" -> "Sp.Atk"
        "special-defense" -> "Sp.Def"
        "speed" -> "Spd"
        else -> statName.capitalize(Locale.ROOT)
    }
}

fun parseStatToColor(statName: String): Color {
    return when(statName) {
        "hp" -> Color(0xFFFC0303) // Merah
        "attack" -> Color(0xFFF08030) // Oranye
        "defense" -> Color(0xFFF8D030) // Kuning
        "special-attack" -> Color(0xFF6890F0) // Biru
        "special-defense" -> Color(0xFF78C850) // Hijau
        "speed" -> Color(0xFFF85888) // Pink
        else -> Color.Gray
    }
}