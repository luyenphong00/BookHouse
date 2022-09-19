package com.mindorks.framework.mvvm.ui.main.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mindorks.framework.mvvm.data.model.EquipmentModel
import com.mindorks.framework.mvvm.data.model.Result
import com.mindorks.framework.mvvm.databinding.ItemDeviceBinding
import com.mindorks.framework.mvvm.databinding.ItemHouseActiveBinding
import com.mindorks.framework.mvvm.databinding.ItemHouseBinding
import com.mindorks.framework.mvvm.databinding.ItemHouseNewBinding
import com.mindorks.framework.mvvm.utils.Utils
import kotlinx.android.synthetic.main.item_layout.view.*

class DeviceAdapter(var context: Context, private var onClick : (EquipmentModel) -> Unit) : RecyclerView.Adapter<DeviceAdapter.ViewHolder>() {

    private var lstHistory = mutableListOf<EquipmentModel>()

    inner class ViewHolder(var binding: ItemDeviceBinding) :
        RecyclerView.ViewHolder(binding.root)

    fun updateData(list: ArrayList<EquipmentModel>) {
        lstHistory.clear()
        if (list.isNotEmpty()){
            lstHistory.addAll(list)
        }
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = lstHistory.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            ItemDeviceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = holder.binding
        val item = lstHistory[position]

        with(binding){
            tvName.text = item.name
            price.text = Utils.currencyFormat(item.price.toString())
        }
        binding.root.setOnClickListener {
            onClick.invoke(item)
        }
    }
}
