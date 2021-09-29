package no.shortcut.fitnessapp.startup.view

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.tatarka.bindingcollectionadapter2.ItemBinding
import no.shortcut.fitnessapp.BR
import no.shortcut.fitnessapp.R
import no.shortcut.fitnessapp.common.livedata.Event
import no.shortcut.fitnessapp.common.service.PreferenceService
import se.ingenuity.lives.Lives

class OnboardingViewModel(
    context: Context,
    private val preferenceService: PreferenceService
) : ViewModel() {

    private val _navigation = MutableLiveData<OnboardingNavigation>()
    val navigation = _navigation.map(::Event)

    val currentPage = MutableLiveData<Int>()

    val pages = MutableLiveData(
        listOf(
            OnboardingPage(
                context.getString(R.string.onboarding_1_title),
                context.getString(R.string.onboarding_1_text),
                ContextCompat.getDrawable(context, R.drawable.ic_launcher_background),
                context.getString(R.string.onboarding_button)
            ),
            OnboardingPage(
                context.getString(R.string.onboarding_2_title),
                context.getString(R.string.onboarding_2_text),
                ContextCompat.getDrawable(context, R.drawable.ic_launcher_background),
                context.getString(R.string.onboarding_button)
            ),
            OnboardingPage(
                context.getString(R.string.onboarding_3_title),
                context.getString(R.string.onboarding_3_text),
                ContextCompat.getDrawable(context, R.drawable.ic_launcher_background),
                context.getString(R.string.onboarding_button_last)
            )
        )
    )

    val buttonText: LiveData<CharSequence> =
        Lives.combineLatest(currentPage, pages) { currentPage, pages ->
            pages[currentPage].buttonText
        }

    val itemBinding: ItemBinding<OnboardingPage> =
        ItemBinding.of(BR.onboardingPage, R.layout.onboarding_page)

    fun nextPage() {
        val current = currentPage.value!!
        if (current < pages.value!!.lastIndex) {
            currentPage.value = currentPage.value!! + 1
        } else {
            viewModelScope.launch(Dispatchers.IO) {
                preferenceService.setOnboardingCompleted(true)
                _navigation.postValue(OnboardingFinish)
            }
        }
    }
}
