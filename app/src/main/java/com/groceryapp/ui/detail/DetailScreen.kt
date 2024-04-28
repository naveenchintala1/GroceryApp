package com.groceryapp.ui.detail

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.groceryapp.R
import com.groceryapp.application.AppConfig
import com.groceryapp.ui.model.CartModel
import com.groceryapp.ui.theme.GroceryAppTheme
import com.groceryapp.ui.theme.orange
import com.groceryapp.ui.theme.white
import com.groceryapp.utils.RoundedButton

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailScreen(navController: NavController,name:String,image:Int,price:String,detail:String) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    var quantity by remember { mutableStateOf(0) }
    var isAdded by remember { mutableStateOf(false) }
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
                            text = "Product Detail", color = Color.White,
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
                            Icon(imageVector = Icons.Rounded.ArrowBack,
                                tint = Color.White,
                                contentDescription = "Back")
                        }
                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = orange,
                        titleContentColor = Color.White
                    )
                )

                Column(modifier = Modifier
                    .fillMaxSize()
                    .background(color = white)) {
                    Card(
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth()
                            .height(150.dp),
                        shape = RoundedCornerShape(10.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
                    ) {
                        Image(
                            painter = painterResource(id = image),
                            contentDescription = "Image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(150.dp)
                        )
                    }
                    Text(
                        name,
                        fontSize = 14.sp,
                        color = Color.Black,
                        modifier = Modifier
                            .padding(vertical = 5.dp, horizontal = 10.dp)
                    )
                    Text(
                        price,
                        fontSize = 14.sp,
                        color = Color.Black,
                        modifier = Modifier
                            .padding(vertical = 5.dp, horizontal = 10.dp)
                    )

                    Text(
                        "detail",
                        fontSize = 14.sp,
                        color = Color.Black,
                        modifier = Modifier
                            .padding(vertical = 5.dp, horizontal = 10.dp)
                    )
                    Text(
                        detail,
                        fontSize = 14.sp,
                        color = Color.Black,
                        modifier = Modifier
                            .padding(vertical = 5.dp, horizontal = 10.dp)
                    )

                    Row(
                        modifier = Modifier
                            .padding(10.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.minus),
                            modifier = Modifier
                                .width(36.dp)
                                .height(36.dp)
                                .border(BorderStroke(1.dp, colorResource(id = R.color.black)))
                                .padding(5.dp)
                                .clickable {
                                    if (quantity > 0) {
                                        quantity--
                                    } else {
                                        quantity = 0
                                    }

                                },
                            contentDescription = "Expandable Image"
                        )
                        Text(
                            "$quantity",
                            fontSize = 14.sp,
                            color = Color.Black,
                            modifier = Modifier
                                .padding(vertical = 5.dp, horizontal = 10.dp)
                        )
                        Image(
                            painter = painterResource(id = R.drawable.baseline_add_24),
                            modifier = Modifier
                                .width(36.dp)
                                .height(36.dp)
                                .border(BorderStroke(1.dp, colorResource(id = R.color.black)))
                                .padding(5.dp)
                                .clickable {
                                    quantity++
                                },
                            contentDescription = "Expandable Image"
                        )
                    }
                    Row(
                        modifier = Modifier
                            .padding(10.dp)
                    ) {
                        RoundedButton(
                            text = "Add to Cart",
                            onClick = {
                                val productPrice = price.toInt()
                                val total = productPrice*quantity
                                if (quantity>0) {
                                    AppConfig.cartList.add(CartModel(image = image, name = name, price = total.toString(), cartCount = quantity.toString()))
                                    quantity = 0
                                    isAdded = true
                                }else{
                                    Toast.makeText(context, "Please increase quantity.", Toast.LENGTH_SHORT).show()
                                }

                            }
                        )
                    }
                }
            }
            if (isAdded) {
                AlertDialog(
                    onDismissRequest = {
                        isAdded = false
                    },
                    title = { Text(stringResource(id = R.string.app_name)) },
                    text = { Text("Product Added Successfully.") },
                    confirmButton = {
                        RoundedButton(
                            text = "OK",
                            textColor = white,
                            onClick = {
                                isAdded = false
                            }
                        )
                    },
                    dismissButton = {}
                )
            }
        }


    }

}