package no.shortcut.fitnessapp.exampleb

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val bModule = module {
    viewModel<BViewModel>()
}
