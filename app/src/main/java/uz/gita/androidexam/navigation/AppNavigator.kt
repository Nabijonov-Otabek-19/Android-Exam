package uz.gita.androidexam.navigation

interface AppNavigator {
    suspend fun navigateTo(screen: AppScreen)
    suspend fun back()
}