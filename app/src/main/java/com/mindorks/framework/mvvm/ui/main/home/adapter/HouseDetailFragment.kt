package com.mindorks.framework.mvvm.ui.main.home.adapter

import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.mindorks.framework.mvvm.common.BaseViewModel
import com.mindorks.framework.mvvm.common.CommonFragment
import com.mindorks.framework.mvvm.data.model.DataResponseDepartment
import com.mindorks.framework.mvvm.databinding.FragmentHouseDetailBinding
import com.mindorks.framework.mvvm.ui.main.adapter.DeviceAdapter
import com.mindorks.framework.mvvm.ui.main.viewmodel.DetailModel
import com.mindorks.framework.mvvm.utils.Status
import org.koin.android.viewmodel.ext.android.viewModel


class HouseDetailFragment : CommonFragment<FragmentHouseDetailBinding, BaseViewModel>() {
    override val viewModel: DetailModel by viewModel()
    private var adapter : DeviceAdapter? = null

    override fun getViewBinding(): FragmentHouseDetailBinding =
        FragmentHouseDetailBinding.inflate(layoutInflater)

    override fun initData() {
        super.initData()
        with(binding) {
            arguments?.getParcelable<DataResponseDepartment>("obj")?.let {
                tvName.text = it.name
                price.text = it.price
                val linkUrl = "http://192.168.42.6/DoAnDuongDucThang/public/" + it.image
                Glide.with(requireContext())
                    .load(linkUrl)
                    .into(img)
            }
            viewModel.fetchEquipments()
        }

        adapter = DeviceAdapter(requireContext()) {

        }

        binding.rclDevice.adapter = adapter
    }

    override fun initObserver() {
        super.initObserver()
        viewModel.lstEquipment.observe(viewLifecycleOwner) {
            it?.let {  source ->
                when(source.status){
                    Status.SUCCESS -> {
                        showLoading(false)
                        source.data?.let { equipmentRes ->
                            if (equipmentRes.data?.isNotEmpty() == true){
                                adapter?.updateData(equipmentRes.data)
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