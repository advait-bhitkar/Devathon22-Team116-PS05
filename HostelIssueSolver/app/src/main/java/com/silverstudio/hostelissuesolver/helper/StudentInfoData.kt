package com.silverstudio.hostelissuesolver.helper

import android.provider.ContactsContract
import com.google.gson.annotations.SerializedName

data class StudentInfoData(
    @SerializedName("name")
    var fullName: String,

    @SerializedName("branch")
    var branch: String,

    @SerializedName("year")
    var year: String,

    @SerializedName("mobileno")
    var mobileNo: String,

    @SerializedName("gender")
    var gender: String,

    @SerializedName("hostel")
    var hostel: String,

    @SerializedName("room_no")
    var roomNo: String,

    @SerializedName("floor")
    var floor: String,


    @SerializedName("block_no")
    var block: String,

    @SerializedName("id")
    var id: String,

    )


