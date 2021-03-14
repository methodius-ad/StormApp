package com.xmethodius.stormapp.ui.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.xmethodius.stormapp.R

class OfficeDetailFragment : Fragment() {

    private lateinit var cityView: TextView
    private lateinit var addressView: TextView
    private lateinit var phoneView: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_office_detail, container, false)
        bindOfficeDetailView(view)
        fetchOfficeExtra()
        return view
    }

    private fun bindOfficeDetailView(view: View) {
        cityView = view.findViewById(R.id.city)
        addressView = view.findViewById(R.id.address)
        phoneView = view.findViewById(R.id.phone)
    }

    private fun fetchOfficeExtra() {
        val intent = activity?.intent
        val cityExtra: String? = intent?.getStringExtra("city")
        val addressExtra: String? = intent?.getStringExtra("address")
        val phoneExtra: String? = intent?.getStringExtra("phone")

        fillOfficeDetailView(cityExtra, addressExtra, phoneExtra)
    }

    @SuppressLint("SetTextI18n")
    private fun fillOfficeDetailView(city: String?, address: String?, phone: String?) {
        cityView.text = "CITY: $city"
        addressView.text = "ADDRESS: $address"
        phoneView.text = "PHONE: $phone"
    }
}