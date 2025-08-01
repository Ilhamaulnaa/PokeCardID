package com.ilham.pokecardid.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ilham.event.data.mapper.toPokemonDetail
import com.ilham.event.data.models.PokemonDetail
import com.ilham.event.data.remote.responses.PokemonResponse
import com.ilham.event.data.repository.PokemonRepository
import com.ilham.event.utils.Response
import com.ilham.pokecardid.presentation.home.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: PokemonRepository
): ViewModel() {

    private val _pokemonDetail = MutableStateFlow<Response<PokemonDetail>>(Response.Loading())
    val pokemonDetail: StateFlow<Response<PokemonDetail>> = _pokemonDetail


    fun fetchPokemonInfo(pokemonName: String) {
        viewModelScope.launch {
            _pokemonDetail.value = Response.Loading()
            val result = repository.getPokemonInfo(pokemonName)

            when (result) {
                is Response.Success -> {
                    result.data?.let { pokemonResponse ->
                        val pokemonDetail = pokemonResponse.toPokemonDetail()
                        _pokemonDetail.value = Response.Success(pokemonDetail)
                    } ?: run {
                        _pokemonDetail.value = Response.Error("Pokemon data is null.")
                    }
                }
                is Response.Error -> {
                    _pokemonDetail.value = Response.Error(result.message ?: "An unknown error occurred.")
                }
                is Response.Loading -> {
                }
            }
        }
    }


}