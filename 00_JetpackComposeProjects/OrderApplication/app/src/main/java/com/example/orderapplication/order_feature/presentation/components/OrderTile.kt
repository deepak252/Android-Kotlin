package com.example.orderapplication.order_feature.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.orderapplication.order_feature.presentation.state.OrderUiState
import com.example.orderapplication.ui.theme.Gray400
import com.example.orderapplication.ui.theme.White

@Composable
fun OrderTile(
    orderUiState: OrderUiState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                orderUiState.vendorName,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                "%.2f".format(orderUiState.totalAmount) + " $",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

        }
        Divider(color = Gray400)
        Text(
            orderUiState.orderDate,
            style = MaterialTheme.typography.titleSmall,
        )

    }
}

//@Preview(showSystemUi = true)
@Preview()
@Composable
private fun OrderTilePreview(){
    OrderTile(
        orderUiState = OrderUiState(
            orderId = "1",
            vendorName = "ABC Enterprises",
            totalAmount = 2.5,
            orderDate = "22-Jan-2022"
        ),
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .border(
                1.dp,
                color = Gray400,
                RoundedCornerShape(10.dp)
            )
            .background(White)
            .padding(12.dp)
    )
}