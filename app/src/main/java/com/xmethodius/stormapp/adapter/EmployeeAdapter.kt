package com.xmethodius.stormapp.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.xmethodius.stormapp.ui.activities.DetailActivity
import com.xmethodius.stormapp.R
import com.xmethodius.stormapp.model.Employee

class EmployeeAdapter(list : ArrayList<Employee>, context: Context) :
        RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder>() {

    private val employeeList = list
    private val employeeAdapterContext = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        return EmployeeViewHolder(LayoutInflater.from(employeeAdapterContext).inflate(
                R.layout.item_list,
                parent,
                false
            )
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        val item = employeeList[position]
        val firstName = item.firstName
        val lastName = item.lastName
        val email = item.email
        val gender = item.gender
        val department = item.department
        val itemView = holder.name

        fillItem(itemView, firstName, lastName)
        choosePerson(itemView, firstName, lastName, email, gender, department)
    }

    override fun getItemCount(): Int {
        return employeeList.size
    }

    class EmployeeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
       var name : TextView = itemView.findViewById(R.id.item_title)
    }

    private fun fillItem(name: TextView, firstName: String, lastName: String) {
        val fullName = "$firstName $lastName"
        name.text = fullName
    }

    private fun choosePerson(
        fullName: TextView,
        firstName: String,
        lastName: String,
        email: String,
        gender: String,
        department: String
    ) {
        fullName.setOnClickListener {
            val intent = Intent(employeeAdapterContext, DetailActivity::class.java)
            intent.putExtra("source", "employee")
            intent.putExtra("first_name", firstName)
            intent.putExtra("last_name", lastName)
            intent.putExtra("email", email)
            intent.putExtra("gender", gender)
            intent.putExtra("department", department)
            employeeAdapterContext.startActivity(intent)
        }
    }
}

