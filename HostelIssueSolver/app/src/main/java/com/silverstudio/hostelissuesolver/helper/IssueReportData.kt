package com.silverstudio.hostelissuesolver.helper

import android.provider.ContactsContract
import com.google.gson.annotations.SerializedName

data class IssueReportData(
    @SerializedName("id")
    var id: String,

    @SerializedName("name")
    var name: String,

    @SerializedName("regno")
    var regdNo: String,

    @SerializedName("issueType")
    var issueType: String,

    @SerializedName("specialization")
    var specialization: String,

    @SerializedName("description")
    var description: String,

    @SerializedName("preferred_timings")
    var preferredTimings: String,

    @SerializedName("room_no")
    var roomNo: String,

    @SerializedName("hostel")
    var hostel: String,

    @SerializedName("floor")
    var floor: String,

    @SerializedName("block_no")
    var block: String,

    @SerializedName("status")
    var status: String,

    @SerializedName("upvotes")
    var upvotes: String,

    @SerializedName("is_private")
    var isprivate: String,

    @SerializedName("number")
    var number: String,




    )
