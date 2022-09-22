package com.mindorks.framework.mvvm.ui.main.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mindorks.framework.mvvm.R
import com.mindorks.framework.mvvm.data.model.DetailUserModel
import com.mindorks.framework.mvvm.data.model.EquipmentModel
import com.mindorks.framework.mvvm.data.model.UserModel
import com.mindorks.framework.mvvm.databinding.ItemDeviceBinding
import com.mindorks.framework.mvvm.databinding.ItemUserBinding
import com.mindorks.framework.mvvm.utils.Utils

class UserAdapter(var context: Context, private var onClick : (UserModel) -> Unit) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private var lstHistory = mutableListOf<UserModel>()

    inner class ViewHolder(var binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root)

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(list: ArrayList<UserModel>) {
        lstHistory.clear()
        if (list.isNotEmpty()){
            lstHistory.addAll(list)
        }
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = lstHistory.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = holder.binding
        val item = lstHistory[position]

        binding.tvName.text = item.fullname
        if (item.select == true){
            binding.add.setImageResource(R.drawable.ic_baseline_remove_circle_24)
        }else {
            binding.add.setImageResource(R.drawable.ic_baseline_add_circle_24)
        }

        binding.add.setOnClickListener {
            item.select = item.select != true
            notifyItemChanged(position)
            onClick.invoke(item)
        }
    }

}
