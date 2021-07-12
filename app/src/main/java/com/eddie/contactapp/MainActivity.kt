package com.eddie.contactapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.eddie.contactapp.activities.ContactDetailsActivity
import com.eddie.contactapp.viewmodels.MainActivityViewModel
import com.eddie.contactapp.databinding.ActivityMainBinding
import com.eddie.contactapp.models.Contact
import com.eddie.contactapp.models.ContactAdapter
import com.eddie.contactapp.models.ContactDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var gumming: ActivityMainBinding
    private lateinit var contactAdapter: ContactAdapter
    private lateinit var database: ContactDatabase
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gumming = ActivityMainBinding.inflate(layoutInflater)
        setContentView(gumming.root)

        database = Room.databaseBuilder(
            applicationContext, ContactDatabase::class.java,
            "contacts_database"
        ).allowMainThreadQueries().build()

        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        viewModel.getContacts(database)

        contactAdapter = ContactAdapter(listOf<Contact>()) {
            val intent = Intent(this@MainActivity, ContactDetailsActivity::class.java)
            intent.run {
                putExtra("Contact id", it.id)
                putExtra("First Name", it.firstName)
                putExtra("Last Name", it.lastName)
                putExtra("Phone Number", it.Phone)
                putExtra("Email", it.Email)
            }
            startActivity(intent)
        }
        gumming.contactsRv.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = contactAdapter
        }

        viewModel.contactsLiveData.observe(this, { contacts ->
            contactAdapter.contacts = contacts
            contactAdapter.notifyDataSetChanged()
        })

        gumming.saveButton.setOnClickListener {
            val firstname = gumming.fnameInput.text.toString()
            val lastname = gumming.lnameInput.text.toString()
            val phone = gumming.phonenumberInput.text.hashCode()
            val email = gumming.emailInput.text.toString()

            saveContact(firstname, lastname, phone, email)

        }
    }

    private fun saveContact(firstname: String, lastname: String, phone: Int, email: String) {
        val contact = Contact(id = 0, firstname, lastname, phone, email)
        viewModel.addContact(database,contact)
    }
}