package com.sandoval.thalesemployee.ui.employee_detail.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sandoval.thalesemployee.databinding.FragmentEmployeeDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EmployeeDetailFragment : Fragment() {

    private var _fragmentEmployeeDetailBinding: FragmentEmployeeDetailBinding? = null
    private val fragmentEmployeeDetailBinding get() = _fragmentEmployeeDetailBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _fragmentEmployeeDetailBinding = FragmentEmployeeDetailBinding.inflate(inflater, container, false)
        return fragmentEmployeeDetailBinding.root
    }
}
