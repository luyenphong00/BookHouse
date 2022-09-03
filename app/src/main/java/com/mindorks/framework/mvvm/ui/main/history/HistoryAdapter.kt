package com.mindorks.framework.mvvm.ui.main.history

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mindorks.framework.mvvm.databinding.ItemHouseActiveBinding
import com.mindorks.framework.mvvm.databinding.ItemHouseBinding

class HistoryAdapter(context: Context, private var onClick : (String) -> Unit) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    private var listLanguage = mutableListOf<String>()

    inner class ViewHolder(var binding: ItemHouseActiveBinding) :
        RecyclerView.ViewHolder(binding.root)

    fun updateData(list: ArrayList<String>) {
        listLanguage.clear()
        listLanguage.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = listLanguage.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            ItemHouseActiveBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = holder.binding
        val item = listLanguage[position]
        binding.root.setOnClickListener {
            onClick.invoke(item)
        }
    }
}
