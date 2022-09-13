package com.mindorks.framework.mvvm.ui.main.home.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mindorks.framework.mvvm.R
import com.mindorks.framework.mvvm.data.model.DataResponseDepartment
import com.mindorks.framework.mvvm.databinding.ItemHouseBinding
import com.mindorks.framework.mvvm.databinding.ItemHouseNewBinding
import com.mindorks.framework.mvvm.utils.Utils.currencyFormat

class HouseAdapter(var context: Context, var type: Int, private var onClick: (DataResponseDepartment) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var lstHouse = mutableListOf<DataResponseDepartment>()

    override fun getItemViewType(position: Int): Int {
        if (type == 0)
            return 0
        return 1
    }

    inner class ViewHolder1(var binding: ItemHouseBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            if (lstHouse.isNotEmpty()){
                val item = lstHouse[position]
                binding.tvName.text = item.name
                binding.price.text = "Giá: ${currencyFormat(item.price?:"")} đ/tháng"
                if (item.active == false){
                    binding.status.setTextColor(ContextCompat.getColor(context,R.color.color_red))
                    binding.status.text = context.getString(R.string.txt_da_thue)
                } else {
                    binding.status.setTextColor(ContextCompat.getColor(context,R.color.colorAccent))
                    binding.status.text = context.resources.getString(R.string.txt_con_trong)
                }
                val linkUrl = "http://192.168.0.108/DoAnDuongDucThang/public/" + item.image
                Glide.with(context)
                    .load(linkUrl)
                    .into(binding.image)
                binding.root.setOnClickListener {
                    onClick.invoke(item)
                }
            }
        }
    }

    fun updateData(list: ArrayList<DataResponseDepartment>) {
        lstHouse.clear()
        lstHouse.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = lstHouse.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == 0) {
            ViewHolder1(ItemHouseBinding.inflate(LayoutInflater.from(context), parent, false))
        } else {
            ViewHolder2(ItemHouseNewBinding.inflate(LayoutInflater.from(context), parent, false))
        }
    }

    private inner class ViewHolder2(var binding: ItemHouseNewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {

        }
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (type == 0) {
            (holder as ViewHolder1).bind(position)
        } else {
            (holder as ViewHolder2).bind(position)
        }
    }
}
