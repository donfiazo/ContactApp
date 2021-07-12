package com.eddie.contactapp.models

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eddie.contactapp.databinding.ContactItemBinding

class ContactAdapter(var contacts : List<Contact>, val mover: (Contact) -> Unit )
    : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>(){

    inner class ContactViewHolder(private val gumming: ContactItemBinding)
        : RecyclerView.ViewHolder(gumming.root){
        fun gumContact(contact: Contact){
            gumming.apply {
                contactIdDisplay.text = contact.id.toString()
                fnameDisplay.text = contact.firstName
                lnameDisplay.text = contact.lastName
                phonenumberDisplay.text = contact.Phone.toString()
                emailDisplay.text = contact.Email
                root.setOnClickListener { mover(contact) }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val gumming = ContactItemBinding.inflate(LayoutInflater.from(parent.context))
        return ContactViewHolder(gumming)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.gumContact(contacts[position])
    }

    override fun getItemCount(): Int {
       return contacts.size
    }
}