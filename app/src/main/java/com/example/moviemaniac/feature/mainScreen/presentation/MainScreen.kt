package com.example.moviemaniac.feature.mainScreen.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.moviemaniac.core.util.NavRoutes
import com.example.moviemaniac.feature.mainScreen.data.BottomNavItem
import com.example.moviemaniac.ui.components.MainScreenNavHost

@Composable
fun MainScreen(
    uiState: MainScreenUiState,
    onEvent: (MainScreenUiEvent) -> Unit,
    navController: NavHostController
) {

    val bottomNavItemList = listOf(
        BottomNavItem(NavRoutes.movieScreen, "Movies", Icons.Default.Home),
        BottomNavItem(NavRoutes.searchScreen, "Search", Icons.Default.Search),
        BottomNavItem(NavRoutes.tvShowScreen, "Recent", Icons.Default.History),
        BottomNavItem(NavRoutes.settingScreen, "Settings", Icons.Default.Settings)
    )

    val innerNavController = rememberNavController()
    val backStackEntry by innerNavController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding(),
        bottomBar = {
            NavigationBar(
                modifier = Modifier
                    .height(56.dp),
                tonalElevation = 0.dp
            ) {
                bottomNavItemList.forEachIndexed { _, item ->
                    NavigationBarItem(
                        selected = currentRoute == item.route,
                        onClick = {
                            innerNavController.navigate(item.route) {
                                popUpTo(NavRoutes.movieScreen) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        label = {
                            Text(
                                text = item.label,
                                fontSize = 12.sp
                            )
                        },
                        icon = {
                            Icon(
                                item.icon,
                                contentDescription = "Icon",
                                modifier = Modifier.size(24.dp)
                            )
                        },
                        modifier = Modifier.padding(vertical = 8.dp),
                        colors = NavigationBarItemDefaults.colors(
                            indicatorColor = Color.Transparent,
                            selectedIconColor = Color.Red,
                            selectedTextColor = Color.Red
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        MainScreenNavHost(
            navController = innerNavController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//private fun PreviewMainScreen() {
//
//    val uiState = MainScreenUiState("")
//    val navController = rememberNavController()
//
//    MainScreen(
//        uiState = uiState,
//        onEvent = {},
//        navController = navController
//    )
//
//}