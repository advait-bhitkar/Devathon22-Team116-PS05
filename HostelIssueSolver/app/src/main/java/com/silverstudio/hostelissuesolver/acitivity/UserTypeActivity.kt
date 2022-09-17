package com.silverstudio.hostelissuesolver.acitivity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import com.google.android.material.button.MaterialButton
import com.silverstudio.hostelissuesolver.R

class UserTypeActivity : AppCompatActivity() {

    private lateinit var userType: AutoCompleteTextView
    private lateinit var continueButton: MaterialButton

    private lateinit var sharedPref:SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPref = getSharedPreferences("myPrefs", Context.MODE_PRIVATE)

        val isLogin = sharedPref.getBoolean("isLogin", false);

        val isStudentDetail = sharedPref.getBoolean("isStudentDetail", false);

        if (isLogin && isStudentDetail)
        {

            val intent = Intent(this,StudentMainActivity::class.java)
            startActivity(intent)
            finish()

        }

        else if (isLogin)
        {
            val intent = Intent(this,StudentDetailsActivity::class.java)
            startActivity(intent)
            finish()

        }

        setContentView(R.layout.activity_user_type)
        initComponents()
    }

    private fun initComponents(){

        userType = findViewById(R.id.device_type)
        continueButton = findViewById(R.id.continue_button)
        val items = listOf("Student","Worker")
        val adapter = ArrayAdapter(this, R.layout.list_item, items)
        userType.setAdapter(adapter)


        continueButton.setOnClickListener {

            if (userType.text.length < 2)
            {
                Toast.makeText(this, "Please select user type", Toast.LENGTH_SHORT).show()
            }
            else if (userType.text.toString() == "Student"){
                val intent = Intent(this,StudentRegisterActivity::class.java)
                intent.putExtra("userType",userType.text.toString())
                startActivity(intent)
            }
            else if (userType.text.toString() == "Worker"){
                val intent = Intent(this,WorkerRegisterActivity::class.java)
                intent.putExtra("userType",userType.text.toString())
                startActivity(intent)
            }
        }


    }
}