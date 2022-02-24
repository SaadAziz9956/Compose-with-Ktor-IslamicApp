package com.example.islamicapp.screens.dashboard

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.expensemanagment.R
import com.example.expensemanagment.ui.theme.*


@Composable
fun DashboardScreen() {

    Surface(
        color = AppBackground,
        modifier = Modifier
            .fillMaxHeight(1f)
            .fillMaxWidth(1f)
    ) {
        ScreenUi()
    }

}

@Composable
fun ScreenUi() {

    Column(modifier = Modifier.fillMaxSize()) {

        TopBar()

        Cards()

    }

}

@Composable
fun Cards() {

    Column(
        modifier = Modifier.padding(
            horizontal = 17.dp,
            vertical = 15.dp
        )
    ) {

        IncomeCard()

        Divider(
            modifier = Modifier.padding(5.dp),
            color = AppBackground
        )

        ExpenseCard()

        Transaction()

    }

}

@Composable
fun Transaction() {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 30.dp,
                bottom = 10.dp
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Transactions",
            fontFamily = FontFamily(
                Font(R.font.ralewayregular)
            ),
            fontSize = 16.sp,
            color = GreyText
        )
        Text(
            text = "See more...",
            fontFamily = FontFamily(
                Font(R.font.ralewayregular)
            ),
            fontSize = 12.sp,
            color = GreyText
        )
    }

    
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .background(
                        color = LightOrange,
                        shape = RoundedCornerShape(12.dp)
                    )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_food), contentDescription = "",
                    modifier = Modifier.padding(8.dp)
                )
            }

            Column(modifier = Modifier.padding(start = 10.dp)) {
                Text(
                    text = "Food",
                    fontFamily = FontFamily(
                        Font(R.font.ralewayregular)
                    ),
                    fontSize = 14.sp,
                    color = GreyText
                )
                Text(
                    text = "1 Transaction",
                    fontFamily = FontFamily(
                        Font(R.font.ralewayregular)
                    ),
                    fontSize = 10.sp,
                    color = GreyText
                )
            }
        }

        Text(
            text = "200 PKR",
            fontFamily = FontFamily(
                Font(R.font.ralewayregular)
            ),
            fontSize = 14.sp,
            color = Color.Black
        )

    }

}


@Composable
fun IncomeCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color.White,
            )
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(15.dp)
        ) {

            Row {
                Box(
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .height(27.dp)
                        .width(3.dp)
                        .background(
                            color = Green,
                            shape = RoundedCornerShape(
                                5.dp
                            )
                        )
                )

                Text(
                    text = "Income",
                    fontFamily = FontFamily(
                        Font(R.font.ralewayregular)
                    ),
                    fontSize = 14.sp,
                    color = GreyText
                )
            }

            Text(
                text = "+200 PKR",
                fontFamily = FontFamily(
                    Font(R.font.ralewayregular)
                ),
                fontSize = 14.sp,
                color = Green
            )

        }

    }

}


@Composable
fun ExpenseCard() {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color.White,
            )
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(15.dp)
        ) {

            Row {
                Box(
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .height(27.dp)
                        .width(3.dp)
                        .background(
                            color = MudRed,
                            shape = RoundedCornerShape(
                                5.dp
                            )
                        )
                )

                Text(
                    text = "Expense",
                    fontFamily = FontFamily(
                        Font(R.font.ralewayregular)
                    ),
                    fontSize = 14.sp,
                    color = GreyText
                )
            }

            Text(
                text = "-200 PKR",
                fontFamily = FontFamily(
                    Font(R.font.ralewayregular)
                ),
                fontSize = 14.sp,
                color = MudRed
            )

        }

    }

}

@Composable
fun TopBar() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Blue
            )
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Icon()
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 25.dp,
                    start = 15.dp
                ),
            horizontalArrangement = Arrangement.Start
        ) {


            Text(
                text = "Overview",
                fontSize = 30.sp,
                color = Color.White,
                fontFamily = FontFamily(
                    Font(R.font.ralewayregular)
                )
            )

        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 15.dp,
                    vertical = 10.dp
                )
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(
                        corner = CornerSize(6.dp)
                    )
                )
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column {

                    Text(
                        text = "Remaining Income",
                        color = GreyText,
                        fontSize = 10.sp
                    )

                    Text(
                        text = "1000 PKR",
                        color = Color.Black,
                        fontSize = 20.sp
                    )

                }

                Box(
                    modifier = Modifier
                        .background(
                            color = LightGrey,
                            shape = RoundedCornerShape(5.dp)
                        )
                        .padding(
                            horizontal = 10.dp,
                            vertical = 4.dp
                        )
                ) {

                    Text(
                        text = "Today",
                        color = Color.Black,
                        fontSize = 10.sp
                    )

                }

            }

        }

    }
}

@Composable
fun Icon() {
    Image(
        painter = painterResource(R.drawable.ic_menu),
        contentDescription = "",
        modifier = Modifier.padding(
            20.dp
        )
    )
}

@Composable
@Preview
fun DashboardPreview() {
    DashboardScreen()
}