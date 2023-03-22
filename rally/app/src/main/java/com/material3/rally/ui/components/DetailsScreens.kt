package com.material3.rally.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.material3.rally.ui.components.common.RallyAnimatedCircle
import com.material3.rally.ui.components.common.extractProportions
import com.material3.rally.ui.components.common.formatAmount


@Composable
fun <T> StatementBody(
    modifier: Modifier = Modifier,
    items: List<T>,
    colors: (T) -> Color,
    amounts: (T) -> Float,
    totalAmount: Float,
    circleLabel: String,
    rows: @Composable (T) -> Unit
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .padding(10.dp)
        ) {

            val accountsProportions = items.extractProportions {
                amounts(it)
            }
            val circleColors = items.map { colors(it) }

            RallyAnimatedCircle(
                proportions = accountsProportions,
                colors = circleColors,
                modifier = Modifier.height(300.dp)
                    .align(Alignment.Center)
                    .fillMaxWidth()
            )

            Column(modifier = Modifier.align(Alignment.Center)) {
                Text(
                    text = circleLabel,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Text(
                    text = formatAmount(totalAmount),
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }

        }
        Spacer(modifier = Modifier.padding(10.dp))
        ElevatedCard {
            Column(modifier = Modifier.padding(10.dp)) {
                items.forEach { item ->
                    rows(item)
                }

            }
        }


    }

}