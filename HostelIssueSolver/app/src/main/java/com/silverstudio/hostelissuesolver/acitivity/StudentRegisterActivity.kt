package com.silverstudio.hostelissuesolver.acitivity

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import com.google.android.material.button.MaterialButton
import com.silverstudio.hostelissuesolver.R
import com.silverstudio.hostelissuesolver.helper.RetrofitHelper
import com.silverstudio.hostelissuesolver.helper.RetrofitInterface
import com.silverstudio.hostelissuesolver.helper.StudentRegisterData
import com.silverstudio.hostelissuesolver.helper.StudentRegisterResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StudentRegisterActivity : AppCompatActivity() {

    private lateinit var signIn: TextView
    private lateinit var createAccount: MaterialButton

    private lateinit var collegeEmail: AppCompatEditText
    private lateinit var registrationNumber: AppCompatEditText
    private lateinit var password: AppCompatEditText
    var dialog: ProgressDialog? = null
    private lateinit var sharedPref : SharedPreferences



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        sharedPref = getSharedPreferences("myPrefs", Context.MODE_PRIVATE)

        val isLogin = sharedPref.getBoolean("isLogin", false);
        val isStudentDetail = sharedPref.getBoolean("isStudentDetail", false);

        if (isLogin && isStudentDetail)
        {

            val intent = Intent(this,StudentMainActivity::class.java)
            startActivity(intent)

        }
        else if (isLogin)
        {
            val intent = Intent(this,StudentDetailsActivity::class.java)
            startActivity(intent)

        }

        setContentView(R.layout.activity_student_register)
        initComponent()
    }

    private fun initComponent(){


        signIn = findViewById(R.id.text_sign_in)
        createAccount = findViewById(R.id.btn_create_account)
        collegeEmail = findViewById(R.id.text_email)
        registrationNumber = findViewById(R.id.text_registration_no)
        password = findViewById(R.id.text_password)


        signIn.setOnClickListener {
            val intent = Intent(this,StudentLoginActivity::class.java)
            startActivity(intent)
        }

        createAccount.setOnClickListener {


            if(password.text.toString().length <6)
            {
                Toast.makeText(this, "Password must be more than 6 characters",Toast.LENGTH_LONG).show()

            }
            else if (!collegeEmail.text!!.contains('@')){

                Toast.makeText(this, "Please enter correct email!!",Toast.LENGTH_LONG).show()

            }
            else if (registrationNumber.text!!.length > 4  && collegeEmail.text!!.length > 5) {

                dialog = ProgressDialog.show(this, "", "Please wait...", true);

                val studentRegisterInfo = StudentRegisterData(
                    collegeEmail = collegeEmail.text.toString(),
                    registrationNumber = registrationNumber.text.toString(),
                    password = password.text.toString(),
                )


                val response = RetrofitHelper.buildService(RetrofitInterface::class.java)
                response.registerStudent(studentRegisterInfo).enqueue(
                    object : Callback<StudentRegisterResult> {
                        override fun onResponse(
                            call: Call<StudentRegisterResult>,
                            response: Response<StudentRegisterResult>
                        ) {

//                            Toast.makeText(
//                                this@StudentRegisterActivity,
//                                response.code().toString(),
//                                Toast.LENGTH_LONG
//                            ).show()
//

                            if (response.code() == 201) {

                                Toast.makeText(
                                    this@StudentRegisterActivity,
                                    "Please enter college email",
                                    Toast.LENGTH_LONG
                                ).show()

                            }
                            else {

                                Toast.makeText(
                                    this@StudentRegisterActivity,
                                    response.body()!!._id,
                                    Toast.LENGTH_LONG
                                ).show()
                                val editor = sharedPref.edit()
                                editor.putString("_id", response.body()!!._id.toString())
                                editor.putBoolean("isLogin", true)
                                editor.putString("regdNo", studentRegisterInfo.registrationNumber)
                                editor.putString("email", studentRegisterInfo.collegeEmail)

                                editor.apply()

                                val intent = Intent(
                                    this@StudentRegisterActivity,
                                    StudentDetailsActivity::class.java
                                )
                                startActivity(intent)

                            }

                            dialog?.dismiss()

                        }

                        override fun onFailure(call: Call<StudentRegisterResult>, t: Throwable) {
                            Toast.makeText(
                                this@StudentRegisterActivity,
                                "Error registering user",
                                Toast.LENGTH_LONG
                            ).show()
                            Toast.makeText(
                                this@StudentRegisterActivity,
                                t.toString(),
                                Toast.LENGTH_LONG
                            ).show()
                            dialog?.dismiss()

                        }

                    }
                )


            }
            else{

                Toast.makeText(this, "Please enter correct details!!",Toast.LENGTH_LONG).show()

            }


        }


    }
}