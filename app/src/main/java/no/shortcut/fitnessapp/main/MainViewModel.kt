package no.shortcut.fitnessapp.main

import android.content.Context
import androidx.lifecycle.*
import no.shortcut.fitnessapp.R
import no.shortcut.fitnessapp.common.livedata.Event

class MainViewModel(
    private val context: Context
) : ViewModel() {
    val currentFragment = MutableLiveData(R.id.splash_fragment)

    private val _navigation = MutableLiveData<MainNavigation>()
    val navigation = _navigation.map(::Event)

    val topLevelDestinations = setOf(
        R.id.a_fragment
    )

    val appbarVisible = currentFragment.map { id ->
        listOf(
            R.id.onboarding_fragment,
            R.id.splash_fragment,
            R.id.versionlock_fragment
        ).contains(id).not()
    }

    val toolbarTitle = currentFragment.map { id ->
        when (id) {
            R.id.a_fragment -> "Toolbar title"
            R.id.c_fragment -> "Toolbar title"
            else -> "Back"
        }
    }

    val bottomNavVisible = currentFragment.map { id ->
        listOf(
            R.id.a_fragment,
            R.id.c_fragment
        ).contains(id)
    }.distinctUntilChanged()
}
