package com.mindorks.framework.mvvm.ui.main.dialog

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Parcelable
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import com.mindorks.framework.mvvm.R
import com.mindorks.framework.mvvm.data.model.SearchRequest
import com.mindorks.framework.mvvm.databinding.DialogSearchBinding
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class ModelCallback(var timeStart: String, var timeEnd: String, var date: String) : Parcelable
class DialogSearch(
    context: Context,
    var submit: (SearchRequest) -> Unit,
    var callback: (ModelCallback) -> Unit

) : Dialog(context, R.style.MaterialDialogSheet) {

    private val binding = DialogSearchBinding.inflate(LayoutInflater.from(context))
    var calendar = Calendar.getInstance()
    var lastSelectedYear = calendar.get(Calendar.YEAR)
    var lastSelectedMonth = calendar.get(Calendar.MONTH)
    var lastSelectedDayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
    var selectTimeStart = false
    var selectTimeEnd = false
    var selectCalendar = false
    var picker: TimePickerDialog? = null

    init {
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        window?.setGravity(Gravity.CENTER)
        window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI() {
        binding.search.setOnClickListener {
            if (selectTimeStart && selectTimeEnd && selectCalendar) {
                submit.invoke(
                    SearchRequest(
                        binding.tvTime.text.toString(),
                        binding.tvEnd.text.toString(),
                        binding.tvCalendar.text.toString(),
                    )
                )
                callback.invoke(
                    ModelCallback(
                        binding.tvTime.text.toString(),
                        binding.tvEnd.text.toString(),
                        binding.tvCalendar.text.toString()
                    )
                )
                dismiss()
            } else {
                showMessage("Chọn đủ thông tin")
            }
        }

        binding.tvCalendar.setOnClickListener {
            buttonSelectDate()
        }

        binding.tvTime.setOnClickListener {
            val cldr: Calendar = Calendar.getInstance()
            val hour: Int = cldr.get(Calendar.HOUR_OF_DAY)
            var minutes: Int = cldr.get(Calendar.MINUTE)
            picker = TimePickerDialog(
                context,
                { tp, sHour, sMinute ->
                    var replaceMinute: String
                    var replacesHour: String
                    replaceMinute = sMinute.toString()
                    if (replaceMinute.toInt() < 10) {
                        replaceMinute = "0${replaceMinute}"
                    }
                    replacesHour = sHour.toString()
                    if (replacesHour.toInt() < 10) {
                        replacesHour = "0${replacesHour}"
                    }
                    binding.tvTime.text = "$replacesHour:$replaceMinute"
                    selectTimeStart = true
                }, hour, minutes, true
            )
            picker?.show()
        }
        binding.tvEnd.setOnClickListener {
            val cldr: Calendar = Calendar.getInstance()
            val hour: Int = cldr.get(Calendar.HOUR_OF_DAY)
            var minutes: Int = cldr.get(Calendar.MINUTE)
            picker = TimePickerDialog(
                context,
                { tp, sHour, sMinute ->
                    var replaceMinute: String
                    var replacesHour: String
                    replaceMinute = sMinute.toString()
                    if (replaceMinute.toInt() < 10) {
                        replaceMinute = "0${replaceMinute}"
                    }
                    replacesHour = sHour.toString()
                    if (replacesHour.toInt() < 10) {
                        replacesHour = "0${replacesHour}"
                    }
                    binding.tvEnd.text = "$replacesHour:$replaceMinute"
                    selectTimeEnd = true
                }, hour, minutes, true
            )
            picker?.show()
        }
    }

    fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    private fun buttonSelectDate() {
        // Date Select Listener.
        val dateSetListener =
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                var replateDays = dayOfMonth.toString()
                if (replateDays.toInt() < 10) {
                    replateDays = "0${replateDays}"
                }
                var replaceMonth = ""
                replaceMonth = if ((monthOfYear + 1).toString().length == 1) {
                    "0${monthOfYear + 1}"
                } else {
                    (monthOfYear + 1).toString()
                }
                binding.tvCalendar.text = "${year}-${replaceMonth}-${replateDays}"
                lastSelectedYear = year
                lastSelectedMonth = monthOfYear
                lastSelectedDayOfMonth = dayOfMonth
                selectCalendar = true
            }
        var datePickerDialog: DatePickerDialog? = null
        datePickerDialog = DatePickerDialog(
            context,
            dateSetListener, lastSelectedYear, lastSelectedMonth, lastSelectedDayOfMonth
        )
        datePickerDialog.show()
    }
}



