package com.sandoval.thalesemployee.ui.employee_detail.fragment

import android.util.Log
import androidx.fragment.app.viewModels
import com.sandoval.thalesemployee.databinding.FragmentEmployeeDetailBinding
import com.sandoval.thalesemployee.ui.base.BaseFragment
import com.sandoval.thalesemployee.ui.employee_detail.viewmodel.GetEmployeeDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EmployeeDetailFragment : BaseFragment<FragmentEmployeeDetailBinding>(
    FragmentEmployeeDetailBinding::inflate
) {
    private val getEmployeeDetailViewModel: GetEmployeeDetailViewModel by viewModels()

    override fun initViewModels() {
        getEmployeeDetailViewModel.getDataDetail(1)
        getEmployeeDetailViewModel.getEmployeeDetailViewModel.observe(viewLifecycleOwner) {
            when {
                it.loading -> {
                    Log.d("Loading", "Loading...")
                }
                it.isEmpty -> {
                    Log.d("Data Detail", "Empty...")
                }
                it.dataDetail != null -> {
                    Log.d("Data Detail", it.dataDetail.toString())
                }
                it.errorMessage != null -> {
                    Log.e("Error", it.errorMessage.toString())
                }
            }
        }
    }

    override fun initViews() {

    }

}

