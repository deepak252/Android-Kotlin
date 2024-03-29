package com.example.orderapplication.order_feature.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.orderapplication.order_feature.presentation.state.OrderDetailUiState
import com.example.orderapplication.ui.theme.Orange500

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun OrderDetailDialog(
    onDismiss : ()->Unit,
    orderDetailUiState: OrderDetailUiState
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
        )
    ) {
        Card(
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .fillMaxHeight(0.8f)
                .border(1.dp, color = Orange500, shape = RoundedCornerShape(10.dp))
        ){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Column {
                    Text(
                        "Ordered from ${orderDetailUiState.vendorName}",
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Text(
                        orderDetailUiState.orderDate,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 5.dp)
                    )
                    Divider(modifier = Modifier.padding(top=10.dp))
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(15.dp),
                        modifier = Modifier
                            .padding(top = 15.dp)
                    ){
                        items(
                            orderDetailUiState.products,
                            key = {productListItem ->
                                productListItem.id
                            }
                        ){
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ){
                                Text(
                                    "${it.selectedAmount}x "+it.name
                                )
                                Text(
                                    "%.2f".format(it.pricePerAmount*it.selectedAmount)+" $"
                                )
                            }
                        }
                    }
                }
                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ){
                    Divider()
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        Text(
                            "Total sum",
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            "%.2f".format(orderDetailUiState.products.sumOf { (it.selectedAmount*it.pricePerAmount).toDouble() })+" $",
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}


//@Preview(showSystemUi = true)
@Preview(showSystemUi = true)
@Composable
private fun OrderDetailDialogPreview(){
    OrderDetailDialog(
        onDismiss = {},
        orderDetailUiState = OrderDetailUiState(
            orderId = "",
            vendorName = "ABC Enterprises",
            orderDate = "22-Jan-2022",
            products = emptyList()
        )
    )
}