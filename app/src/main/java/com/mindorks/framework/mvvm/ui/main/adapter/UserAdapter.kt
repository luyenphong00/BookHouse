package com.mindorks.framework.mvvm.ui.main.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.mindorks.framework.mvvm.R
import com.mindorks.framework.mvvm.data.model.DetailUserModel
import com.mindorks.framework.mvvm.data.model.UserModel
import com.mindorks.framework.mvvm.databinding.ItemManagerUserBinding
import com.mindorks.framework.mvvm.databinding.ItemUserBinding

class UserAdapter(var context: Context, private var onClick : (UserModel) -> Unit) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private var lstHistory = mutableListOf<UserModel>()

    inner class ViewHolder(var binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root)

    private var userModel: UserModel? = null

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(list: ArrayList<UserModel>) {
        lstHistory.clear()
        if (list.isNotEmpty()){
            lstHistory.addAll(list)
        }
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun update(userModel: UserModel){
        this.userModel = userModel
        lstHistory.forEach {
            if (it.id == userModel.id){
                it.select = true
            }
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

        if (item.select == true) {
            binding.add.setImageResource(R.drawable.ic_baseline_remove_circle_24)
        }else {
            binding.add.setImageResource(R.drawable.ic_baseline_add_circle_24)
        }

        with(binding){
            tvName.text = item.fullname
            add.setOnClickListener {
                if (item.id != userModel?.id){
                    item.select = item.select != true
                    notifyItemChanged(position)
                }
            }
        }
    }

    fun getLstUser() : String{
        var userId = ""
        val lstFilter = lstHistory.filter {
            it.select == true
        }
        if (lstFilter.isNotEmpty()){
            if (lstFilter.size == 1){
                userId = (lstFilter[0].id?:0).toString()
            }else {
                lstFilter.forEach {
                    if (userId == ""){
                        userId = "${it.id}"
                    }else {
                        userId += ",${it.id}"
                    }
                }
            }
        }

        return userId
    }

}

class AccountAdapter(var context: Context, private var onClick : (UserModel) -> Unit) : RecyclerView.Adapter<AccountAdapter.ViewHolder>() {

    private var lstHistory = mutableListOf<UserModel>()

    inner class ViewHolder(var binding: ItemManagerUserBinding) :
        RecyclerView.ViewHolder(binding.root)

    private var userModel: UserModel? = null

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(list: ArrayList<UserModel>) {
        lstHistory.clear()
        if (list.isNotEmpty()){
            list.forEach {
                it.select = true
            }
            lstHistory.addAll(list)
        }
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun update(userModel: UserModel){
        this.userModel = userModel
    }

    override fun getItemCount(): Int = lstHistory.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =ItemManagerUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = holder.binding
        val item = lstHistory[position]

        if (item.select == true) {
            binding.remove.setImageResource(R.drawable.ic_baseline_remove_circle_24)
        }else {
            binding.remove.setImageResource(R.drawable.ic_baseline_add_circle_24)
        }

        with(binding){
            tvName.text = item.fullname
            remove.setOnClickListener {
                if (item.id != userModel?.id){
                    onClick.invoke(item)
                }else {
                    Toast.makeText(context,"No access", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}

