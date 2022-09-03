package com.mindorks.framework.mvvm.common

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import com.mindorks.framework.mvvm.R
import com.mindorks.framework.mvvm.databinding.LayoutToolbarBinding

class ToolbarCustomer(context: Context, private var attrs: AttributeSet) : ConstraintLayout(context,attrs) {

    private var _binding: LayoutToolbarBinding? = null
    private val binding get() = _binding!!

    var onClick : (() -> Unit?)? = null

    init {
        _binding = LayoutToolbarBinding.inflate(LayoutInflater.from(context), this, true)
        initView()
    }

    private fun initView() {
        val typedArray: TypedArray = context
            .obtainStyledAttributes(attrs, R.styleable.ToolbarCustomer)

        val title = typedArray.getString(R.styleable.ToolbarCustomer_txt_toolbar)

        binding.title.text = title

        binding.root.setOnClickListener {
            onClick?.invoke() ?: kotlin.run { findNavController().popBackStack() }
        }
        typedArray.recycle()
    }

}

