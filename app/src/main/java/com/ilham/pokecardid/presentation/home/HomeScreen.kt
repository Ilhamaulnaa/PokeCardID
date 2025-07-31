package com.ilham.pokecardid.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ilham.pokecardid.R
import com.ilham.pokecardid.presentation.home.component.ItemList
import com.ilham.pokecardid.presentation.home.component.LoadingItemListSection
import com.ilham.pokecardid.ui.component.button.PrimaryButton
import com.ilham.pokecardid.ui.component.searchbar.SearchBar
import com.ilham.pokecardid.ui.theme.PokeCardIDTheme
import com.ilham.pokecardid.ui.theme.poppinsFontFamily

@ExperimentalLayoutApi
@ExperimentalMaterial3Api
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToDetailScreen: (String) -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val refresh = uiState.refresh

    val listState = rememberLazyGridState()

    var search by remember { mutableStateOf("") }

    val filteredList = if (search.isBlank()) {
        uiState.pokemon
    } else {
        uiState.pokemon.filter {
            it.pokemonName.contains(search.trim(), ignoreCase = true)
        }
    }

    // Trigger next page saat scroll hampir ke bawah
    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
            .collect { index ->
                val lastIndex = uiState.pokemon.lastIndex
                if (index != null && index >= lastIndex - 2) {
                    viewModel.loadNextPage()
                }
            }
    }

    Box(modifier = Modifier.fillMaxSize()){

        Image(
            painter = painterResource(R.drawable.bg_home),
            contentDescription = null,
            contentScale = ContentScale.Crop, // Crop atau Fit sesuai kebutuhan
            modifier = Modifier.fillMaxSize()
        )

        Scaffold(
            modifier = modifier.fillMaxSize(),
            containerColor = Color.Transparent
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
                    .padding(paddingValues)
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(110.dp),
                    painter = painterResource(R.drawable.pokemon_logo),
                    contentDescription = null
                )

                Spacer(modifier = Modifier.height(16.dp))

                SearchBar(
                    value = search,
                    onValueChanged = { search = it },
                    placeholder = "Yuk cari pokemon..."
                )

                Spacer(modifier = Modifier.height(16.dp))

                Box(modifier = Modifier.fillMaxSize()) {
                    when {
                        uiState.isLoading && uiState.pokemon.isEmpty() -> {
                            LoadingItemListSection()
                        }
                        filteredList.isNotEmpty() -> {
                            LazyVerticalGrid(
                                columns = GridCells.Fixed(2),
                                state = listState,
                                contentPadding = PaddingValues(8.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                items(
                                    items = filteredList,
                                    key = { entry -> entry.number }
                                ) { entry ->
                                    ItemList(
                                        entry = entry,
                                        modifier = Modifier.fillMaxWidth(),
                                        onItemClick = {
                                            navigateToDetailScreen(entry.pokemonName)
                                        }
                                    )
                                }

                                // Loading Indicator
                                if (uiState.isLoading) {
                                    item(span = { GridItemSpan(2) }) {
                                        Box(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(16.dp),
                                            contentAlignment = Alignment.Center
                                        ) {
//                                            CircularProgressIndicator()
                                            LoadingItemListSection()
                                        }
                                    }
                                }

                                // Error Message
                                uiState.errorMessage?.let { error ->
                                    item(span = { GridItemSpan(2) }) {
                                        RetrySection(
                                            error = "Terjadi kesalahan: $error",
                                            onRetry = {
                                                viewModel.retryLoad()
                                            }
                                        )
//                                        Text(
//                                            text = "Terjadi kesalahan: $error",
//                                            color = Color.Red,
//                                            modifier = Modifier.padding(8.dp)
//                                        )
                                    }
                                }
                            }
                        }

                        uiState.errorMessage != null -> {
                            RetrySection(
                                error = "Terjadi kesalahan: ${uiState.errorMessage}",
                                onRetry = {
                                    viewModel.retryLoad()
                                }
                            )
//                            Text(
//                                text = "Terjadi kesalahan: ${uiState.errorMessage}",
//                                color = Color.Red,
//                                modifier = Modifier.padding(16.dp)
//                            )
                        }

                        else -> {
                            Text(
                                text = "Tidak ada data.",
                                color = Color.Gray,
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                    }
                }
            }
        }

    }

}

@Composable
fun RetrySection(
    error: String,
    onRetry: () -> Unit = {}
) {
    Column {
        Text(text = error, color = Color.Red, fontSize = 18.sp, fontFamily = poppinsFontFamily)
        Spacer(modifier = Modifier.height(8.dp))
        PrimaryButton(
            onClick = { onRetry() },
            text = "Retry",
            modifier = Modifier.align(Alignment.CenterHorizontally),
        )
    }
}

//@Composable
//fun HomeScreen(
//    modifier: Modifier = Modifier,
//    viewModel: HomeViewModel = hiltViewModel(),
//    navigateToDetailScreen: () -> Unit = {}
//) {
//
//    val scrollState = rememberScrollState()
//    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
//
//    val pokemon = uiState.pokemon
//
//    var search by remember {
//        mutableStateOf("")
//    }
//
//    val onItemClick: (PokeListEntry) -> Unit = {
//        navigateToDetailScreen()
//    }
//
//    Scaffold(
//        modifier = modifier.fillMaxSize(),
//        containerColor = MaterialTheme.colorScheme.primary
//    ){  paddingValues ->
//        Box {
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 16.dp)
//                    .padding(paddingValues),
//            ){
//                Image(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .align(Alignment.CenterHorizontally),
//                    painter = painterResource(R.drawable.pokemon_logo),
//                    contentDescription = null
//                )
//                Spacer(modifier = Modifier.height(16.dp))
//                SearchBar(
//                    value = search,
//                    onValueChanged = { search = it },
//                    placeholder = "Yuk cari pokemon..."
//                )
//            }
//            Spacer(modifier = Modifier.height(16.dp))
//            if (pokemon is Response.Loading){
//                LoadingItemListSection()
//            }
//            if (pokemon is Response.Success){
//                FlowRow(
//                    modifier = Modifier
//                        .fillMaxWidth(),
//                    horizontalArrangement = Arrangement.spacedBy(8.dp),
//                    verticalArrangement = Arrangement.spacedBy(8.dp),
//                    maxItemsInEachRow = 2
//                ){
//                    for (itemList in pokemon.data){
//                        ItemList(
//                            entry = itemList,
//                            modifier = Modifier.weight(1f),
//                            onItemClick = onItemClick
//                        )
//                    }
//                }
//            }
//        }
//    }
//
//}

@ExperimentalLayoutApi
@ExperimentalMaterial3Api
@Preview
@Composable
private fun HomeScreenPreview() {
    PokeCardIDTheme {
        Surface {
            HomeScreen()
        }
    }
}