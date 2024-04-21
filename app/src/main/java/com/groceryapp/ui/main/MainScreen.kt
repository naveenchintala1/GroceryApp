package com.groceryapp.ui.main

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.gson.Gson
import com.groceryapp.R
import com.groceryapp.routing.Screen
import com.groceryapp.ui.model.ProductModel
import com.groceryapp.ui.theme.GroceryAppTheme
import com.groceryapp.ui.theme.orange
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class,
    DelicateCoroutinesApi::class
)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "MutableCollectionMutableState")
@Composable
fun MainScreen(navController: NavController) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    val list = ArrayList<ProductModel>().apply {
        add(ProductModel(id = "", image = "", name = "Test Product", price = "120.00"))
        add(ProductModel(id = "", image = "", name = "Test Product", price = "120.00"))
        add(ProductModel(id = "", image = "", name = "Test Product", price = "120.00"))
        add(ProductModel(id = "", image = "", name = "Test Product", price = "120.00"))
        add(ProductModel(id = "", image = "", name = "Test Product", price = "120.00"))
        add(ProductModel(id = "", image = "", name = "Test Product", price = "120.00"))
        add(ProductModel(id = "", image = "", name = "Test Product", price = "120.00"))
        add(ProductModel(id = "", image = "", name = "Test Product", price = "120.00"))
        add(ProductModel(id = "", image = "", name = "Test Product", price = "120.00"))
        add(ProductModel(id = "", image = "", name = "Test Product", price = "120.00"))
        add(ProductModel(id = "", image = "", name = "Test Product", price = "120.00"))
    }
    LaunchedEffect(Unit) {
        GlobalScope.launch {
            try {
                val client = OkHttpClient()
                val request = Request.Builder()
                    .url("https://fakestoreapi.com/products")
                    .build()
                val response = client.newCall(request).execute()
                if(response.body()!=null) {
                    val result: String = Gson().toJson(response.body()!!.string())
                    if(result!=null) {
                        val json = JSONObject(result)
                        Log.e("TAG", "MainScreen: response=>" + json)
                    }

                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

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
                            text = "Home Screen", color = Color.White,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            style = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
                        )

                    },
                    actions = {
                        IconButton(
                            onClick = {
                                navController.navigate(Screen.WishlistScreen.route)
                            }
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_like),
                                contentDescription = "Image",
                                contentScale = ContentScale.Fit,
                                modifier = Modifier
                                    .width(35.dp)
                                    .height(35.dp)
                                    .clickable {
                                        navController.navigate(Screen.WishlistScreen.route)
                                    }
                            )
                        }
                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = orange,
                        titleContentColor = Color.White
                    )
                )
                Spacer(Modifier.height(10.dp))
                Column {
                    list.forEachIndexed { index, productModel ->
                        Card(
                            modifier = Modifier
                                .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
                                .fillMaxWidth()
                                .height(170.dp)
                                .clickable {
                                    navController.navigate(Screen.DetailScreen.route)
                                },
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
                                Row(
                                    modifier = Modifier
                                        .padding(end = 10.dp)
                                        .align(Alignment.TopEnd)
                                ) {
                                    Image(
                                        painter = painterResource(id = if (productModel.isLiked) R.drawable.ic_like else R.drawable.ic_dislike),
                                        contentDescription = "Image",
                                        contentScale = ContentScale.Fit,
                                        modifier = Modifier
                                            .width(35.dp)
                                            .height(35.dp)
                                            .clickable {
                                                productModel.isLiked = !productModel.isLiked
                                            }
                                    )


                                }
                            }
                            Text(
                                productModel.name ?: "",
                                fontSize = 14.sp,
                                color = Color.Black,
                                modifier = Modifier
                                    .padding(vertical = 5.dp, horizontal = 10.dp)
                            )
                            Text(
                                productModel.price ?: "",
                                fontSize = 14.sp,
                                color = Color.Black,
                                modifier = Modifier
                                    .padding(vertical = 5.dp, horizontal = 10.dp)
                            )
                        }
                    }
                }
            }
        }


    }



}
