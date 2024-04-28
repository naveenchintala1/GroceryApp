package com.groceryapp.ui.cartScreen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.groceryapp.R
import com.groceryapp.application.AppConfig
import com.groceryapp.routing.Screen
import com.groceryapp.ui.model.CartModel
import com.groceryapp.ui.model.ProductModel
import com.groceryapp.ui.theme.GroceryAppTheme
import com.groceryapp.ui.theme.orange
import com.groceryapp.ui.theme.white
import com.groceryapp.utils.RoundedButton

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "MutableCollectionMutableState")
@Composable
fun CartScreen(navController: NavController) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    var amount = 0
    val list = arrayListOf<String>().apply {
        clear()
        add("Pay via credit card / debit card ")
        add("Pay via net banking ")
        add("Pay via upi ")
        add("Pay via wallet ")
    }
    var option by remember { mutableStateOf("") }
    var isPlace by remember { mutableStateOf(false) }
    GroceryAppTheme {
        Scaffold {
            Column(
                modifier = Modifier
                    .background(color = orange)
                    .padding(top = 40.dp)
                    .verticalScroll(scrollState)
            ) {
                SmallTopAppBar(
                    title = {
                        Text(
                            text = "Cart Screen", color = Color.White,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            style = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
                        )
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                navController.navigateUp()
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.ArrowBack,
                                tint = Color.White,
                                contentDescription = "Back"
                            )
                        }
                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = orange,
                        titleContentColor = Color.White
                    )
                )
                Spacer(Modifier.height(10.dp))

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = white)
                ) {
                    if (AppConfig.cartList.size > 0) {
                        AppConfig.cartList.forEachIndexed { index, productModel ->
                            val productPrice = productModel.price.toInt()
                            amount += productPrice
                            Card(
                                modifier = Modifier
                                    .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
                                    .fillMaxWidth()
                                    .height(170.dp),
                                shape = RoundedCornerShape(10.dp),
                                colors = CardDefaults.cardColors(containerColor = Color.White),
                                elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
                            ) {
                                Spacer(Modifier.height(10.dp))
                                Box(Modifier) {
                                    Image(
                                        painter = painterResource(id = R.drawable.ic_grocery),
                                        contentDescription = "Image",
                                        contentScale = ContentScale.FillWidth,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(100.dp)
                                    )
                                }
                                Text(
                                    productModel.name ?: "",
                                    fontSize = 14.sp,
                                    color = Color.Black,
                                    modifier = Modifier
                                        .padding(vertical = 5.dp, horizontal = 10.dp)
                                )
                                Text(
                                    productModel.price,
                                    fontSize = 14.sp,
                                    color = Color.Black,
                                    modifier = Modifier
                                        .padding(vertical = 5.dp, horizontal = 10.dp)
                                )
                            }
                        }
                        Spacer(Modifier.height(10.dp))
                        Text(
                            "Total Amount : $amount",
                            fontSize = 14.sp,
                            color = Color.Black,
                            modifier = Modifier
                                .padding(vertical = 5.dp, horizontal = 10.dp)
                        )
                        Spacer(Modifier.height(10.dp))
                        Text(
                            text = "select payment option : ", color = Color.Gray,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            style = TextStyle(
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 18.sp
                            )
                        )
                        Column(
                            modifier = Modifier.padding(8.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            list.forEach { name ->
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    RadioButton(
                                        selected = (name == option),
                                        onClick = { option = name }
                                    )
                                    Text(
                                        text = name,
                                        style = MaterialTheme.typography.bodyMedium,
                                        modifier = Modifier
                                            .padding(start = 8.dp)
                                            .clickable {
                                                option = name
                                            }
                                    )
                                }
                            }

                        }
                        Spacer(Modifier.height(10.dp))
                        RoundedButton(
                            text = "Pay and order",
                            onClick = {
                                if (option.isEmpty()) {
                                    Toast.makeText(
                                        context,
                                        "Please select payment option.",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                } else {
                                    isPlace = true
                                }
                            }
                        )
                    } else {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color = white),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "No Record Found.",
                                modifier = Modifier
                                    .fillMaxSize(),
                                color = Color.Black,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }


            }

            if (isPlace) {
                AlertDialog(
                    onDismissRequest = {
                        isPlace = false
                    },
                    title = { Text(stringResource(id = R.string.app_name)) },
                    text = { Text("You have successfully places your order.") },
                    confirmButton = {
                        RoundedButton(
                            text = "Ok",
                            textColor = white,
                            onClick = {
                                AppConfig.cartList.clear()
                                navController.navigate(Screen.MainScreen.route)
                                isPlace = false
                            }
                        )
                    },
                    dismissButton = { }
                )
            }
        }

    }
}

