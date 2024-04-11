package com.groceryapp.ui.sign_up

import android.annotation.SuppressLint
import android.app.Dialog
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.groceryapp.R
import com.groceryapp.routing.Screen
import com.groceryapp.ui.theme.GroceryAppTheme
import com.groceryapp.utils.OutlineFormField
import com.groceryapp.utils.RoundedButton

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SignUpScreen(navController: NavController) {
    val context = LocalContext.current
    var name by remember { mutableStateOf("") }
    var mobileNumber by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val db = Firebase.firestore
    GroceryAppTheme {
        Scaffold {
            Box(
                Modifier
                    .fillMaxSize()
                    .background(colorResource(id = R.color.white))
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_grocery),
                    contentDescription = "",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.6F)
                        .clip(
                            shape = RoundedCornerShape(
                                bottomStart = 30.dp, bottomEnd = 30.dp
                            )
                        )
                )

            }
        }

        Dialog(
            onDismissRequest = {

            },
            properties = DialogProperties(
                usePlatformDefaultWidth = false,
                dismissOnBackPress = true, dismissOnClickOutside = false
            ),
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Bottom
            ) {
                Box(Modifier.padding(bottom = 60.dp)) {
                    Card(
                        modifier = Modifier.padding(top = 30.dp, start = 30.dp, end = 30.dp),
                        shape = RoundedCornerShape(25.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(5.dp),
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .padding(20.dp)
                        ) {

                            Spacer(modifier = Modifier.height(10.dp))

                            Text(
                                "Registration",
                                style = TextStyle(fontSize = 30.sp, fontWeight = FontWeight.Bold),
                                textAlign = TextAlign.Center,
                            )
                            Spacer(modifier = Modifier.height(10.dp))

                            OutlineFormField(
                                value = name,
                                onValueChange = { text ->
                                    name = text
                                },
                                placeholder = "Enter Name",
                                keyboardType = KeyboardType.Text,
                            )

                            Spacer(Modifier.height(10.dp))
                            OutlineFormField(
                                value = mobileNumber,
                                onValueChange = { text ->
                                    mobileNumber = text
                                },
                                placeholder = "Enter Mobile Number",
                                keyboardType = KeyboardType.Phone,
                            )

                            Spacer(Modifier.height(10.dp))
                            OutlineFormField(
                                value = password,
                                onValueChange = { text ->
                                    password = text
                                },
                                placeholder = "Enter Password",
                                keyboardType = KeyboardType.Password,
                                visualTransformation = PasswordVisualTransformation(),
                            )

                            Spacer(modifier = Modifier.height(5.dp))

                        }
                        Spacer(modifier = Modifier.height(10.dp))
                    }

                    Row(
                        modifier = Modifier
                            .padding(start = 50.dp, end = 50.dp)
                            .align(BottomCenter)
                            .offset(0.dp, 32.dp)
                    ) {
                        RoundedButton(
                            text = "Register",
                            onClick = {
                                if (name.isNotEmpty()) {
                                    if (mobileNumber.isNotEmpty()) {
                                        if (password.isNotEmpty()) {
                                            val user = hashMapOf(
                                                "name" to name,
                                                "mobile" to mobileNumber,
                                                "password" to password
                                            )
                                            db.collection("users")
                                                .get()
                                                .addOnSuccessListener { result ->
                                                    if (result.isEmpty) {
                                                        db.collection("users")
                                                            .add(user)
                                                            .addOnSuccessListener { documentReference ->
                                                                Toast.makeText(
                                                                    context,
                                                                    "Register successfully.",
                                                                    Toast.LENGTH_SHORT
                                                                ).show()

                                                            }
                                                            .addOnFailureListener { e ->
                                                                Toast.makeText(
                                                                    context,
                                                                    e.message.toString(),
                                                                    Toast.LENGTH_SHORT
                                                                ).show()
                                                            }
                                                    } else {
                                                        for (document in result) {
                                                            if (document.data["mobile"] == mobileNumber &&
                                                                document.data["password"] == password
                                                            ) {
                                                                Toast.makeText(
                                                                    context,
                                                                    "Already exists.",
                                                                    Toast.LENGTH_SHORT
                                                                ).show()
                                                                return@addOnSuccessListener
                                                            } else {
                                                                db.collection("users")
                                                                    .add(user)
                                                                    .addOnSuccessListener { documentReference ->
                                                                        Toast.makeText(
                                                                            context,
                                                                            "Register successfully.",
                                                                            Toast.LENGTH_SHORT
                                                                        ).show()
                                                                        navController.navigate(Screen.MainScreen.route) {
                                                                            popUpTo(Screen.RegisterScreen.route) {
                                                                                inclusive = true
                                                                            }
                                                                        }
                                                                    }
                                                                    .addOnFailureListener { e ->
                                                                        Toast.makeText(
                                                                            context,
                                                                            e.message.toString(),
                                                                            Toast.LENGTH_SHORT
                                                                        ).show()
                                                                    }
                                                            }
                                                        }
                                                    }
                                                }
                                                .addOnFailureListener { exception ->
                                                    Toast.makeText(
                                                        context,
                                                        exception.message.toString(),
                                                        Toast.LENGTH_SHORT
                                                    ).show()
                                                }
                                        } else {
                                            Toast.makeText(
                                                context,
                                                "Please enter password.",
                                                Toast.LENGTH_LONG
                                            ).show()
                                        }
                                    } else {
                                        Toast.makeText(
                                            context,
                                            "Please enter mobile number.",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }
                                } else {
                                    Toast.makeText(
                                        context,
                                        "Please enter name.",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }
                        )
                    }

                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "Already have an account?",
                        textAlign = TextAlign.End,
                        style = TextStyle(color = colorResource(id = R.color.white))
                    )

                    Text(
                        " Login", modifier = Modifier
                            .clickable {
                                navController.navigate(Screen.LoginScreen.route)
                            }, textAlign = TextAlign.End,
                        style = TextStyle(color = colorResource(id = R.color.orange))
                    )
                }

                Spacer(modifier = Modifier.height(40.dp))


            }
        }
    }
}