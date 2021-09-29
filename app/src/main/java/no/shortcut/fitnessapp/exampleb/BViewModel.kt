package no.shortcut.fitnessapp.exampleb

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import no.shortcut.fitnessapp.common.livedata.Event

class BViewModel : ViewModel() {

    private val _navigation = MutableLiveData<Event<BNavigation>>()
    val navigation = _navigation.map { it }
}
