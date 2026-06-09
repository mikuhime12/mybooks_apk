package com.my.books

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppNavigation()

        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    fun navigateToTab(tab: Int) {
        when (tab) {
            0 -> navController.navigate(HomeRoute) {
                launchSingleTop = true
            }
            1 -> navController.navigate(AddRoute) {
                launchSingleTop = true
            }
            2 -> navController.navigate(LibraryRoute) {
                launchSingleTop = true
            }
        }
    }

    NavHost(
        navController = navController,
        startDestination = SplashRoute
    ) {
        composable<SplashRoute> {
            SplashScreen(onTimeout = {
                navController.navigate(LoginRoute) {
                    popUpTo(SplashRoute) {inclusive = true}
                }
            })
        }

        composable<LoginRoute> {
            LoginScreen(onLoginSuccess = {
                navController.navigate(HomeRoute) {
                    popUpTo(LoginRoute) {inclusive = true}
                }
            })
        }

        composable<HomeRoute> {
            HomeScreen(
                selectedTab = 0,
                onTabSelected = {tab ->
                    navigateToTab(tab)
                }
            )
        }

        composable<AddRoute> {
            AddScreen(
                selectedTab = 1,
                onTabSelected = {tab ->
                    navigateToTab(tab)
                }
            )
        }

        composable<LibraryRoute> {
            LibraryScreen(
                selectedTab = 2,
                onTabSelected = {tab ->
                    navigateToTab(tab)
                }
            )
        }


    }
}

