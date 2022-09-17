package com.silverstudio.hostelissuesolver.acitivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.silverstudio.hostelissuesolver.R

class StudentLoginActivity : AppCompatActivity() {

    private lateinit var register: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_login)
        initComponent()

    }

    private fun initComponent(){

        register = findViewById(R.id.text_register_now)
        register.setOnClickListener {
            val intent = Intent(this,StudentRegisterActivity::class.java)
            startActivity(intent)

        }
    }

}