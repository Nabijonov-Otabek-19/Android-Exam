package uz.gita.androidexam.presentation.screen.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.tab.*
import uz.gita.androidexam.navigation.AppScreen
import uz.gita.androidexam.presentation.screen.home.page.*
import uz.gita.androidexam.presentation.screen.home.page.home.HomePage
import uz.gita.androidexam.presentation.screen.home.page.shopping.ShoppingPage
import uz.gita.androidexam.ui.theme.AndroidExamTheme

class HomeScreen : AppScreen() {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        AndroidExamTheme {
            Surface(modifier = Modifier.fillMaxSize()) {
                TabNavigator(HomePage) {
                    Scaffold(
                        content = {
                            Box(
                                Modifier
                                    .fillMaxSize()
                                    .padding(it)
                            ) {
                                CurrentTab()
                            }
                        },
                        bottomBar = {
                            NavigationBar(modifier = Modifier) {
                                TabNavigationItem(tab = HomePage)
                                TabNavigationItem(tab = ShoppingPage)
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun RowScope.TabNavigationItem(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current

    NavigationBarItem(
        selected = tabNavigator.current == tab,
        onClick = { tabNavigator.current = tab },
        icon = { Icon(painter = tab.options.icon!!, contentDescription = tab.options.title) }
    )
}