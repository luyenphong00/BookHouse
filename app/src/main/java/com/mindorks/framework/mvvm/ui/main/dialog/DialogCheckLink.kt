package com.mindorks.framework.mvvm.ui.main.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.WindowManager
import com.mindorks.framework.mvvm.R
import com.mindorks.framework.mvvm.databinding.DialogChecklinkBinding

class DialogCheckLink(
    context: Context,
    var total : String
) : Dialog(context, R.style.MaterialDialogSheet) {

    private val binding = DialogChecklinkBinding.inflate(LayoutInflater.from(context))

    init {
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        window!!.setGravity(Gravity.CENTER)
        window!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI() {
        binding.total.text = "Tổng tiền : ${total}"
        binding.submit.setOnClickListener {
            dismiss()
        }

        binding.cancel.setOnClickListener {
            dismiss()
        }
    }
}
