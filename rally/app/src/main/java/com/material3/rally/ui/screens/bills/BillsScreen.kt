package com.material3.rally.ui.screens.bills

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.material3.rally.R
import com.material3.rally.data.UserData
import com.material3.rally.ui.components.StatementBody
import com.material3.rally.ui.components.common.BillRow
import com.material3.rally.ui.components.common.formatAmount

@Composable
fun BillsScreen(){
    val totalAmount = remember {
        UserData.bills.map { bill -> bill.amount}.sum()
    }
    StatementBody(
        modifier = Modifier,
        items = UserData.bills,
        amounts = {bill -> bill.amount},
        colors = { account -> account.color },
        totalAmount = totalAmount,
        circleLabel =  stringResource(R.string.total),
        rows = { bill ->
            BillRow(
                name = bill.name,
                due  = formatAmount(totalAmount),
                amount = bill.amount,
                color = bill.color
            )
        }
    )
}
