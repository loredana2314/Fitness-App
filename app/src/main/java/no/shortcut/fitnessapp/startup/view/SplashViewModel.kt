package no.shortcut.fitnessapp.startup.view

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import no.shortcut.fitnessapp.common.livedata.Event
import no.shortcut.fitnessapp.common.service.PreferenceService
import no.shortcut.fitnessapp.common.service.RemoteConfig

class SplashViewModel(
    remoteConfig: RemoteConfig,
    preferences: PreferenceService
) : ViewModel() {

    private val _navigationEvent = MutableLiveData<SplashNavigationEvent>()
    val navigationEvent = _navigationEvent.map(::Event)

    init {
        viewModelScope.launch(Dispatchers.IO) {
            remoteConfig.init()
            when {
                remoteConfig.isVersionLocked() -> _navigationEvent.postValue(
                    SplashNavigationVersionLock
                )
                !preferences.onboardingCompleted.first() -> _navigationEvent.postValue(
                    SplashNavigationOnboarding
                )
                else -> _navigationEvent.postValue(SplashNavigationMain)
            }
        }
    }
}
