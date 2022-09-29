package com.mindorks.framework.mvvm.ui.main.home.adapter

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.mindorks.framework.mvvm.common.BaseViewModel
import com.mindorks.framework.mvvm.common.CommonFragment
import com.mindorks.framework.mvvm.data.model.DataResponseDepartment
import com.mindorks.framework.mvvm.data.model.RentalEquipmentsRequest
import com.mindorks.framework.mvvm.data.model.RentalServicesRequest
import com.mindorks.framework.mvvm.data.model.RoomBock
import com.mindorks.framework.mvvm.databinding.FragmentDetailBinding
import com.mindorks.framework.mvvm.ui.main.adapter.DeviceAdapter
import com.mindorks.framework.mvvm.ui.main.adapter.UserAdapter
import com.mindorks.framework.mvvm.ui.main.dialog.DialogCheckLink
import com.mindorks.framework.mvvm.ui.main.viewmodel.DetailModel
import com.mindorks.framework.mvvm.ui.main.viewmodel.MainViewModel
import com.mindorks.framework.mvvm.utils.Status
import com.mindorks.framework.mvvm.utils.Utils
import com.mindorks.framework.mvvm.utils.Utils.currencyFormat
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*

class HouseDetailFragment : CommonFragment<FragmentDetailBinding, BaseViewModel>() {
    override val viewModel: DetailModel by viewModel()
    private var adapterEquiment: DeviceAdapter? = null
    private var adapterService: DeviceAdapter? = null
    private var adapterUser: UserAdapter? = null
    private var response: DataResponseDepartment? = null
    private val sharedViewModel by sharedViewModel<MainViewModel>()
    var picker: TimePickerDialog? = null
    private var count = 0

    override fun getViewBinding(): FragmentDetailBinding =
        FragmentDetailBinding.inflate(layoutInflater)

    override fun initEvent() {
        super.initEvent()
        binding.submit.setOnClickListener {
            if (count == 0) {
                showMessage("Vui lòng chọn số giờ thuê")
                return@setOnClickListener
            }
            val meetTingRoom = RoomBock(
                response?.id,
                adapterUser?.getLstUser().toString(),
                getDataRentalServices(),
                getDataRentalEquipment()
            )
            val totalMoney = (adapterService?.getTotalMoney()?.toLong()?.let { it1 ->
                adapterEquiment?.getTotalMoney()?.toLong()
                    ?.plus(it1)?.plus(response?.price?.toLong() ?: 0)
            }).toString()

            DialogCheckLink(requireContext(), totalMoney) {
                viewModel.rent(meetTingRoom)
            }.show()
        }

        binding.abc.setOnClickListener {
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
                }, hour, minutes, true
            )
            picker?.show()
        }

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
        adapterEquiment?.getDataSelect()?.forEach {
            arrRental.clear()
            arrRental.add(RentalEquipmentsRequest(it.id, it.count))
        }
        return arrRental
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun initData() {
        super.initData()
        with(binding) {

            arguments?.getParcelable<DataResponseDepartment>("obj")?.let {
                tvName.text = it.name
                price.text = "Giá: ${currencyFormat(it.price ?: "")} đ/giờ"
                val linkUrl = Utils.URL_IMAGE + it.image
                Glide.with(requireContext())
                    .load(linkUrl)
                    .into(img)
                response = it
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
        viewModel.getListUser()
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
                        showMessage("Đặt phòng thành công")
                        findNavController().popBackStack()
                    }
                    Status.ERROR -> {
                        showLoading(false)
                        showMessage("Đặt phòng thất bại")
                    }
                    Status.LOADING -> {
                        showLoading(true)
                    }
                }
            }
        }

        viewModel.lstUserLiveData.observe(viewLifecycleOwner) {
            it?.let { source ->
                when (source.status) {
                    Status.SUCCESS -> {
                        showLoading(false)
                        source.data?.let { response ->
                            if (response.lstUser?.isNotEmpty() == true) {
                                adapterUser?.updateData(response.lstUser)
                                sharedViewModel.userModel?.let { it1 -> adapterUser?.update(it1) }
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
    }
}