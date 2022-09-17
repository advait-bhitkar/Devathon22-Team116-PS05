package com.silverstudio.hostelissuesolver.helper

import android.provider.ContactsContract
import com.google.gson.annotations.SerializedName

data class StudentRegisterData(
    @SerializedName("email")
    var collegeEmail: String,

    @SerializedName("regno")
    var registrationNumber: String,

    @SerializedName("password")
    var password: String,

)
