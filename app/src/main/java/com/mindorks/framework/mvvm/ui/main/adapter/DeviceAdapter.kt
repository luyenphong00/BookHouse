package com.mindorks.framework.mvvm.ui.main.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mindorks.framework.mvvm.R
import com.mindorks.framework.mvvm.data.model.EquipmentModel
import com.mindorks.framework.mvvm.databinding.ItemDeviceBinding
import com.mindorks.framework.mvvm.utils.Utils

class DeviceAdapter(var context: Context, private var onClick : (EquipmentModel) -> Unit) : RecyclerView.Adapter<DeviceAdapter.ViewHolder>() {

    private var lstHistory = mutableListOf<EquipmentModel>()

    inner class ViewHolder(var binding: ItemDeviceBinding) :
        RecyclerView.ViewHolder(binding.root)

    @SuppressLint("NotifyDataSetChanged")
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

    @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = holder.binding
        val item = lstHistory[position]

        with(binding){
            tvName.text = item.name
            price.text = Utils.currencyFormat(item.price.toString())
            if (item.select){
                binding.add.setImageResource(R.drawable.ic_baseline_remove_circle_24)
            }else {
                binding.add.setImageResource(R.drawable.ic_baseline_add_circle_24)
            }
        }

        binding.add.setOnClickListener {
            onClick.invoke(item)
        }
    }

     fun getTotalMoney() : String{
        var total = 0L
        if (lstHistory.isNotEmpty()){
            lstHistory.forEach {
                if (it.select){
                    total += it.price?:0
                }
            }
        }
        return total.toString()
    }
}
