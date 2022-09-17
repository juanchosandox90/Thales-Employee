package com.sandoval.thalesemployee.ui.employee_detail.fragment

import android.view.View
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
                    showLoading()
                }
                it.isEmpty -> {
                    hideLoading()
                }
                it.dataDetail != null -> {
                    hideLoading()
                }
                it.errorMessage != null -> {
                    hideLoading()
                }
            }
        }
    }

    override fun initViews() {

    }

    private fun showLoading() {
        binding.loading.loadingContainer.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.loading.loadingContainer.visibility = View.GONE
    }

}

