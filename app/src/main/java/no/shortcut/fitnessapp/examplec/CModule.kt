package no.shortcut.fitnessapp.examplec

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val cModule = module {
    viewModel<CViewModel>()
}
