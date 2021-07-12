package com.eddie.contactapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.eddie.contactapp.R
import com.eddie.contactapp.databinding.ActivityContactDetailsBinding

class ContactDetailsActivity : AppCompatActivity() {

    private lateinit var gumming: ActivityContactDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gumming = ActivityContactDetailsBinding.inflate(layoutInflater)
        setContentView(gumming.root)

        gumming.run {
            firstnameTv.text = intent.getStringExtra("First Name")
            lastnameTv.text = intent.getStringExtra("Last Name")
            phoneNumberTv.text = intent.getStringExtra("Phone Number").toString()
            emailTv.text = intent.getStringExtra("E-Mail")
            emailTv.text = intent.getStringExtra("Email")

        }
    }
}