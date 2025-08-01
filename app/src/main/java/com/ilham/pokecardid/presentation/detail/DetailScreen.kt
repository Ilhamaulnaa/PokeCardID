package com.ilham.pokecardid.presentation.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ilham.event.utils.Response
import com.ilham.pokecardid.presentation.detail.component.PokemonDetailStateWrapper
import com.ilham.pokecardid.presentation.detail.component.PokemonDetailTopSection
import com.ilham.pokecardid.ui.theme.PokeCardIDTheme

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = hiltViewModel(),
    pokemonName: String,
    topPadding: Dp = 12.dp,
    pokemonImageSize: Dp = 200.dp,
    navigateUp: () -> Unit
) {
    val pokemonInfo by viewModel.pokemonDetail.collectAsStateWithLifecycle()

    LaunchedEffect(pokemonName) {
        viewModel.fetchPokemonInfo(pokemonName)
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
        .padding(bottom = 16.dp)
    ) {
        PokemonDetailTopSection(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.2f)
                .align(Alignment.TopCenter),
            navigateUp = navigateUp
        )
        PokemonDetailStateWrapper(
            pokemonInfo = pokemonInfo,
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = topPadding + pokemonImageSize / 2f,
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 16.dp
                )
                .shadow(10.dp, RoundedCornerShape(10.dp))
                .clip(RoundedCornerShape(10.dp))
                .background(MaterialTheme.colorScheme.surface)
                .padding(16.dp)
                .align(Alignment.BottomCenter),
            loadingModifier = Modifier
                .size(100.dp)
                .align(Alignment.Center)
                .padding(
                    top = topPadding + pokemonImageSize / 2f,
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 16.dp
                )
        )
        Box(contentAlignment = Alignment.TopCenter,
            modifier = Modifier
                .fillMaxSize()) {
            if(pokemonInfo is Response.Success) {
                pokemonInfo.data?.imageUrl?.let { imageURL ->
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(imageURL)
                            .crossfade(true)
                            .build(),
                        contentDescription = null,
                        modifier = Modifier
                            .size(pokemonImageSize)
                            .offset(y = topPadding)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun DetailScreenPreview() {
    PokeCardIDTheme {
        Surface {
            DetailScreen(
                pokemonName = "bulbasour",
                navigateUp = {}
            )
        }
    }
}