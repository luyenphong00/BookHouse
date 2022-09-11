package com.mindorks.framework.mvvm.ui.main.history

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mindorks.framework.mvvm.data.model.Result
import com.mindorks.framework.mvvm.databinding.ItemHouseActiveBinding
import com.mindorks.framework.mvvm.databinding.ItemHouseBinding
import com.mindorks.framework.mvvm.databinding.ItemHouseNewBinding

class HistoryAdapter(context: Context, private var onClick : (Result) -> Unit) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    private var listLanguage = mutableListOf<Result>()

    inner class ViewHolder(var binding: ItemHouseNewBinding) :
        RecyclerView.ViewHolder(binding.root)

    fun updateData(list: ArrayList<Result>) {
        listLanguage.clear()
        listLanguage.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = listLanguage.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            ItemHouseNewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
