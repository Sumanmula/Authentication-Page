package com.example.firstapplication.model

import com.google.gson.annotations.SerializedName

data class Person(
    @SerializedName("Name")val Name: String,
    @SerializedName("Bio")val Bio: String,
    @SerializedName("Description")val Description: String,
    @SerializedName("Image")val Image: String,
    @SerializedName("Contact")val Contact: Contact,
    @SerializedName("Address")val Address: String,
    @SerializedName("ID")val id: String
)

data class Contact(
    @SerializedName("Phone")val Phone: String,
    @SerializedName("Email")val Email: String
)
