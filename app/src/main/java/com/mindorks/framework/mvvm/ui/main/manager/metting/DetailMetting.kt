package com.mindorks.framework.mvvm.ui.main.manager.metting

import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.mindorks.framework.mvvm.common.CommonFragment
import com.mindorks.framework.mvvm.data.model.DataResponseDepartment
import com.mindorks.framework.mvvm.databinding.FragmentHouseDetailManagerBinding
import com.mindorks.framework.mvvm.ui.main.manager.ManagerViewModel
import com.mindorks.framework.mvvm.utils.Status
import com.mindorks.framework.mvvm.utils.Utils
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * Create by LuyenPhong on 9/25/2022
 * phone 0358844343
 */
class DetailMetting : CommonFragment<FragmentHouseDetailManagerBinding, ManagerViewModel>() {
    override val viewModel: ManagerViewModel by viewModel()
    private var response: DataResponseDepartment? = null

    override fun getViewBinding(): FragmentHouseDetailManagerBinding =
        FragmentHouseDetailManagerBinding.inflate(layoutInflater)

    override fun initData() {
        super.initData()
        arguments?.getParcelable<DataResponseDepartment>("obj")?.let {
            binding.tvName.setText(it.name)
            binding.price.setText(it.price)
            binding.phone.setText(it.telephone)
            binding.des.setText(it.description)
            val linkUrl = Utils.URL_IMAGE + it.image
            Glide.with(requireContext())
                .load(linkUrl)
                .into(binding.img)
            response = it
        }
    }

    override fun initEvent() {
        super.initEvent()
        with(binding) {
            edit.setOnClickListener {
                response?.let { it1 ->
                    it1.name = binding.tvName.text.toString()
                    it1.price = binding.price.text.toString()
                    it1.telephone = binding.phone.text.toString()
                    it1.description = binding.des.text.toString()
                    viewModel.updateMeetingRooms(it1)
                }
            }

            delete.setOnClickListener {
                response?.let { it1 -> viewModel.deleteMeeting(it1) }
            }
        }
    }

    override fun initObserver() {
        super.initObserver()
        viewModel.deleteResponse.observe(viewLifecycleOwner) {
            it?.let { source ->
                when(source.status){
                    Status.SUCCESS -> {
                        mainActivity()?.showLoading(false)
                        showMessage("Xóa thành công phòng họp")
                        findNavController().popBackStack()
                    }
                    Status.ERROR -> {
                        mainActivity()?.showLoading(false)
                        showMessage(source.message?:"")
                    }
                    Status.LOADING -> {
                        mainActivity()?.showLoading(true)
                    }
                }
            }
        }

        viewModel.updateLiveData.observe(viewLifecycleOwner) {
            it?.let { source ->
                when(source.status){
                    Status.SUCCESS -> {
                        mainActivity()?.showLoading(false)
                        showMessage("Cập nhật phòng họp thành công")
                    }
                    Status.ERROR -> {
                        mainActivity()?.showLoading(false)
                        showMessage(source.message?:"")
                    }
                    Status.LOADING -> {
                        mainActivity()?.showLoading(true)
                    }
                }
            }
        }
    }
}