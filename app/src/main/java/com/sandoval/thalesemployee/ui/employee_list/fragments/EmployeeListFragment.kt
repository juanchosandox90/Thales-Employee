package com.sandoval.thalesemployee.ui.employee_list.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.sandoval.thalesemployee.databinding.FragmentEmployeeListBinding

class EmployeeListFragment : Fragment() {

    private var _fragmentEmployeeListBinding: FragmentEmployeeListBinding? = null
    private val fragmentEmployeeListBinding get() = _fragmentEmployeeListBinding!!

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

        return fragmentEmployeeListBinding.root
    }

}