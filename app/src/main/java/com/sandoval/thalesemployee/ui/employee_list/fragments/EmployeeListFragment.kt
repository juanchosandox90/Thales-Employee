package com.sandoval.thalesemployee.ui.employee_list.fragments

import android.content.Context
import android.os.Parcelable
import android.text.InputType
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.sandoval.thalesemployee.R
import com.sandoval.thalesemployee.databinding.FragmentEmployeeListBinding
import com.sandoval.thalesemployee.ui.base.BaseFragment
import com.sandoval.thalesemployee.ui.employee_detail.models.DDataDetailPresentation
import com.sandoval.thalesemployee.ui.employee_detail.viewmodel.GetEmployeeDetailViewModel
import com.sandoval.thalesemployee.ui.employee_list.adapter.EmployeeListAdapter
import com.sandoval.thalesemployee.ui.employee_list.viewmodel.GetEmployeeListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EmployeeListFragment : BaseFragment<FragmentEmployeeListBinding>(
    FragmentEmployeeListBinding::inflate
), SearchView.OnQueryTextListener {

    private var recyclerViewState: Parcelable? = null
    private val getEmployeeListViewModel: GetEmployeeListViewModel by viewModels()
    private val getEmployeeDetailViewModel: GetEmployeeDetailViewModel by viewModels()

    private var employeeId: Int? = null

    private val adapter: EmployeeListAdapter by lazy {
        EmployeeListAdapter()
    }

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
                    binding.employeesListRecycler.visibility = View.VISIBLE
                    binding.generalError.generalIssues.visibility = View.GONE
                    hideLoading()
                }
                it.errorMessage != null -> {
                    setupGeneralErrorView()
                    hideLoading()
                }
            }
        }

        getEmployeeDetailViewModel.getEmployeeDetailViewModel.observe(viewLifecycleOwner) {
            when {
                it.loading -> {
                    showLoading()
                }
                it.isEmpty -> {
                    hideLoading()
                }
                it.dataDetail != null -> {
                    binding.generalError.generalIssues.visibility = View.GONE
                    setupDetailEmployeeView(it.dataDetail)
                    hideLoading()
                }
                it.errorMessage != null -> {
                    hideLoading()
                }
            }
        }

        getEmployeeListViewModel.listEmployees.observe(viewLifecycleOwner) { employeesList ->
            recyclerViewState =
                binding.employeesListRecycler.layoutManager?.onSaveInstanceState()
            adapter.submitList(null)
            adapter.submitDataList(employeesList)

        }
    }

    override fun initViews() {
        setUpRecyclerView()
        adapter.apply {
            registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
                override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                    recyclerViewState?.let {
                        binding.employeesListRecycler.layoutManager?.onRestoreInstanceState(it)
                    }
                }
            })
        }

        setHasOptionsMenu(true)
    }

    private fun setUpRecyclerView() {
        binding.employeesListRecycler.adapter = adapter
    }

    private fun setupDetailEmployeeView(dataDetail: DDataDetailPresentation) {
        val salaryInt = dataDetail.employee_salary
        val salaryAnnual = salaryInt?.times(12)
        binding.generalError.generalIssues.visibility = View.GONE
        binding.employeesListRecycler.visibility = View.GONE
        binding.employeeDetailContainer.visibility = View.VISIBLE
        binding.employeeDetailName.setText("Name: ${dataDetail.employee_name}")
        binding.employeeDetailSalary.setText("Annual Salary: ${salaryAnnual.toString()}")
        binding.employeeDetailAge.setText("Employee Age: ${dataDetail.employee_age.toString()}")
    }

    private fun initSearchViewOption(menu: Menu) {
        val search = menu.findItem(R.id.searchConversationMenu)
        val searchView = search.actionView as? SearchView
        searchView?.isSubmitButtonEnabled = false
        searchView?.isIconified = true
        searchView?.setIconifiedByDefault(true)
        searchView?.setOnQueryTextListener(this)
        searchView?.inputType = InputType.TYPE_CLASS_NUMBER

        val searchViewsCloseBtn =
            searchView!!.findViewById<View>(com.google.android.material.R.id.search_close_btn)
        searchViewsCloseBtn.setOnClickListener {
            if (searchView.query.isNotEmpty()) {
                binding.employeeDetailContainer.visibility = View.GONE
                searchView.setQuery("", false)
                searchView.onActionViewCollapsed()
                search.collapseActionView()
                getEmployeeListViewModel.getData()
            }
        }

        search.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {

            override fun onMenuItemActionExpand(p0: MenuItem): Boolean {
                binding.employeesListRecycler.visibility = View.GONE
                return true
            }

            override fun onMenuItemActionCollapse(p0: MenuItem): Boolean {
                binding.employeeDetailContainer.visibility = View.GONE
                getEmployeeListViewModel.getData()
                hideKeyBoard()
                return true
            }

        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.employee_list_menu, menu)
        initSearchViewOption(menu)
    }

    private fun hideKeyBoard() {
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (imm.isActive) {
            imm.hideSoftInputFromWindow(binding.root.windowToken, 0)
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null) {
            binding.employeesListRecycler.visibility = View.GONE
            employeeId = query.toInt()
            getEmployeeDetailViewModel.getDataDetail(employeeId!!)
            hideKeyBoard()
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

    private fun setupGeneralErrorView() {
        binding.employeesListRecycler.visibility = View.GONE
        binding.generalError.generalIssues.visibility = View.VISIBLE
        binding.generalError.reload.setOnClickListener {
            getEmployeeListViewModel.getData()
            binding.generalError.generalIssues.visibility = View.GONE
            showLoading()
        }
    }

    override fun showNoInternetConnection() {
        binding.employeesListRecycler.visibility = View.GONE
        binding.generalError.generalIssues.visibility = View.VISIBLE
        binding.generalError.reload.setOnClickListener {
            getEmployeeListViewModel.getData()
            binding.generalError.generalIssues.visibility = View.GONE
            showLoading()
        }
    }

    private fun showLoading() {
        binding.loading.loadingContainer.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.loading.loadingContainer.visibility = View.GONE
    }

}