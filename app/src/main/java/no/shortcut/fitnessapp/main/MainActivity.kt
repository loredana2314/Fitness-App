package no.shortcut.fitnessapp.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import no.shortcut.fitnessapp.R
import no.shortcut.fitnessapp.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Shortcut)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        setSupportActionBar(binding.toolbar)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        val navController = Navigation.findNavController(this, R.id.menu_nav_host).apply {
            addOnDestinationChangedListener { _, destination, _ ->
                viewModel.currentFragment.postValue(destination.id)
            }
            binding.bottomNav.setupWithNavController(this)
        }
        AppBarConfiguration(
            topLevelDestinationIds = viewModel.topLevelDestinations
        ).also { appbarConfig ->
            binding.toolbar.setupWithNavController(navController, appbarConfig)
        }
    }
}
