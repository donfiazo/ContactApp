package com.eddie.contactapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Contact(
    @PrimaryKey (autoGenerate = true)
    val id :    Int,
    val firstName : String,
    val lastName :  String,
    val Phone   :   Int,
    val Email   :   String
)
