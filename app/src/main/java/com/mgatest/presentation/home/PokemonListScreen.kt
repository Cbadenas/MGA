package com.mgatest.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.dto.PokedexListEntry
import kotlinx.coroutines.Dispatchers

/**
 * Screen to display a list of Pokemon
 */
@Composable
fun PokemonListScreen(
    state: PokedexListUIState,
    onEvent: (PokedexListActions) -> Unit
) {

    /**
     * List of Pokemon
     */
    LazyColumn(
        contentPadding = PaddingValues(16.dp)
    ) {

        val itemCount = if (state.pokemonList.size % 2 == 0) {
            state.pokemonList.size / 2
        } else {
            state.pokemonList.size / 2 + 1
        }

        items(itemCount) { index ->
            if (index >= itemCount - 1 && !state.endReached) {
                onEvent(PokedexListActions.LoadPodemonList)
            }

            PokemonItemRow(
                pokemon = state.pokemonList[index]
            )
        }

    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        if (state.isLoading) {
            CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
        }
        if (state.loadError.isNotEmpty()) {
            RetrySection(error = state.loadError) {
                onEvent(PokedexListActions.LoadPodemonList)
            }
        }
    }

}

/**
 * Item Row representing a Pokemon
 */
@Composable
fun PokemonItemRow(pokemon: PokedexListEntry) {

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        val imageRequest = ImageRequest.Builder(LocalContext.current)
            .data(pokemon.imageUrl)
            .dispatcher(Dispatchers.IO)
            .build()

        AsyncImage(
            model = imageRequest,
            contentDescription = "Image Description",
            modifier = Modifier
                .size(120.dp)
                .align(Alignment.CenterHorizontally),
            contentScale = ContentScale.Crop,
            onSuccess = {

            },
        )

        Text(
            text = pokemon.pokemonName,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }

}

/**
 * Retry section to display error message
 */
@Composable
fun RetrySection(
    error: String,
    onRetry: () -> Unit
) {
    Column {
        Text(text = error, color = Color.Red)
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { onRetry() },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Retry")
        }
    }

}

/**
 * Preview method for PokemonListScreen
 */
@Preview
@Composable
fun PreviewPokemonListScreen() {
    PokemonListScreen(
        state = PokedexListUIState(),
        onEvent = {}
    )

}