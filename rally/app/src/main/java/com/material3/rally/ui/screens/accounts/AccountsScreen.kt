package com.material3.rally.ui.screens.accounts

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import com.material3.rally.R
import com.material3.rally.data.UserData
import com.material3.rally.ui.components.StatementBody
import com.material3.rally.ui.components.common.AccountRow
import com.material3.rally.ui.screens.bills.BillsScreen
import java.sql.Statement

@Composable
fun AccountsScreen(
    onAccountClick: (String) -> Unit = {}
) {
    val totalAmount = remember {
        UserData.accounts.map { account -> account.balance }.sum()
    }
    StatementBody(
        modifier = Modifier,
        items = UserData.accounts,
        amounts = {account -> account.balance},
        colors = { account -> account.color },
        totalAmount = totalAmount,
        circleLabel =  stringResource(R.string.total),
        rows = { account ->
            AccountRow(
                modifier = Modifier.clickable {
                    onAccountClick(account.name)
                },
                name = account.name,
                number = account.number,
                amount = account.balance,
                color = account.color
            )
        }
    )
}

/**
 * Detail screen for a single account.
 */
@Composable
fun SingleAccountScreen(
    accountType: String? = UserData.accounts.first().name
) {
    val account = remember(accountType) { UserData.getAccount(accountType) }
    StatementBody(
        items = listOf(account),
        colors = { account.color },
        amounts = { account.balance },
        totalAmount = account.balance,
        circleLabel = account.name,
    ) { row ->
        AccountRow(
            name = row.name,
            number = row.number,
            amount = row.balance,
            color = row.color
        )
    }
}

