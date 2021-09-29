package no.shortcut.fitnessapp.startup.view

sealed class SplashNavigationEvent()

object SplashNavigationVersionLock : SplashNavigationEvent()

object SplashNavigationOnboarding : SplashNavigationEvent()

object SplashNavigationMain : SplashNavigationEvent()
