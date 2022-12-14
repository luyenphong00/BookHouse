package com.mindorks.framework.mvvm.ui.main.history

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mindorks.framework.mvvm.data.model.Result
import com.mindorks.framework.mvvm.databinding.ItemHouseActiveBinding
import com.mindorks.framework.mvvm.databinding.ItemHouseBinding
import com.mindorks.framework.mvvm.databinding.ItemHouseNewBinding
import com.mindorks.framework.mvvm.utils.Utils
import kotlinx.android.synthetic.main.item_layout.view.*

class HistoryAdapter(var context: Context, private var onClick : (Result) -> Unit) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    private var lstHistory = mutableListOf<Result>()

    inner class ViewHolder(var binding: ItemHouseNewBinding) :
        RecyclerView.ViewHolder(binding.root)

    fun updateData(list: ArrayList<Result>) {
        lstHistory.clear()
        if (list.isNotEmpty()){
            lstHistory.addAll(list)
        }
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = lstHistory.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            ItemHouseNewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = holder.binding
        val item = lstHistory[position]

        with(binding){
            tvName.text = item.nameRoom
            price.text = "${Utils.currencyFormat(item.totalMoney?:"")}đ"
            val linkUrl = "http://192.168.0.109/DoAnDuongDucThang/public/" + item.path
            Glide.with(context)
                .load(linkUrl)
                .into(binding.image)
        }
        binding.root.setOnClickListener {
            onClick.invoke(item)
        }
    }
}
