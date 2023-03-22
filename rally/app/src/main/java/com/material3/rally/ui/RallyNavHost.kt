package com.material3.rally.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.material3.rally.data.Accounts
import com.material3.rally.data.Bills
import com.material3.rally.data.Overview
import com.material3.rally.data.SingleAccount
import com.material3.rally.ui.screens.accounts.AccountsScreen
import com.material3.rally.ui.screens.accounts.SingleAccountScreen
import com.material3.rally.ui.screens.bills.BillsScreen
import com.material3.rally.ui.screens.overview.OverviewScreen

@Composable
fun RallyNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Overview.route,
        modifier = modifier,
    ) {
        composable(route = Overview.route) {
            OverviewScreen(
                onClickSeeAllAccounts = {
                    navController.navigateSingleTopTo(Accounts.route)
                },
                onClickSeeAllBills = {
                    navController.navigateSingleTopTo(Bills.route)
                },
                onAccountClick = { accountType ->
                    navController.navigateToSingleAccount(accountType)
                }
            )
        }
        composable(route = Accounts.route) {
            AccountsScreen(onAccountClick = {accountType->
                navController
                    .navigateToSingleAccount(accountType)
            })
        }
        composable(route = Bills.route) {
            BillsScreen()
        }

        composable(
            route = SingleAccount.routeWithArgs, arguments = SingleAccount.arguments,
            deepLinks = SingleAccount.deepLinks
        ) { navBackStackEntry ->
            // Retrieve the passed argument
            val accountType =
                navBackStackEntry.arguments?.getString(SingleAccount.accountTypeArg)
            // Pass accountType to SingleAccountScreen
            SingleAccountScreen(accountType)
        }


    }
}

fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        // Pop up to the start destination of the graph to
        // avoid building up a large stack of destinations
        // on the back stack as users select items
        popUpTo(
            this@navigateSingleTopTo.graph.findStartDestination().id
        ) {
            saveState = true
        }
        // Avoid multiple copies of the same destination when
        // reselecting the same item
        launchSingleTop = true
        // Restore state when reselecting a previously selected item
        restoreState = true
    }

private fun NavHostController.navigateToSingleAccount(accountType: String) {
    this.navigateSingleTopTo("${SingleAccount.route}/$accountType")
}