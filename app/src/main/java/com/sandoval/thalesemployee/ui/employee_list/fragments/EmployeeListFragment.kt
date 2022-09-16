package com.sandoval.thalesemployee.ui.employee_list.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.sandoval.thalesemployee.databinding.FragmentEmployeeListBinding
import com.sandoval.thalesemployee.ui.employee_list.viewmodel.GetEmployeeListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EmployeeListFragment : Fragment() {

    private var _fragmentEmployeeListBinding: FragmentEmployeeListBinding? = null
    private val fragmentEmployeeListBinding get() = _fragmentEmployeeListBinding!!
    private val getEmployeeListViewModel: GetEmployeeListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _fragmentEmployeeListBinding =
            FragmentEmployeeListBinding.inflate(inflater, container, false)

        fragmentEmployeeListBinding.goToDetail.setOnClickListener {
            val action =
                EmployeeListFragmentDirections.actionNavigationEmployeeListFragmentToEmployeeDetailFragment()
            findNavController().navigate(action)
        }

        initViewModels()

        return fragmentEmployeeListBinding.root
    }

    private fun initViewModels() {
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

}