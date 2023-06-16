package uz.gita.androidexam.navigation

import kotlinx.coroutines.flow.Flow
import uz.gita.androidexam.navigation.NavigationArg

interface NavigationHandler {
    val navigationBuffer: Flow<NavigationArg>
}