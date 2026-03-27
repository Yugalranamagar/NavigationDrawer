package com.example.navigationdrawer

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.navigationdrawer.ui.theme.Grey
import com.example.navigationdrawer.ui.theme.NavigationDrawerTheme
import kotlinx.coroutines.launch
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Home
import androidx.navigation.compose.composable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.ShoppingCart
import com.example.navigationdrawer.Home
import com.example.navigationdrawer.Cart
import com.example.navigationdrawer.Favorite
import com.example.navigationdrawer.ui.theme.Black

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavigationDrawerTheme {
                NavigationDrawer()

            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationDrawer() {
    val navigationController = rememberNavController()
    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = true,
        drawerContent = {
            ModalDrawerSheet {
                // Header Box
                Box(
                    modifier = Modifier
                        .background(Grey)
                        .fillMaxWidth() // Changed from fillMaxSize to allow items to show
                        .height(120.dp)
                ) {
                    Text(text = "")
                } // Added missing closing bracket for Box

                Divider()

                NavigationDrawerItem(
                    label = { Text(text = "Home", color = Black) },
                    selected = false,
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Home, // Fixed 'v' to 'V'
                            contentDescription = "home", // Fixed spelling
                            tint = Black
                        )
                    },
                    onClick = {
                        coroutineScope.launch { drawerState.close() }
                        navigationController.navigate("home") {
                            popUpTo(0) // Fixed "id:0"
                        }
                    }
                )
                NavigationDrawerItem(
                    label = { Text(text = "Cart", color = Black) },
                    selected = false,
                    icon = {Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = "Cart",
                        tint = Black
                    ) },
                    onClick = {
                        coroutineScope.launch { drawerState.close() }
                        navigationController.navigate("cart") {
                            popUpTo(0)
                        }
                    }
                )
                NavigationDrawerItem(
                    label = { Text(text = "Favorite", color = Black) },
                    selected = false,
                    icon = {Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Favorite",
                        tint = Black
                    ) },
                    onClick = {
                        coroutineScope.launch { drawerState.close() }
                        navigationController.navigate("Favorite") {
                            popUpTo(0)
                        }
                    }
                )
                NavigationDrawerItem(
                    label = { Text(text = "Calendar", color = Black) },
                    selected = false,
                    icon = {Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Calendar",
                        tint = Black
                    ) },
                    onClick = {
                        coroutineScope.launch { drawerState.close() }
                        navigationController.navigate("Calendar") {
                            popUpTo(0)
                        }
                    }
                )
                NavigationDrawerItem(
                    label = { Text(text = "Bin", color = Black) },
                    selected = false,
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Delete, // Fixed 'v' to 'V'
                            contentDescription = "Bin", // Fixed spelling
                            tint = Black
                        )
                    },
                    onClick = {
                        coroutineScope.launch { drawerState.close() }
                        navigationController.navigate("Bin") {
                            popUpTo(0) // Fixed "id:0"
                        }
                    }
                )
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "NavigationDrawer") },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Grey,
                        titleContentColor = Color.Black,
                        navigationIconContentColor = Color.Black
                    ),
                    navigationIcon = {
                        IconButton(onClick = {
                            coroutineScope.launch {
                                drawerState.open()
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Default.Menu, // Using Default.Menu
                                contentDescription = "Menu button"
                            )
                        }
                    }
                )
            }
        ) { innerPadding -> // Added innerPadding
            Box(modifier = Modifier.padding(innerPadding)) {
                NavHost(
                    navController = navigationController,
                    startDestination = Screens.Home.screen
                ) {
                    composable(Screens.Home.screen) { Home() }
                    composable(Screens.Cart.screen) { Cart() }
                    composable(Screens.Favorite.screen) { Favorite() }
                    composable(route = Screens.Calendar.screen) {Calendar()}
                    composable(route = Screens.Bin.screen){Bin()}

                }
            }
        }
    }
}