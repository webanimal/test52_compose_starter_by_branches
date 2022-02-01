package ru.webanimal.test52_compose01.core

import androidx.compose.runtime.Composable
import ru.webanimal.test52_compose01.core.ScreenNavigator.CodelabType
import ru.webanimal.test52_compose01.core.ScreenNavigator.CodelabType.Layouts.CustomLayout
import ru.webanimal.test52_compose01.core.ScreenNavigator.CodelabType.Layouts.Scaffold
import ru.webanimal.test52_compose01.core.ScreenNavigator.CodelabType.Main
import ru.webanimal.test52_compose01.core.ScreenNavigator.CodelabType.Onboarding
import ru.webanimal.test52_compose01.feature_contacts.ContactsScreen
import ru.webanimal.test52_compose01.feature_custom_layout.CustomLayoutScreen
import ru.webanimal.test52_compose01.feature_main.MainScreen
import ru.webanimal.test52_compose01.feature_onboarding.OnboardingScreen

interface ScreenNavigator {

    @Composable
    fun NavigateTo(type: CodelabType)

    sealed class CodelabType {
        class Onboarding(val onContinueClick: () -> Unit) : CodelabType()
        object Main : CodelabType()
        sealed class Layouts : CodelabType() {
            object Scaffold : Layouts()
            object CustomLayout : Layouts()
        }
    }

    companion object {
        fun provideNavigator() = ScreenNavigatorImpl()
    }
}

class ScreenNavigatorImpl : ScreenNavigator {

    @Composable
    override fun NavigateTo(type: CodelabType) = when (type) {
        is Onboarding -> OnboardingScreen { type.onContinueClick.invoke() }
        Main -> MainScreen()
        Scaffold -> ContactsScreen()
        CustomLayout -> CustomLayoutScreen()
    }
}