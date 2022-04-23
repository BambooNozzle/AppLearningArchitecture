package com.bamboonozzle.applearningarchitecture.weapondetail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.bamboonozzle.applearningarchitecture.AppLearningArchitectureApplication
import com.bamboonozzle.applearningarchitecture.EventObserver
import com.bamboonozzle.applearningarchitecture.R
import com.bamboonozzle.applearningarchitecture.ViewModelFactory
import com.bamboonozzle.applearningarchitecture.databinding.WeaponDetailFragmentBinding
import com.bamboonozzle.applearningarchitecture.weaponslist.WeaponsListViewModel

class WeaponDetailFragment : Fragment() {

    companion object {
        fun newInstance() = WeaponDetailFragment()
    }

    private lateinit var viewModel: WeaponDetailViewModel
    private lateinit var binding: WeaponDetailFragmentBinding

    private val args: WeaponDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = WeaponDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModelFactory = ViewModelFactory((requireContext().applicationContext as AppLearningArchitectureApplication).weaponRepository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(WeaponDetailViewModel::class.java)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel.loadWeaponDetail(args.uuid)
        viewModel.errorEvent.observe(viewLifecycleOwner, EventObserver{
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        })
    }

}