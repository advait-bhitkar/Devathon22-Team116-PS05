package com.silverstudio.hostelissuesolver.helper

import android.provider.ContactsContract
import com.google.gson.annotations.SerializedName

data class StudentInfoResult(
    @SerializedName("msg")
    var msg: String,

    @SerializedName("id")
    var _id: String,

    )
