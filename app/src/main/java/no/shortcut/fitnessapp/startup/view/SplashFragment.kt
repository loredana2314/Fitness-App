package no.shortcut.fitnessapp.startup.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import no.shortcut.fitnessapp.common.extensions.navigate
import no.shortcut.fitnessapp.databinding.FragmentSplashBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment : Fragment() {

    private val viewModel: SplashViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSplashBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        viewModel.navigationEvent.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { event ->
                when (event) {
                    SplashNavigationOnboarding -> navigate(
                        SplashFragmentDirections.toOnboarding()
                    )
                    SplashNavigationVersionLock -> navigate(
                        SplashFragmentDirections.toVersionLock()
                    )
                    SplashNavigationMain -> navigate(SplashFragmentDirections.toMain())
                }
            }
        }
        return binding.root
    }
}
