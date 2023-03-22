package com.material3.rally.ui.components.common
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.material3.rally.R
import com.material3.rally.ui.theme.RallyDarkGreen
import com.material3.rally.ui.theme.RallyDarkGreenLight
import java.text.DecimalFormat


private val AccountDecimalFormat = DecimalFormat("####")
private val AmountDecimalFormat = DecimalFormat("#,###.##")
private val BaseRowHeight = 68.dp

@Composable
fun AccountRow(
    modifier: Modifier = Modifier,
    name: String,
    number: Int,
    amount: Float,
    color: Color
) {
    BaseRow(
        modifier = modifier,
        color = color,
        title = name,
        subtitle = stringResource(R.string.account_redacted) + AccountDecimalFormat.format(number),
        amount = amount,
        isNegative = false
    )
}

/**
 * A row representing the basic information of a Bill.
 */
@Composable
fun BillRow(name: String, due: String, amount: Float, color: Color) {
    BaseRow(
        color = color,
        title = name,
        subtitle = "Due $due",
        amount = amount,
        isNegative = true
    )
}

@Composable
private fun BaseRow(
    modifier: Modifier = Modifier,
    color: Color,
    title: String,
    subtitle: String,
    amount: Float,
    isNegative: Boolean
) {
    val dollarSign = if (isNegative) "-$ " else "$ "
    val formattedAmount = formatAmount(amount = amount)

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(BaseRowHeight)
            .clearAndSetSemantics {
                contentDescription = "$title account ending with ${subtitle.takeLast(4)} "
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        val typography = MaterialTheme.typography
        AccountColorIndicator(
            color = color
        )
        Column(
            modifier = Modifier
                .padding(start = 12.dp)
        ) {
            Text(
                text = title,
                style = typography.titleMedium
            )

            Text(
                modifier = Modifier
                    .alpha(0.5f), text = subtitle,
                style = typography.titleMedium
            )
        }

        Spacer(modifier = Modifier.weight(1f))
        Text(
            modifier = Modifier.align(Alignment.CenterVertically),
            text = dollarSign + formattedAmount,
            style = typography.labelLarge
        )
        Icon(
            imageVector = Icons.Filled.ChevronRight,
            contentDescription = null,
            modifier = Modifier
                .padding(end = 12.dp)
                .size(24.dp)
                .alpha(0.5f)
        )

    }
    RallyDivider()
}

@Composable
private fun AccountColorIndicator(
    color: Color,
    modifier: Modifier = Modifier
) {
    Spacer(
        modifier
            .size(4.dp, 36.dp)
            .background(color = color)
    )
}

@Composable
fun RallyDivider(
    modifier: Modifier = Modifier,
    color: Color = Color.LightGray
) {
    Divider(
        modifier = modifier,
        color = color,
        thickness = Dp.Hairline
    )
}

@Composable
fun RallySeeAll( modifier: Modifier=Modifier, onClickSeeAll: () -> Unit){
    TextButton(
        modifier = modifier,
        onClick = onClickSeeAll,
        shape = MaterialTheme.shapes.small,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
    ) {
        Text(
            text = stringResource(R.string.SELL_ALL),
            color = RallyDarkGreenLight,
            style = MaterialTheme.typography.labelMedium
        )
    }
}


fun formatAmount(amount: Float): String {
    return AmountDecimalFormat.format(amount)
}

/**
 * Used with accounts and bills to create the animated circle amount portions.
 */
fun <E> List<E>.extractProportions(selector: (E) -> Float): List<Float> {
    val total = this.sumOf { selector(it).toDouble() }
    return this.map { (selector(it) / total).toFloat() }
}