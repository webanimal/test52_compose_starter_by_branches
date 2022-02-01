package ru.webanimal.test52_compose01.core

import androidx.compose.runtime.Composable
import ru.webanimal.test52_compose01.feature_main.MainScreen
import ru.webanimal.test52_compose01.feature_onboarding.OnboardingScreen
import ru.webanimal.test52_compose01.feature_contacts.ContactsScreen

class ScreenProvider {

    @Composable
    fun Onboarding(onContinueClick: () -> Unit) = OnboardingScreen { onContinueClick() }

    @Composable
    fun Main() = MainScreen()

    @Composable
    fun Marketplace() = ContactsScreen()
}