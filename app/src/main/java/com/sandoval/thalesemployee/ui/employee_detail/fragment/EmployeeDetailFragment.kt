package com.sandoval.thalesemployee.ui.employee_detail.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sandoval.thalesemployee.databinding.FragmentEmployeeDetailBinding
import com.sandoval.thalesemployee.ui.employee_detail.viewmodel.GetEmployeeDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EmployeeDetailFragment : Fragment() {

    private var _fragmentEmployeeDetailBinding: FragmentEmployeeDetailBinding? = null
    private val fragmentEmployeeDetailBinding get() = _fragmentEmployeeDetailBinding!!
    private val getEmployeeDetailViewModel: GetEmployeeDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _fragmentEmployeeDetailBinding =
            FragmentEmployeeDetailBinding.inflate(inflater, container, false)

        initViewModels()

        return fragmentEmployeeDetailBinding.root
    }

    private fun initViewModels() {
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

}

