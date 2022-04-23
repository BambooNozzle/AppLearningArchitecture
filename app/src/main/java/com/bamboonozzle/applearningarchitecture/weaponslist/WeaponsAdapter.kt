package com.bamboonozzle.applearningarchitecture.weaponslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bamboonozzle.applearningarchitecture.data.Weapon
import com.bamboonozzle.applearningarchitecture.databinding.ItemWeaponLayoutBinding

class WeaponsAdapter(private val viewModel: WeaponsListViewModel)
    : ListAdapter<Weapon, WeaponsAdapter.WeaponViewHolder>(WeaponDiffCallBack()) {

    class WeaponViewHolder(private val binding: ItemWeaponLayoutBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(viewModel: WeaponsListViewModel, weapon: Weapon) {
            binding.weapon = weapon
            binding.viewmodel = viewModel
        }

        companion object {
            fun from(parent: ViewGroup): WeaponViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemWeaponLayoutBinding.inflate(layoutInflater, parent, false)
                return WeaponViewHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeaponViewHolder {
        return WeaponViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: WeaponViewHolder, position: Int) {
        val weapon = getItem(position)
        holder.bind(viewModel, weapon)
    }


}

class WeaponDiffCallBack: DiffUtil.ItemCallback<Weapon>() {
    override fun areItemsTheSame(oldItem: Weapon, newItem: Weapon): Boolean {
        return oldItem.uuid == newItem.uuid
    }

    override fun areContentsTheSame(oldItem: Weapon, newItem: Weapon): Boolean {
        return (oldItem.uuid == newItem.uuid) and
                (oldItem.displayName == newItem.displayName)
    }

}