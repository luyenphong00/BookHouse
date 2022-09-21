package com.mindorks.framework.mvvm.ui.main.home.adapter

import android.annotation.SuppressLint
import com.bumptech.glide.Glide
import com.mindorks.framework.mvvm.common.BaseViewModel
import com.mindorks.framework.mvvm.common.CommonFragment
import com.mindorks.framework.mvvm.data.model.DataResponseDepartment
import com.mindorks.framework.mvvm.data.model.EquipmentModel
import com.mindorks.framework.mvvm.data.model.RoomData
import com.mindorks.framework.mvvm.databinding.FragmentHouseDetailBinding
import com.mindorks.framework.mvvm.ui.main.adapter.DeviceAdapter
import com.mindorks.framework.mvvm.ui.main.dialog.DialogCheckLink
import com.mindorks.framework.mvvm.ui.main.viewmodel.DetailModel
import com.mindorks.framework.mvvm.utils.Status
import com.mindorks.framework.mvvm.utils.Utils.currencyFormat
import org.koin.android.viewmodel.ext.android.viewModel

class HouseDetailFragment : CommonFragment<FragmentHouseDetailBinding, BaseViewModel>() {
    override val viewModel: DetailModel by viewModel()
    private var adapterAdd: DeviceAdapter? = null
    private var adapterRemove: DeviceAdapter? = null
    private var totalAll: Long = 0
    private var lstEquipmentModel = ArrayList<EquipmentModel>()
    private var lstEquỉmentRemove = ArrayList<EquipmentModel>()
    private var meetTingRoom: RoomData? = null

    override fun getViewBinding(): FragmentHouseDetailBinding =
        FragmentHouseDetailBinding.inflate(layoutInflater)

    override fun initEvent() {
        super.initEvent()
        binding.submit.setOnClickListener {
//            DialogCheckLink(requireContext(), adapter?.getTotalMoney().toString()).show()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun initData() {
        super.initData()
        with(binding) {
            arguments?.getParcelable<DataResponseDepartment>("obj")?.let {
                tvName.text = it.name
                price.text = "Giá: ${currencyFormat(it.price ?: "")} đ/giờ"
                val linkUrl = "http://192.168.0.109/DoAnDuongDucThang/public/" + it.image
                Glide.with(requireContext())
                    .load(linkUrl)
                    .into(img)
                meetTingRoom?.meetingRoomId = it.id
                meetTingRoom?.userId = "1,2,4"
//                meetTingRoom?.rentalServices = adapter.getTotalMoney()
            }
        }

        adapterAdd = DeviceAdapter(requireContext()) {
            it.select = true
            lstEquipmentModel.remove(it)
            adapterAdd?.updateData(lstEquipmentModel)
            lstEquỉmentRemove.add(it)
            adapterRemove?.updateData(lstEquỉmentRemove)
        }

        adapterRemove = DeviceAdapter(requireContext()) {
            it.select = false
            lstEquipmentModel.add(it)
            adapterAdd?.updateData(lstEquipmentModel)
            lstEquỉmentRemove.remove(it)
            adapterRemove?.updateData(lstEquỉmentRemove)
        }
        binding.rclEquidment.adapter = adapterAdd
        binding.rclEquidmentRemove.adapter = adapterRemove
        adapterRemove?.updateData(lstEquỉmentRemove)
        viewModel.fetchEquipments()

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
                                lstEquipmentModel.addAll(equipmentRes.data)
                                adapterAdd?.updateData(lstEquipmentModel)
                                viewModel.fetchService()
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
                                lstEquỉmentRemove.addAll(equipmentRes.data)
                                adapterRemove?.updateData(lstEquipmentModel)
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