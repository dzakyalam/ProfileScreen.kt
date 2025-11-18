package com.example.healthcare.ui.navigation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.healthcare.ui.components.FloatingBottomNavBar

// --- Import Screen ---
import com.example.healthcare.ui.screens.home.HomeScreen
import com.example.healthcare.ui.screens.login.LoginScreen
import com.example.healthcare.ui.screens.register.RegisterScreen
import com.example.healthcare.ui.screens.artikel.ArtikelScreen
import com.example.healthcare.ui.screens.sistempakar.SistemPakarScreen
import com.example.healthcare.ui.screens.sistempakar.GejalaScreen
import com.example.healthcare.ui.screens.sistempakar.KondisiScreen
import com.example.healthcare.ui.screens.sistempakar.DetailScreen
import com.example.healthcare.ui.screens.sistempakar.InfoScreen
import com.example.healthcare.ui.screens.artikel.ReadArtikelScreen
import com.example.healthcare.ui.profile.ProfileScreen


@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppRoutes.LOGIN_SCREEN
    ) {
        /* ====================== LOGIN ====================== */

        composable(AppRoutes.LOGIN_SCREEN) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(AppRoutes.HOME_SCREEN) {
                        popUpTo(AppRoutes.LOGIN_SCREEN) { inclusive = true }
                    }
                },
                onRegisterClick = {
                    navController.navigate(AppRoutes.REGISTER_SCREEN)
                }
            )
        }

        composable(AppRoutes.REGISTER_SCREEN) {
            RegisterScreen(
                onRegisterSuccess = { navController.popBackStack() },
                onBackClick = { navController.popBackStack() }
            )
        }

        /* ====================== HOME ====================== */

        composable(AppRoutes.HOME_SCREEN) {
            Scaffold(
                bottomBar = { FloatingBottomNavBar(navController) }
            ) { paddingValues ->
                HomeScreen(
                    onSistemPakarClick = {
                        navController.navigate(AppRoutes.SISTEM_PAKAR_SCREEN)
                    },
                    onArtikelClick = {
                        navController.navigate(AppRoutes.ARTIKEL_SCREEN)
                    }
                )
            }
        }

        /* ====================== ARTIKEL ====================== */

        composable(AppRoutes.ARTIKEL_SCREEN) {
            Scaffold(
                bottomBar = { FloatingBottomNavBar(navController) }
            ) { paddingValues ->
                ArtikelScreen(
                    onBackClick = { navController.popBackStack() },
                    onArtikelDetailClick = { artikelId ->
                        navController.navigate("${AppRoutes.ARTIKEL_DETAIL_ROUTE}/$artikelId")
                    }
                )
            }
        }

        composable(
            route = AppRoutes.ARTIKEL_DETAIL_SCREEN,
            arguments = listOf(navArgument(AppRoutes.ARTIKEL_DETAIL_ARG_ID) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val artikelId = backStackEntry.arguments?.getString(AppRoutes.ARTIKEL_DETAIL_ARG_ID)

            ReadArtikelScreen(
                itemId = artikelId,
                onBackClick = { navController.popBackStack() }
            )
        }

        /* ====================== SISTEM PAKAR ====================== */

        composable(AppRoutes.SISTEM_PAKAR_SCREEN) {
            Scaffold(
                bottomBar = { FloatingBottomNavBar(navController) }
            ) { paddingValues ->
                SistemPakarScreen(
                    onMulaiClick = {
                        navController.navigate(AppRoutes.INFO_SCREEN)
                    },
                    onBackClick = { navController.popBackStack() }
                )
            }
        }

        composable(AppRoutes.GEJALA_SCREEN) {
            GejalaScreen(
                onBackClick = { navController.popBackStack() },
                onLanjutClick = { navController.navigate(AppRoutes.KONDISI_SCREEN) }
            )
        }

        composable(AppRoutes.KONDISI_SCREEN) {
            KondisiScreen(
                onBackClick = { navController.popBackStack() },
                onLanjutClick = {
                    val hasilId = "HasilDiagnosa_001"
                    navController.navigate("${AppRoutes.PAKAR_DETAIL_ROUTE}/$hasilId")
                }
            )
        }

        composable(AppRoutes.INFO_SCREEN) {
            InfoScreen(
                onBackClick = { navController.popBackStack() },
                onLanjutClick = { navController.navigate(AppRoutes.GEJALA_SCREEN) }
            )
        }

        composable(
            route = AppRoutes.PAKAR_DETAIL_SCREEN,
            arguments = listOf(navArgument(AppRoutes.PAKAR_DETAIL_ARG_ID) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString(AppRoutes.PAKAR_DETAIL_ARG_ID)

            DetailScreen(
                itemId = id,
                onBackClick = { navController.popBackStack() },
                onSelesaiClick = {
                    navController.popBackStack(
                        route = AppRoutes.SISTEM_PAKAR_SCREEN,
                        inclusive = false
                    )
                }
            )
        }

        /* ====================== PROFILE ====================== */

        composable(AppRoutes.PROFILE_SCREEN) {
            Scaffold(
                bottomBar = { FloatingBottomNavBar(navController) }
            ) {
                ProfileScreen(
                    navController = navController,
                    onLogoutClick = {
                        navController.navigate(AppRoutes.LOGIN_SCREEN) {
                            popUpTo(AppRoutes.HOME_SCREEN) { inclusive = true }
                        }
                    }
                )
            }
        }
    }
}
