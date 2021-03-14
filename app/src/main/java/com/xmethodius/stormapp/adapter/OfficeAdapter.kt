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
import com.xmethodius.stormapp.model.Office

class OfficeAdapter(list : ArrayList<Office>, context: Context)
    : RecyclerView.Adapter<OfficeAdapter.OfficeViewHolder>() {

    private val officeList = list
    private val officeAdapterContext = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfficeViewHolder {
        return OfficeViewHolder(LayoutInflater.from(officeAdapterContext).inflate(
                R.layout.item_list,
                parent,
                false
        )
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: OfficeViewHolder, position: Int) {
        val item = officeList[position]
        val city = item.city
        val address = item.address
        val phone = item.phone
        val itemView = holder.itemView
        holder.city.text = city

        chooseOffice(itemView, city, address, phone)
    }

    override fun getItemCount(): Int {
        return officeList.size
    }

    class OfficeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var city : TextView = itemView.findViewById(R.id.item_title)
    }

    private fun chooseOffice(item: View, city: String, address: String, phone: String) {
        item.setOnClickListener {
            val intent = Intent(officeAdapterContext, DetailActivity::class.java)
            intent.putExtra("source", "office")
            intent.putExtra("city", city)
            intent.putExtra("address", address)
            intent.putExtra("phone", phone)
            officeAdapterContext.startActivity(intent)
        }
    }
}