package com.groceryapp.ui.reviewScreen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.groceryapp.R
import com.groceryapp.ui.theme.GroceryAppTheme
import com.groceryapp.ui.theme.orange
import com.groceryapp.ui.theme.white
import com.groceryapp.utils.RoundedButton

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ReviewScreen(navController: NavController) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    var rating by remember { mutableStateOf(0F) }

    var review by remember { mutableStateOf("") }
    var feedBack by remember { mutableStateOf("") }
    var isReviewed by remember { mutableStateOf(false) }
    GroceryAppTheme {
        Scaffold {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = orange)
                    .padding(top = 40.dp)
            ) {
                SmallTopAppBar(
                    title = {
                        Text(
                            text = "Rate & Review", color = Color.White,
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

                Column(modifier = Modifier.fillMaxSize().background(color = white)) {
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        "Rating",
                        modifier = Modifier.fillMaxWidth(),
                        style = TextStyle(color = white)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    StarRatingBar(
                        maxStars = 5,
                        rating = rating,
                        onRatingChanged = {
                            rating = it
                        }
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        "Review",
                        modifier = Modifier.fillMaxWidth(),
                        style = TextStyle(color = white)
                    )
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp)
                            .padding(start = 15.dp, top = 10.dp, end = 15.dp)
                            .background(Color.White, RoundedCornerShape(5.dp)),
                        shape = RoundedCornerShape(5.dp),
                        value = review,
                        placeholder = {
                            Text("Enter review", fontSize = 16.sp)
                        },
                        onValueChange = { review = it },
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                        maxLines = 3
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        "Feedback",
                        modifier = Modifier.fillMaxWidth(),
                        style = TextStyle(color = white)
                    )
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp)
                            .padding(start = 15.dp, top = 10.dp, end = 15.dp)
                            .background(Color.White, RoundedCornerShape(5.dp)),
                        shape = RoundedCornerShape(5.dp),
                        value = feedBack,
                        placeholder = {
                            Text("Enter feedback", fontSize = 16.sp)
                        },
                        onValueChange = { feedBack = it },
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                        maxLines = 3
                    )
                    Spacer(Modifier.height(30.dp))
                    Row(
                        modifier = Modifier
                    ) {
                        RoundedButton(
                            text = "Submit rating",
                            textColor = white,
                            onClick = {
                                if (rating <= 0F) {
                                    Toast.makeText(
                                        context,
                                        "Please select rating.",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                } else if (review.isEmpty()) {
                                    Toast.makeText(
                                        context,
                                        "Please enter review.",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                } else if (feedBack.isEmpty()) {
                                    Toast.makeText(
                                        context,
                                        "Please enter feedback.",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                } else {
                                    isReviewed = true
                                }

                            }
                        )
                    }
                    Spacer(Modifier.height(30.dp))

                }
            }
        }
        if (isReviewed) {
            AlertDialog(
                onDismissRequest = {
                    isReviewed = false
                },
                title = { Text(stringResource(id = R.string.app_name)) },
                text = { Text("Thanks for giving your feedback.") },
                confirmButton = {
                    RoundedButton(
                        text = "Ok",
                        textColor = white,
                        onClick = {
                            navController.navigateUp()
                            isReviewed = false
                        }
                    )
                },
                dismissButton = {}
            )
        }


    }
}

@Composable
fun StarRatingBar(
    maxStars: Int = 5,
    rating: Float,
    onRatingChanged: (Float) -> Unit
) {
    val density = LocalDensity.current.density
    val starSize = (12f * density).dp
    val starSpacing = (0.5f * density).dp

    Row(
        modifier = Modifier.selectableGroup(), horizontalArrangement = Arrangement.Start
    ) {
        for (i in 1..maxStars) {
            val isSelected = i <= rating
            val icon = if (isSelected) Icons.Filled.Star else Icons.Default.Star
            val iconTintColor = if (isSelected) orange else Color.Black
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconTintColor,
                modifier = Modifier
                    .selectable(
                        selected = isSelected,
                        onClick = {
                            onRatingChanged(i.toFloat())
                        }
                    )
                    .width(starSize)
                    .height(starSize)
            )

            if (i < maxStars) {
                Spacer(modifier = Modifier.width(starSpacing))
            }
        }
    }
}