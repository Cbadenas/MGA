package com.mgatest.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mgatest.presentation.home.PokemonListScreen
import com.mgatest.presentation.home.PokemonListViewModel
import com.mgatest.ui.theme.MGATestTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * [MainActivity] that contains definition of [NavHostController]
 * and holds [Navigation]
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MGATestTheme {

                /**
                 * Definition of a [NavHostController]
                  */
                val navHostController = rememberNavController()
                NavHost(navController = navHostController, startDestination = Navigation.Home.route) {


                    composable(route = Navigation.Home.route){
                        val viewModel : PokemonListViewModel = hiltViewModel()
                        val state = viewModel.state.collectAsState()
                        PokemonListScreen(
                            state = state.value,
                            onEvent = viewModel::onAction,
                        )
                    }

                }
            }
        }
    }
}