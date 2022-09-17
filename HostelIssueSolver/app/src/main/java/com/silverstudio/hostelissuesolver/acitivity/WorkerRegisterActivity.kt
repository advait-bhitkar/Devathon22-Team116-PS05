package com.silverstudio.hostelissuesolver.acitivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.silverstudio.hostelissuesolver.R
import kotlin.math.log

class WorkerRegisterActivity : AppCompatActivity() {

    private lateinit var logIn: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_worker_register)
        initComponent()
    }

    private fun initComponent(){

        logIn = findViewById(R.id.text_sign_in)
        logIn.setOnClickListener {

            val intent = Intent(this,WorkerLoginActivity::class.java)
            startActivity(intent)

        }


    }

}