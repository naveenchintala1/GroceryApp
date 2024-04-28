package com.groceryapp.ui.drawer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.groceryapp.R
import com.groceryapp.routing.Screen
import com.groceryapp.ui.theme.orange
import com.groceryapp.ui.theme.white

@Composable
fun TopBar(
    navController: NavController,
    onNavigationIconClick: () -> Unit,
    onCartClick: () -> Unit,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    if (currentRoute == null || currentRoute == Screen.LoginScreen.route) {
        return
    }

    TopAppBar(
        title = {
            Text(text = stringResource(id = R.string.app_name), color = white)
        },
        modifier = androidx.compose.ui.Modifier
            .background(orange)
            .padding(top = 45.dp),
        actions = {
            Image(
                painter = painterResource(id = R.drawable.baseline_shopping_cart_24),
                contentDescription = "Image",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .width(35.dp)
                    .height(35.dp)
                    .clickable {
                        onCartClick()
                    }
            )
        },
        backgroundColor = orange,
        navigationIcon = {
            IconButton(onClick = onNavigationIconClick) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = null,
                    tint = white
                )
            }
        }
    )
}