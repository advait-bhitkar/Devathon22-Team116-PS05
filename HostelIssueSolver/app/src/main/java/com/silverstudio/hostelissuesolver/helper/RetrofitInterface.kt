package com.silverstudio.hostelissuesolver.helper

import com.silverstudio.hostelissuesolver.fragments.ReportNewIssueFragment
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST


interface RetrofitInterface {



    @Headers("Content-Type: application/json")
    @POST("studentsignup")
    fun registerStudent(@Body studentRegisterData: StudentRegisterData) : Call<StudentRegisterResult>


    @Headers("Content-Type: application/json")
    @POST("studentprofile")
    fun studentInfo(@Body studentInfoData: StudentInfoData) : Call<StudentInfoResult>


    @Headers("Content-Type: application/json")
    @POST("postissue")
    fun issueReport(@Body issueReportData: IssueReportData) : Call<IssueReportResult>

}