package com.bamboonozzle.applearningarchitecture.weaponslist

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.bamboonozzle.applearningarchitecture.AppLearningArchitectureApplication
import com.bamboonozzle.applearningarchitecture.EventObserver
import com.bamboonozzle.applearningarchitecture.R
import com.bamboonozzle.applearningarchitecture.ViewModelFactory
import com.bamboonozzle.applearningarchitecture.databinding.WeaponsListFragmentBinding

class WeaponsListFragment : Fragment() {

    lateinit var adapter: WeaponsAdapter

    companion object {
        fun newInstance() = WeaponsListFragment()
    }

    private lateinit var binding: WeaponsListFragmentBinding
    private lateinit var viewModel: WeaponsListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = WeaponsListFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModelFactory = ViewModelFactory((requireContext().applicationContext as AppLearningArchitectureApplication).weaponRepository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(WeaponsListViewModel::class.java)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        adapter = WeaponsAdapter(viewModel)
        binding.rvWeapons.adapter = adapter
        viewModel.loadWeapons()
        setupObserveEvent()
    }

    private fun setupObserveEvent() {
        viewModel.weaponDetailEvent.observe(viewLifecycleOwner, EventObserver{
            openWeaponDetail(it)
        })
        viewModel.errorLoadToast.observe(viewLifecycleOwner, EventObserver{
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        })
    }

    private fun openWeaponDetail(uuid: String) {
        val action = WeaponsListFragmentDirections.actionWeaponsListFragmentToWeaponDetailFragment(uuid)
        findNavController().navigate(action)
    }

}