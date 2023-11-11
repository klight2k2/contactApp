package com.example.phonebook

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DetailPhone : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_phone)

        val name = intent.getStringExtra("name")
        val phoneNumber = intent.getStringExtra("phoneNumber")
        val email = intent.getStringExtra("email")

        findViewById<TextView>(R.id.text_avt).text = name?.get(0)?.toString()
        findViewById<TextView>(R.id.fullname).text= "Fullname : $name"
        findViewById<TextView>(R.id.phone_number).text = "Phone number : $phoneNumber"
        findViewById<TextView>(R.id.email).text = "Email : $email"


    }
}