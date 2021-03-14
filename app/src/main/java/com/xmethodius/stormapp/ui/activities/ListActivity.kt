package com.xmethodius.stormapp.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.xmethodius.stormapp.R
import com.xmethodius.stormapp.adapter.EmployeeAdapter
import com.xmethodius.stormapp.adapter.OfficeAdapter
import com.xmethodius.stormapp.model.Employee
import com.xmethodius.stormapp.model.Office
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset

class ListActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    lateinit var mAdView : AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        openEmployeeList()
        openOfficeList()

        MobileAds.initialize(this)
        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
    }

    private fun openOfficeList() {
        recyclerView = findViewById(R.id.officesList)
        val officeList: ArrayList<Office> = ArrayList()

        try {
            val fileName = "company_offices.json"
            val obj = JSONObject(getJSONFromAssets(fileName)!!)
            val array = obj.getJSONArray("office")

            for (i in 0 until array.length()) {
                val user = array.getJSONObject(i)
                val id = user.getInt("id")
                val city = user.getString("city")
                val address = user.getString("address")
                val phone = user.getString("phone")

                val officeModelDetails = Office(id, city, address, phone)

                officeList.add(officeModelDetails)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.isNestedScrollingEnabled = false
        recyclerView.adapter = OfficeAdapter(officeList, this)
    }

    private fun openEmployeeList() {
        recyclerView = findViewById(R.id.employeesList)
        val employeeList: ArrayList<Employee> = ArrayList()

        try {
            val fileName = "top_employees.json"
            val obj = JSONObject(getJSONFromAssets(fileName)!!)
            val array = obj.getJSONArray("employee")

            for (i in 0 until array.length()) {
                val user = array.getJSONObject(i)
                val id = user.getInt("id")
                val firstName = user.getString("first_name")
                val lastName = user.getString("last_name")
                val email = user.getString("email")
                val gender = user.getString("gender")
                val department = user.getString("department")

                val employeeModelDetails = Employee(id, firstName, lastName, email, gender, department)

                employeeList.add(employeeModelDetails)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.isNestedScrollingEnabled = false
        recyclerView.adapter = EmployeeAdapter(employeeList, this)
    }

    private fun getJSONFromAssets(file: String): String? {
        val json: String?
        val charset: Charset = Charsets.UTF_8
        try {
            val fileContent = assets.open(file)
            val size = fileContent.available()
            val bufferData = ByteArray(size)
            fileContent.read(bufferData)
            fileContent.close()
            json = String(bufferData, charset)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }
}