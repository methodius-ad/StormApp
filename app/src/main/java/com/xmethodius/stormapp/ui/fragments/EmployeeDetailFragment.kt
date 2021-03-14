package com.xmethodius.stormapp.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.xmethodius.stormapp.R
import com.xmethodius.stormapp.model.Employee

class EmployeeDetailFragment : Fragment() {

    private lateinit var firstNameView: TextView
    private lateinit var lastNameView: TextView
    private lateinit var emailView: TextView
    private lateinit var genderView: TextView
    private lateinit var departmentView: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_employee_detail, container, false)
        bindEmployeeDetailView(view)
        fetchEmployeeExtra()
        return view
    }

    private fun bindEmployeeDetailView(view: View) {
        firstNameView = view.findViewById(R.id.firstName)
        lastNameView = view.findViewById(R.id.lastName)
        emailView = view.findViewById(R.id.email)
        genderView = view.findViewById(R.id.gender)
        departmentView = view.findViewById(R.id.department)
    }

    private fun fetchEmployeeExtra() {
        val intent = activity?.intent
        val firstNameExtra: String? = intent?.getStringExtra("first_name")
        val lastNameExtra: String? = intent?.getStringExtra("last_name")
        val emailExtra: String? = intent?.getStringExtra("email")
        val genderExtra: String? = intent?.getStringExtra("gender")
        val departmentExtra: String? = intent?.getStringExtra("department")

        fillEmployeeDetailView(firstNameExtra, lastNameExtra, emailExtra, genderExtra, departmentExtra)
    }

    @SuppressLint("SetTextI18n")
    private fun fillEmployeeDetailView(firstName: String?, lastName: String?, email: String?,
                                       gender: String?, department: String?) {
        firstNameView.text = "FIRST NAME: $firstName"
        lastNameView.text = "LAST NAME: $lastName"
        emailView.text = "EMAIL: $email"
        genderView.text = "GENDER: $gender"
        departmentView.text = "DEPARTMENT: $department"
    }
}