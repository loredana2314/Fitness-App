package no.shortcut.fitnessapp.startup.view

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import no.shortcut.fitnessapp.BuildConfig
import no.shortcut.fitnessapp.databinding.FragmentVersionLockBinding

class VersionLockFragment : Fragment() {

    private lateinit var binding: FragmentVersionLockBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVersionLockBinding.inflate(inflater, container, false)
        binding.versionlockButton.setOnClickListener {
            try {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("market://details?id=${BuildConfig.APPLICATION_ID}")
                    )
                )
            } catch (e: ActivityNotFoundException) {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://play.google.com/store/apps/details?id=$${BuildConfig.APPLICATION_ID}")
                    )
                )
            }
        }
        return binding.root
    }
}
