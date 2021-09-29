package no.shortcut.fitnessapp.examplec

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import no.shortcut.fitnessapp.databinding.FragmentCBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class CFragment : Fragment() {
    private val viewModel: CViewModel by viewModel()

    lateinit var binding: FragmentCBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }
}
