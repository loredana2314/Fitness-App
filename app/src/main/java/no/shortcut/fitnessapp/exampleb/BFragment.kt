package no.shortcut.fitnessapp.exampleb

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import no.shortcut.fitnessapp.R
import no.shortcut.fitnessapp.common.extensions.navigate
import no.shortcut.fitnessapp.databinding.FragmentBBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class BFragment : Fragment() {

    val viewModel: BViewModel by viewModel()

    private lateinit var binding: FragmentBBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        menu.add("FragmentB option").apply {
            setIcon(R.drawable.ic_baseline_message_24)
            setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM)
            setOnMenuItemClickListener {
                Snackbar.make(
                    requireView(),
                    "FragmentB has an options menu created with setHasOptionsMenu(true) and 'override onCreateOptionsMenu'. A global menu could be created in main activity",
                    Snackbar.LENGTH_LONG
                ).show()
                true
            }
        }

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupEventListeners()
    }

    private fun setupEventListeners() {
        viewModel.navigation.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.also { event ->
                when (event) {
                    is BSubmit -> navigate(BFragmentDirections.toMain())
                }
            }
        }
    }
}
