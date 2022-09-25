package com.mindorks.framework.mvvm.ui.main.manager.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.mindorks.framework.mvvm.R
import com.mindorks.framework.mvvm.data.model.UserModel
import com.mindorks.framework.mvvm.databinding.ItemManagerUserBinding

/**
 * Create by LuyenPhong on 9/25/2022
 * phone 0358844343
 */
class ManagerDeviceAdapter(var context: Context, private var onClick: (UserModel) -> Unit) :
    RecyclerView.Adapter<ManagerDeviceAdapter.ViewHolder>() {

    private var lstHistory = mutableListOf<UserModel>()

    inner class ViewHolder(var binding: ItemManagerUserBinding) :
        RecyclerView.ViewHolder(binding.root)

    private var userModel: UserModel? = null

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(list: ArrayList<UserModel>) {
        lstHistory.clear()
        if (list.isNotEmpty()) {
            list.forEach {
                it.select = true
            }
            lstHistory.addAll(list)
        }
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun update(userModel: UserModel) {
        this.userModel = userModel
    }

    override fun getItemCount(): Int = lstHistory.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            ItemManagerUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = holder.binding
        val item = lstHistory[position]

        if (item.select == true) {
            binding.remove.setImageResource(R.drawable.ic_baseline_remove_circle_24)
        } else {
            binding.remove.setImageResource(R.drawable.ic_baseline_add_circle_24)
        }

        with(binding) {
            tvName.text = item.fullname
            remove.setOnClickListener {
                if (item.id != userModel?.id) {
                    onClick.invoke(item)
                } else {
                    Toast.makeText(context, "No access", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}