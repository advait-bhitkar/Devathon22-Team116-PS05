package com.silverstudio.hostelissuesolver.acitivity

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatAutoCompleteTextView
import androidx.appcompat.widget.AppCompatEditText
import com.google.android.material.button.MaterialButton
import com.silverstudio.hostelissuesolver.R
import com.silverstudio.hostelissuesolver.helper.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StudentDetailsActivity : AppCompatActivity() {

    private lateinit var btnContinue: MaterialButton

    private lateinit var gender: AutoCompleteTextView
    private lateinit var hostel: AutoCompleteTextView
    private lateinit var fullName: AppCompatEditText
    private lateinit var branch: AppCompatEditText
    private lateinit var year: AppCompatEditText
    private lateinit var mobileNo: AppCompatEditText
    private lateinit var roomNo: AppCompatEditText
    private lateinit var floor: AppCompatEditText
    private lateinit var block: AppCompatEditText

    var dialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_details)
        initComponents()
    }

    private fun initComponents(){

        btnContinue =findViewById(R.id.btn_continue)
        gender = findViewById(R.id.gender)
        hostel = findViewById(R.id.hostel)
        fullName = findViewById(R.id.fullName)
        branch = findViewById(R.id.branch)
        year = findViewById(R.id.text_year)
        mobileNo = findViewById(R.id.text_mobile_number)
        roomNo = findViewById(R.id.text_room_number)
        floor = findViewById(R.id.text_floor)
        block = findViewById(R.id.text_block)






        val items = listOf("Male","Female", "Other")
        val adapter = ArrayAdapter(this, R.layout.list_item, items)
        gender.setAdapter(adapter)

        val items1 = listOf("NEW LH","Priyadarshini","Sarojini","1.8k","1k","ISH","Blocks")
        val adapter1 = ArrayAdapter(this, R.layout.list_item, items1)
        hostel.setAdapter(adapter1)


        btnContinue.setOnClickListener {

            if (fullName.text!!.isEmpty() ||
                    branch.text!!.isEmpty() ||
                    year.text!!.isEmpty() ||
                    mobileNo.text!!.isEmpty() ||
                        gender.text.isEmpty() ||
                        hostel.text.isEmpty() ||
                        roomNo.text!!.isEmpty() ||
                        floor.text!!.isEmpty() ||
                        block.text!!.isEmpty()
                    ){

                Toast.makeText(this, "Enter all details", Toast.LENGTH_SHORT).show()
            }
            else{

               var  sharedPref = getSharedPreferences("myPrefs", Context.MODE_PRIVATE)

                val id = sharedPref.getString("_id", "");


                val studentInfoData = StudentInfoData(
                    fullName = fullName.text.toString(),
                    branch = branch.text.toString(),
                    year = year.text.toString(),
                    mobileNo = mobileNo.text.toString(),
                    gender = gender.text.toString(),
                    hostel = hostel.text.toString(),
                    roomNo = roomNo.text.toString(),
                    floor = floor.text.toString(),
                    block =  block.text.toString(),
                    id = id.toString()
                    )


                dialog = ProgressDialog.show(this, "", "Please wait...", true);

                val response = RetrofitHelper.buildService(RetrofitInterface::class.java)
                response.studentInfo(studentInfoData).enqueue(
                    object : Callback<StudentInfoResult> {
                        override fun onResponse(
                            call: Call<StudentInfoResult>,
                            response: Response<StudentInfoResult>
                        ) {


                            Toast.makeText(
                                this@StudentDetailsActivity,
                                "Student Info saved successfully",
                                Toast.LENGTH_LONG
                            ).show()

                            dialog?.dismiss()

                            if(response.code() == 200) {

                                Toast.makeText(
                                    this@StudentDetailsActivity,
                                    studentInfoData.block.toString(),
                                    Toast.LENGTH_LONG
                                ).show()


                                val editor = sharedPref.edit()
                                editor.putBoolean("isStudentDetail", true)
                                editor.putString("fullName", studentInfoData.fullName.toString())
                                editor.putString("branch", studentInfoData.branch.toString())
                                editor.putString("year", studentInfoData.year.toString())
                                editor.putString("mobileNo", studentInfoData.mobileNo.toString())
                                editor.putString("gender", studentInfoData.gender.toString())
                                editor.putString("hostel", studentInfoData.hostel.toString())
                                editor.putString("roomNo", studentInfoData.roomNo.toString())
                                editor.putString("floor", studentInfoData.floor.toString())
                                editor.putString("block", studentInfoData.block.toString())

                                editor.apply()


                                val intent = Intent(
                                    this@StudentDetailsActivity,
                                    StudentMainActivity::class.java
                                )
                                startActivity(intent)

                            }
                            else{

                                Toast.makeText(
                                    this@StudentDetailsActivity,
                                    "Something went wrong",
                                    Toast.LENGTH_LONG
                                ).show()

                            }

                        }

                        override fun onFailure(call: Call<StudentInfoResult>, t: Throwable) {
                            Toast.makeText(
                                this@StudentDetailsActivity,
                                "Error entering details",
                                Toast.LENGTH_LONG
                            ).show()
                            Toast.makeText(
                                this@StudentDetailsActivity,
                                t.toString(),
                                Toast.LENGTH_LONG
                            ).show()
                            dialog?.dismiss()

                        }

                    }
                )



            }


        }

    }
}