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
import com.mindorks.framework.mvvm.databinding.DialogConfirmDeleteBinding

class DialogDeleteVideo(
    internal var context: Context, var onClick : () -> Unit
) : Dialog(context, R.style.MaterialDialogSheet) {

    val binding = DialogConfirmDeleteBinding.inflate(LayoutInflater.from(context))

    init {
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        window!!.setGravity(Gravity.CENTER)
        window!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI() {
        with(binding){
            submit.setOnClickListener {
                dismiss()
                onClick.invoke()
            }

            cancelButton.setOnClickListener {
                dismiss()
            }
        }
    }
}
