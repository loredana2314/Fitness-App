package no.shortcut.fitnessapp.startup.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import no.shortcut.fitnessapp.common.extensions.navigate
import no.shortcut.fitnessapp.common.service.AnalyticsEvent
import no.shortcut.fitnessapp.common.service.AnalyticsService
import no.shortcut.fitnessapp.databinding.FragmentOnboardingBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class OnboardingFragment : Fragment() {

    private lateinit var binding: FragmentOnboardingBinding

    private val viewModel: OnboardingViewModel by viewModel()

    private val analyticsService: AnalyticsService by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentOnboardingBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@OnboardingFragment.viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupEventListeners()
    }

    private fun setupEventListeners() {
        viewModel.navigation.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.also { event ->
                when (event) {
                    is OnboardingFinish -> {
                        analyticsService.logEvent(AnalyticsEvent.OnboardingComplete)
                        navigate(OnboardingFragmentDirections.toMain())
                    }
                }
            }
        }
    }
}
