package com.sandoval.thalesemployee.ui.employee_list.fragments

import android.util.Log
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
                    Log.d("Loading", "Loading...")
                }
                it.isEmpty -> {
                    Log.d("Data", "Empty...")
                }
                it.data != null -> {
                    Log.d("Data", it.data.toString())
                }
                it.errorMessage != null -> {
                    Log.e("Error", it.errorMessage.toString())
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

}