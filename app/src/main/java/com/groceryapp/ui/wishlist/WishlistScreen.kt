package com.groceryapp.ui.wishlist

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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
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
fun WishlistScreen(navController: NavController) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    var isAdded by remember { mutableStateOf(false) }
    val list = ArrayList<ProductModel>().apply {
        add(ProductModel(isLiked = true,detail="apple, (Malus domestica), domesticated tree and fruit of the rose family (Rosaceae), one of the most widely cultivated tree fruits. Apples are predominantly grown for sale as fresh fruit, though apples are also used commercially for vinegar, juice, jelly, applesauce, and apple butter and are canned as pie stock.",id = "", image = R.drawable.ic_bread, name = "Apple", price = 80))
        add(ProductModel(isLiked = true,detail="Cheese is a dairy product produced in a range of flavors, textures, and forms by coagulation of the milk protein casein. It comprises proteins and fat from milk (usually the milk of cows, buffalo, goats or sheep).",id = "", image = R.drawable.ic_cheese, name = "Cheese", price = 450))
        add(ProductModel(isLiked = true,detail="A banana is an elongated, edible fruit – botanically a berry – produced by several kinds of large herbaceous flowering plants in the genus Musa. In some countries, cooking bananas are called plantains, distinguishing them from dessert bananas.",id = "", image = R.drawable.ic_banana, name = "Bananas 2 KG ", price = 80))
        add(ProductModel(isLiked = true,detail="Butter is a water-in-oil emulsion resulting from an inversion of the cream, where the milk proteins are the emulsifiers. Butter remains a firm solid when refrigerated but softens to a spreadable consistency at room temperature and melts to a thin liquid consistency at 32 to 35 °C (90 to 95 °F).",id = "", image = R.drawable.ic_butter, name = "Butter", price = 500))
        add(ProductModel(isLiked = true,detail="Sugars are carbohydrates. Like all carbohydrates, they provide a source of energy in our diet. Sugar is a term that includes all sweet carbohydrates, although the term is most often used to describe sucrose or table sugar, a 'double sugar'.",id = "", image = R.drawable.ic_suger, name = "Sugar 5 Kg Pack Cost ", price = 250))
        add(ProductModel(isLiked = true,detail="A refined vegetable oil may mean an oil simply purified by the method of settling, or it may mean an oil treated with acid, or it may mean an oil purified with an alkali, filtered or bleached with fuller's earth, or a similar filtering me- dium.",id = "", image = R.drawable.ic_oil, name = "Oil 1 litre ", price = 110))
        add(ProductModel(isLiked = true,detail="milk, liquid secreted by the mammary glands of female mammals to nourish their young for a period beginning immediately after birth. The milk of domesticated animals is also an important food source for humans, either as a fresh fluid or processed into a number of dairy products such as butter and cheese.",id = "", image = R.drawable.ic_milk, name = "Milk 2 KG ", price = 100))
        add(ProductModel(isLiked = true,detail="soup, liquid food prepared by cooking meat, poultry, fish, legumes, or vegetables with seasonings in water, stock, milk, or some other liquid medium.",id = "", image = R.drawable.ic_soup, name = "Soup Cost ", price = 80))
        add(ProductModel(isLiked = true,detail="Honey is a sweet liquid that bees produce using nectar from flowers. People throughout the world have hailed the health benefits of honey for thousands of years.",id = "", image = R.drawable.ic_honey, name = "Honey Cost ", price = 170))
        add(ProductModel(isLiked = true,detail="Coriander, also known as cilantro and dhaniya, is an annual herb in the family Apiaceae. All parts of the plant are edible, but the fresh leaves and the dried seeds are the parts most traditionally used in cooking.",id = "", image = R.drawable.ic_coridor, name = "Coriander Cost ", price = 80))
        add(ProductModel(isLiked = true,detail="rice, (Oryza sativa), edible starchy cereal grain and the grass plant (family Poaceae) by which it is produced. Roughly one-half of the world population, including virtually all of East and Southeast Asia, is wholly dependent upon rice as a staple food; 95 percent of the world's rice crop is eaten by humans.",id = "", image = R.drawable.ic_rice, name = "Rice 1 KG  ", price = 80))
        add(ProductModel(isLiked = true,detail="The kidney bean is a variety of the common bean (P. vulgaris), so named because of its kidney-like shape and its colour. The optimum moisture range is 12–14%. The colour of kidney beans ranges all the way from very light red to very dark, almost purple.",id = "", image = R.drawable.ic_beans, name = "Red kidney beans ", price = 96))
        add(ProductModel(isLiked = true,detail="White beans are a nutritional powerhouse, as they're packed with fiber and protein and a good source of numerous micronutrients, including folate, magnesium, and vitamin B6. A 1-cup (170-gram) serving of cooked white beans provides ( 1 ): Calories: 242. Protein: 17 grams.",id = "", image = R.drawable.ic_white, name = "White beans  ", price = 130))
        add(ProductModel(isLiked = true,detail="Green lentils hold their shape exceptionally well, making them ideal for salads. In addition to high protein and fiber content, the lentil is also packed with iron, zinc, potassium and magnesium. The bushy, annual lentil plant is a member of the legume family, growing about 16 inches tall with seed-producing pods.",id = "", image = R.drawable.ic_green, name = "Green lentils   ", price = 90))
    }
    GroceryAppTheme {
        Scaffold {
            Column(
                modifier = Modifier
                    .background(color = orange)
                    .padding(top = 40.dp)
            ) {
                SmallTopAppBar(
                    title = {
                        Text(
                            text = "Wishlist Screen", color = Color.White,
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
                Spacer(Modifier.height(10.dp))
                Column(modifier = Modifier.verticalScroll(scrollState)) {
                    list.forEachIndexed { index, productModel ->
                        Card(
                            modifier = Modifier
                                .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
                                .fillMaxWidth()
                                .height(350.dp)
                                .clickable {
                                    navController.navigate(Screen.DetailScreen.route + "/${productModel.name}" + "/${productModel.image}" + "/${productModel.price}"+ "/${productModel.detail}")
                                },
                            shape = RoundedCornerShape(10.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
                        ) {
                            Spacer(Modifier.height(10.dp))
                            Box(Modifier) {
                                Image(
                                    painter = painterResource(id = productModel.image),
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
                                "${productModel.price}",
                                fontSize = 14.sp,
                                color = Color.Black,
                                modifier = Modifier
                                    .padding(vertical = 5.dp, horizontal = 10.dp)
                            )
                            var quantity by remember { mutableStateOf(0) }
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
                                    .padding(start = 50.dp, end = 50.dp)
                                    .offset(0.dp, 32.dp)
                            ) {
                                RoundedButton(
                                    text = "Add to Cart",
                                    onClick = {
                                        val productPrice = productModel.price.toInt()
                                        val total = productPrice*quantity
                                        if (quantity>0) {
                                            AppConfig.cartList.add(CartModel(image = productModel.image, name = productModel.name, price = total.toString(), cartCount = quantity.toString()))
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

