package com.material3.rally.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.material3.rally.data.*
import com.material3.rally.ui.components.RallyTopAppBar
import com.material3.rally.ui.screens.accounts.AccountsScreen
import com.material3.rally.ui.screens.accounts.SingleAccountScreen
import com.material3.rally.ui.screens.bills.BillsScreen
import com.material3.rally.ui.screens.overview.OverviewScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RallyApp() {
    val navController: NavHostController = rememberNavController()

    val currentBackStack by navController.currentBackStackEntryAsState()
    // Fetch your currentDestination:
    val currentDestination = currentBackStack?.destination
    // Change the variable to this and use Overview as a backup screen if this returns null
    val currentScreen =
        rallyTabRowScreens.find { it.route == currentDestination?.route } ?: Overview

    Scaffold(
        topBar = {
            RallyTopAppBar(
                allScreens = rallyTabRowScreens,
                { newScreen -> navController.navigateSingleTopTo(newScreen.route) },
                currentScreen = currentScreen
            )
        }) { innerPadding ->
        RallyNavHost(modifier = Modifier.padding(innerPadding),
        navController = navController)


    }
}
