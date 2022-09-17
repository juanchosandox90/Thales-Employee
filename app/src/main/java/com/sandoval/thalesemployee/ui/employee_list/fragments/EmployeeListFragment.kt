package com.sandoval.thalesemployee.ui.employee_list.fragments

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.sandoval.thalesemployee.databinding.FragmentEmployeeListBinding
import com.sandoval.thalesemployee.ui.base.BaseFragment
import com.sandoval.thalesemployee.ui.employee_list.viewmodel.GetEmployeeListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EmployeeListFragment : BaseFragment<FragmentEmployeeListBinding>(
    FragmentEmployeeListBinding::inflate
) {
    private val getEmployeeListViewModel: GetEmployeeListViewModel by viewModels()

    override fun initViewModels() {
        getEmployeeListViewModel.employeeListModel.observe(viewLifecycleOwner) {
            when {
                it.loading -> {
                    showLoading()
                }
                it.isEmpty -> {
                    hideLoading()
                }
                it.data != null -> {
                    hideLoading()
                }
                it.errorMessage != null -> {
                    hideLoading()
                }
            }
        }
    }

    override fun initViews() {
        binding.goToDetail.setOnClickListener {
            val action =
                EmployeeListFragmentDirections.actionNavigationEmployeeListFragmentToEmployeeDetailFragment()
            findNavController().navigate(action)
        }
    }

    private fun showLoading() {
        binding.loading.loadingContainer.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.loading.loadingContainer.visibility = View.GONE
    }

}