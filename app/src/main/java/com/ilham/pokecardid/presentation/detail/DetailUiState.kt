package com.ilham.pokecardid.presentation.detail

import com.ilham.event.data.models.PokeListEntry
import com.ilham.event.data.remote.responses.PokemonResponse

data class DetailUiState (

    val refresh: Boolean = false,
    val pokemonDetail: List<PokemonResponse> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,

    )