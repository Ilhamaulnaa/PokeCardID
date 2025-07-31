package com.ilham.pokecardid.presentation.home

import com.ilham.event.data.models.PokeListEntry
import com.ilham.event.utils.Response

data class HomeUiState(

    val refresh: Boolean = false,
    val pokemon: List<PokeListEntry> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null

)