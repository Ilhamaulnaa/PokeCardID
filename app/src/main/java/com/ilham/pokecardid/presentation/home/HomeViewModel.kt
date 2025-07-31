package com.ilham.pokecardid.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ilham.event.data.models.PokeListEntry
import com.ilham.event.data.repository.PokemonRepository
import com.ilham.event.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: PokemonRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> get() = _uiState

    private var currentPage = 0
    private val pageSize = 10
    private var isLoadingPage = false


    init {
        loadNextPage()
    }

    fun loadNextPage() {
        if (isLoadingPage) return

        viewModelScope.launch {
            isLoadingPage = true
            _uiState.update { it.copy(isLoading = true) }

            val offset = currentPage * pageSize
            when (val result = repository.getPokemonList(limit = pageSize, offset = offset)) {
                is Response.Success -> {
                    val newList = result.data?.results?.mapIndexed { index, entry ->
                        val number = entry?.url
                            ?.trimEnd('/')
                            ?.takeLastWhile { it.isDigit() }
                            ?.toIntOrNull() ?: 0
                        val imageUrl = "$number.png"

                        PokeListEntry(
                            pokemonName = entry?.name.orEmpty().replaceFirstChar { it.uppercase() },
                            imageUrl = imageUrl,
                            number = number,
                            url = entry?.url.orEmpty()
                        )
                    } ?: emptyList()

                    _uiState.update {
                        it.copy(
                            pokemon = it.pokemon + newList,
                            isLoading = false
                        )
                    }
                    currentPage++
                }
                is Response.Error -> {
                    _uiState.update { it.copy(isLoading = false, errorMessage = result.message) }
                }
                else -> {}
            }

            isLoadingPage = false
        }
    }

    fun retryLoad() {
        currentPage = 0
        isLoadingPage = false
        _uiState.update { it.copy(pokemon = emptyList(), errorMessage = null) }
        loadNextPage()
    }

}