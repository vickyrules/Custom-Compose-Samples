package com.material3.rally.ui.screens.overview

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.material3.rally.R
import com.material3.rally.data.UserData
import com.material3.rally.ui.components.RallyAlertDialog
import com.material3.rally.ui.components.common.*

private val RallyDefaultPadding = 12.dp
private const val SHOWN_ITEMS = 3

@Composable
fun OverviewScreen(
    onClickSeeAllAccounts: () -> Unit = {},
    onClickSeeAllBills: () -> Unit = {},
    onAccountClick: (String) -> Unit = {},
) {
    Column(
        modifier = Modifier
            .padding(RallyDefaultPadding)
            .verticalScroll(rememberScrollState())
            .semantics { contentDescription = "Overview Screen" }
    ) {
        AlertCard()
        Spacer(Modifier.height(RallyDefaultPadding))
        AccountsCard(
            onClickSeeAll = onClickSeeAllAccounts,
            onAccountClick = onAccountClick
        )
        Spacer(Modifier.height(RallyDefaultPadding))
        BillsCard(
            onClickSeeAll = onClickSeeAllBills
        )

    }

}

@Composable
private fun AlertCard() {
    var isAlertOpen by remember { mutableStateOf(false) }

    if (isAlertOpen) {
        RallyAlertDialog(
            onDismiss = { isAlertOpen = false },
            bodyText = stringResource(R.string.AlertBodyTxt),
            buttonLabel = stringResource(R.string.Dismiss),
        )
    }

    ElevatedCard() {
        Column(
            modifier = Modifier
                .padding(RallyDefaultPadding)
        ) {
            AlertHeader(
                onClickSeeAll = { isAlertOpen = true }
            )
            RallyDivider()
            AlertItems(message = stringResource(R.string.AlertBodyTxt))
        }
    }

}

@Composable
private fun AlertHeader(
    modifier: Modifier = Modifier,
    onClickSeeAll: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(RallyDefaultPadding),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(modifier = Modifier.weight(1f), text = stringResource(R.string.AlertLabel))
        RallySeeAll(onClickSeeAll = onClickSeeAll)

    }

}

@Composable
private fun AlertItems(message: String) {
    Row(
        modifier = Modifier.padding(RallyDefaultPadding)
            // Regard the whole row as one semantics node. This way each row will receive focus as
            // a whole and the focus bounds will be around the whole row content. The semantics
            // properties of the descendants will be merged. If we'd use clearAndSetSemantics instead,
            // we'd have to define the semantics properties explicitly.
            .semantics(mergeDescendants = true) {},
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(modifier = Modifier.weight(1f), text = message)
        IconButton(
            onClick = {},
        ) {
            Icon(
                imageVector = Icons.Filled.Sort,
                contentDescription = null,
            )
        }

    }
}


//Accounts card

@Composable
fun AccountsCard(
    onClickSeeAll: () -> Unit,
    onAccountClick: (String) -> Unit
) {
    val TotalAmount = UserData.accounts.map { account -> account.balance }.sum()

    OverViewScreenCard(
        title = stringResource(R.string.accounts),
        totalAmount = TotalAmount,
        onClickSeeAll = onClickSeeAll,
        items = UserData.accounts,
        colors = { it.color },
        values = { it.balance },
    ) { account ->
        AccountRow(
            modifier = Modifier.clickable { onAccountClick(account.name) },
            name = account.name,
            number = account.number,
            amount = account.balance,
            color = account.color
        )

    }
}

//Bills card
@Composable
fun BillsCard(
    onClickSeeAll: () -> Unit,
) {
    val TotalAmount = UserData.bills.map { bill -> bill.amount }.sum()

    OverViewScreenCard(
        title = stringResource(R.string.accounts),
        totalAmount = TotalAmount,
        onClickSeeAll = onClickSeeAll,
        items = UserData.bills,
        colors = { it.color },
        values = { it.amount },
    ) { bill ->
        BillRow(
            name = bill.name,
            amount = bill.amount,
            color = bill.color,
            due = bill.due
        )

    }
}

@Composable
fun <T> OverViewScreenCard(
    title: String,
    totalAmount: Float,
    onClickSeeAll: () -> Unit,
    items: List<T>,
    colors: (T) -> Color,
    values: (T) -> Float,
    row: @Composable (T) -> Unit
) {
    ElevatedCard {
        Column {
            Column(modifier = Modifier.padding(RallyDefaultPadding)) {
                Text(text = title, style = MaterialTheme.typography.titleMedium)

                val amount = "$ " + formatAmount(amount = totalAmount)
                Text(text = amount, style = MaterialTheme.typography.headlineMedium)
            }
            OverViewDivider(items, values, colors)

            Column(modifier = Modifier.padding(RallyDefaultPadding))
            {
                items.take(SHOWN_ITEMS).forEach {
                    row(it)
                }
                RallySeeAll(modifier = Modifier
                    .fillMaxWidth()
                    .clearAndSetSemantics {
                        contentDescription = "All $title"
                    }, onClickSeeAll = onClickSeeAll
                )
            }


        }
    }

}

@Composable
private fun <T> OverViewDivider(
    data: List<T>,
    values: (T) -> Float,
    colors: (T) -> Color
) {
    Row(Modifier.fillMaxWidth()) {
        data.forEach { item: T ->
            Spacer(
                modifier = Modifier
                    .weight(values(item))
                    .height(1.5.dp)
                    .background(colors(item))
            )
        }
    }
}