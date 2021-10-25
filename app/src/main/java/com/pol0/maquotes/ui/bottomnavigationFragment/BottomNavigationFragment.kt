package com.pol0.maquotes.ui.bottomnavigationFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.pol0.maquotes.R
import com.pol0.maquotes.databinding.BottomNavigationFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BottomNavigationFragment : Fragment() {

    private lateinit var binding: BottomNavigationFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomNavigationFragmentBinding.inflate(layoutInflater, container, false)

        setUpBottomNavigation()

        return binding.root
    }

    private fun setUpBottomNavigation() {
        val navController = childFragmentManager.findFragmentById(R.id.nav_host_bottom_nav)
            ?.findNavController()
        binding.bottomNavigation.setupWithNavController(navController!!)
    }

}