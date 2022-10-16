package com.mindorks.framework.mvvm.ui.main.history

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.util.Log
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.mindorks.framework.mvvm.common.BaseViewModel
import com.mindorks.framework.mvvm.common.CommonFragment
import com.mindorks.framework.mvvm.data.model.*
import com.mindorks.framework.mvvm.databinding.FragmentDetailEditBinding
import com.mindorks.framework.mvvm.ui.main.adapter.DeviceAdapter
import com.mindorks.framework.mvvm.ui.main.adapter.UserAdapter
import com.mindorks.framework.mvvm.ui.main.dialog.DialogCheckLink
import com.mindorks.framework.mvvm.ui.main.viewmodel.DetailModel
import com.mindorks.framework.mvvm.ui.main.viewmodel.MainViewModel
import com.mindorks.framework.mvvm.utils.Status
import com.mindorks.framework.mvvm.utils.Utils
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*

class DetailHistoryFragment : CommonFragment<FragmentDetailEditBinding, BaseViewModel>() {

    override val viewModel: DetailModel by viewModel()
    private var adapterEquiment: DeviceAdapter? = null
    private var adapterService: DeviceAdapter? = null
    private var adapterUser: UserAdapter? = null
    private var response: Result? = null
    private val sharedViewModel by sharedViewModel<MainViewModel>()
    var picker: TimePickerDialog? = null
    var calendar = Calendar.getInstance()
    var lastSelectedYear = calendar.get(Calendar.YEAR)
    var lastSelectedMonth = calendar.get(Calendar.MONTH)
    var lastSelectedDayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
    var selectTimeStart = false
    var selectTimeEnd = false
    var selectCalendar = false

    override fun getViewBinding(): FragmentDetailEditBinding =
        FragmentDetailEditBinding.inflate(layoutInflater)

