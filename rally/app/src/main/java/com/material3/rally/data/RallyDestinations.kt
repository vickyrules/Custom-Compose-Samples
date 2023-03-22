package com.material3.rally.data

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.MoneyOff
import androidx.compose.material.icons.filled.PieChart
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.material3.rally.ui.screens.accounts.AccountsScreen
import com.material3.rally.ui.screens.bills.BillsScreen
import com.material3.rally.ui.screens.overview.OverviewScreen

sealed interface RallyDestinations {
    val icon: ImageVector
    val route: String
    //val screen : @Composable () -> Unit
}

/**
 * navigation destinations
 */
object Overview : RallyDestinations {
    override val icon = Icons.Filled.PieChart
    override val route = "overview"
    //override val screen: @Composable () -> Unit = { OverviewScreen() }
}

object Accounts : RallyDestinations {
    override val icon = Icons.Filled.AttachMoney
    override val route = "accounts"
    //override val screen: @Composable () -> Unit = { AccountsScreen() }
}

object Bills : RallyDestinations {
    override val icon = Icons.Filled.MoneyOff
    override val route = "bills"
    //override val screen: @Composable () -> Unit = { BillsScreen() }
}
object SingleAccount : RallyDestinations {
    // Added for simplicity, this icon will not in fact be used, as SingleAccount isn't
    // part of the RallyTabRow selection
    override val icon = Icons.Filled.Money
    override val route = "single_account"
    const val accountTypeArg = "account_type"
    val routeWithArgs = "${route}/{${accountTypeArg}}"
    val deepLinks = listOf(
        navDeepLink { uriPattern = "rally://$route/{$accountTypeArg}"}
    )
    val arguments = listOf(
        navArgument(accountTypeArg) { type = NavType.StringType }
    )
}


// Screens to be displayed in the top RallyTabRow
val rallyTabRowScreens = listOf(Overview, Accounts, Bills)