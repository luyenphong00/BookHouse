package com.mindorks.framework.mvvm.ui.main.home.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mindorks.framework.mvvm.databinding.ItemHouseBinding
import com.mindorks.framework.mvvm.databinding.ItemHouseNewBinding

class HouseAdapter(var context: Context, var type: Int, private var onClick: (String) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var listLanguage = mutableListOf<String>()

    override fun getItemViewType(position: Int): Int {
        if (type == 0)
            return 0
        return 1
    }

    inner class ViewHolder1(var binding: ItemHouseBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {

        }
    }

    fun updateData(list: ArrayList<String>) {
        listLanguage.clear()
        listLanguage.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = listLanguage.size

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
