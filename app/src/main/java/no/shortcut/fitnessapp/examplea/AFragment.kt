package no.shortcut.fitnessapp.examplea

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import no.shortcut.fitnessapp.common.extensions.navigate
import no.shortcut.fitnessapp.databinding.FragmentABinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class AFragment : Fragment() {

    private val viewModel: AViewModel by viewModel()

    lateinit var binding: FragmentABinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentABinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
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
                    is ASubmit -> navigate(AFragmentDirections.toB())
                }
            }
        }
    }
}
