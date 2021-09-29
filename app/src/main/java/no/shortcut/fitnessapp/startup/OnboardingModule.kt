package no.shortcut.fitnessapp.startup

import no.shortcut.fitnessapp.startup.view.OnboardingViewModel
import no.shortcut.fitnessapp.startup.view.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val onboardingModule = module {
    viewModel<OnboardingViewModel>()
    viewModel<SplashViewModel>()
}
