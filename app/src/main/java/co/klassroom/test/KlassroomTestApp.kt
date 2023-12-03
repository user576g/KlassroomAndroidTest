package co.klassroom.test

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.klassroom.test.api_screen.ApiScreen
import co.klassroom.test.charlotte_screen.CharlotteScreen
import kotlinx.coroutines.launch

@Preview
@Composable
fun AppPreview() {
    KlassroomTestApp()
}

enum class Screen { Charlotte, Api }

@Composable
fun KlassroomTestApp() {
    val drawerState: DrawerState = rememberDrawerState(DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    fun closeDrawer() {
        coroutineScope.launch {
            drawerState.close()
        }
    }
    fun openDrawer() {
        coroutineScope.launch {
            drawerState.open()
        }
    }

    val selectedScreen = remember { mutableStateOf(Screen.Charlotte) }

    androidx.compose.material3.ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(Modifier.width(200.dp)) {
                Column(
                    modifier = Modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.Center,
                ) {
                    NavigationDrawerItem(
                        label = {
                            Text(
                                text = stringResource(id = R.string.charlotte_perriands).take(20),
                            )
                        },
                        selected = Screen.Charlotte == selectedScreen.value,
                        onClick = {
                            selectedScreen.value = Screen.Charlotte
                            closeDrawer()
                        }
                    )
                    NavigationDrawerItem(
                        label = {
                            Text(
                                text = stringResource(id = R.string.api_recycler_view),
                            )
                        },
                        selected = Screen.Api == selectedScreen.value,
                        onClick = {
                            selectedScreen.value = Screen.Api
                            closeDrawer()
                        }
                    )
                }
            }
        }
    ) {
        when(selectedScreen.value) {
            Screen.Charlotte -> {
                CharlotteScreen(onMenuClick = ::openDrawer)
            }
            Screen.Api -> {
                ApiScreen(onMenuClick = ::openDrawer)
            }
        }
    }
}