    override fun initEvent() {
        super.initEvent()
        binding.submit.setOnClickListener {
            if (selectTimeStart && selectTimeEnd && selectCalendar) {
                val meetTingRoom = RoomBock(
                    response?.meetId?.toInt(),
                    response?.userId,
                    getDataRentalServices(),
                    getDataRentalEquipment(),
                    binding.tvTime.text.toString(),
                    binding.tvEnd.text.toString(),
                    binding.tvCalendar.text.toString(),
                    response?.id
                )
                val totalMoney = (adapterService?.getTotalMoney()?.toLong()?.let { it1 ->
                    adapterEquiment?.getTotalMoney()?.toLong()
                        ?.plus(it1)?.plus(response?.price_meeting_room?.toLong() ?: 0)
                }).toString()

                DialogCheckLink(requireContext(), totalMoney) {
                    viewModel.rent(meetTingRoom)
                }.show()
            } else {
                showMessage("Chọn đủ thông tin")
            }
        }

        binding.abc.setOnClickListener {
            buttonSelectDate()
        }

        binding.tvTime.setOnClickListener {
            val cldr: Calendar = Calendar.getInstance()
            val hour: Int = cldr.get(Calendar.HOUR_OF_DAY)
            var minutes: Int = cldr.get(Calendar.MINUTE)
            picker = TimePickerDialog(
                requireContext(),
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
                requireContext(),
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
            requireContext(),
            dateSetListener, lastSelectedYear, lastSelectedMonth, lastSelectedDayOfMonth
        )
        datePickerDialog.show()
    }

    fun getDataRentalServices(): ArrayList<RentalServicesRequest> {
        val arrRental = ArrayList<RentalServicesRequest>()
        adapterService?.getDataSelect()?.forEach {
            arrRental.clear()
            arrRental.add(RentalServicesRequest(it.id, it.count))
        }
        return arrRental
    }

    fun getDataRentalEquipment(): ArrayList<RentalEquipmentsRequest> {
        val arrRental = ArrayList<RentalEquipmentsRequest>()
        arrRental.clear()
        adapterEquiment?.getDataSelect()?.forEach {
            arrRental.add(RentalEquipmentsRequest(it.id, it.count))
        }
        return arrRental
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun initData() {
        super.initData()
        with(binding) {

            sharedViewModel.result?.let {
                tvName.text = it.nameRoom
                price.text = "Giá: ${Utils.currencyFormat(it.price_meeting_room ?: "")} đ/giờ"
                val linkUrl = Utils.URL_IMAGE + it.path
                Glide.with(requireContext())
                    .load(linkUrl)
                    .into(img)

                tvTime.text =  it.hour_start
                tvEnd.text = it.hour_end
                tvEnd.text = it.hour_end
                tvCalendar.text = it.date
                response = it
                 selectTimeStart = true
                 selectTimeEnd = true
                 selectCalendar = true
            }

        }

        adapterEquiment = DeviceAdapter(requireContext()) {
        }

        adapterService = DeviceAdapter(requireContext()) {
        }

        adapterUser = UserAdapter(requireContext()) {

        }

        binding.recycleViewUser.adapter = adapterUser

        binding.rclEquidment.adapter = adapterEquiment
        binding.rclEquidmentRemove.adapter = adapterService
        viewModel.fetchEquipments()
//        viewModel.getListUser()
        viewModel.fetchService()
    }

    override fun initObserver() {
        super.initObserver()
        viewModel.lstEquipment.observe(viewLifecycleOwner) {
            it?.let { source ->
                when (source.status) {
                    Status.SUCCESS -> {
                        showLoading(false)
                        source.data?.let { equipmentRes ->
                            if (equipmentRes.data?.isNotEmpty() == true) {
                                equipmentRes.data.forEach { equiment ->
                                    if (sharedViewModel.result?.rental_equipments?.isNotEmpty() == true) {
                                        sharedViewModel.result?.rental_equipments?.forEach { rentel ->
                                            if (equiment.id == rentel.equipment_id) {
                                                equiment.count = rentel.quantity?.toInt() ?: 0
                                                Log.d(
                                                    "AAAAAAAAAAAAA",
                                                    "${equiment.id}-- ${rentel.equipment_id}"
                                                )
                                            }
                                        }
                                    }

                                }
                                adapterEquiment?.updateData(equipmentRes.data)
                            }
                        }
                    }
                    Status.ERROR -> {
                        showLoading(false)
                    }
                    Status.LOADING -> {
                        showLoading(true)
                    }
                }
            }
        }

        viewModel.lstService.observe(viewLifecycleOwner) {
            it?.let { source ->
                when (source.status) {
                    Status.SUCCESS -> {
                        showLoading(false)
                        source.data?.let { equipmentRes ->
                            if (equipmentRes.data?.isNotEmpty() == true) {
                                equipmentRes.data.forEach { equiment ->
                                    if (sharedViewModel.result?.rentalServices?.isNotEmpty() == true) {
                                        sharedViewModel.result?.rentalServices?.forEach { rentel ->
                                            if (equiment.id == rentel.service_id) {
                                                equiment.count = rentel.quantity?.toInt() ?: 0
                                                Log.d(
                                                    "AAAAAAAAAAAAA",
                                                    "${equiment.id}-- ${rentel.equipment_id}"
                                                )
                                            }
                                        }
                                    }

                                }
                                adapterService?.updateData(equipmentRes.data)
                            }
                        }

                    }
                    Status.ERROR -> {
                        showLoading(false)
                    }
                    Status.LOADING -> {
                        showLoading(true)
                    }
                }
            }
        }
        viewModel.rentLiveData.observe(viewLifecycleOwner) {
            it?.let { source ->
                when (source.status) {
                    Status.SUCCESS -> {
                        showLoading(false)
                        source.data?.let { res ->
                            findNavController().popBackStack()
                            showMessage(res.message ?: "")
                        } ?: kotlin.run {
                            showMessage(source.message ?: "")
                        }

                    }
                    Status.ERROR -> {
                        showLoading(false)
                        showMessage(source.message ?: "")
                    }
                    Status.LOADING -> {
                        showLoading(true)
                    }
                }
            }
        }

    }

    fun filterData(rental_equipments: ArrayList<RentalServices>, data: ArrayList<RentalServices>) {

    }
}