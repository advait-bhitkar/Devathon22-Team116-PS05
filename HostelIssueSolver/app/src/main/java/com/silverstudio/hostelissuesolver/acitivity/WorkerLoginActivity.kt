package com.silverstudio.hostelissuesolver.acitivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.silverstudio.hostelissuesolver.R
import org.w3c.dom.Text

class WorkerLoginActivity : AppCompatActivity() {

    private lateinit var register: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_worker_login)
        initComponent()
    }

    private fun initComponent(){

        register = findViewById(R.id.text_register_now)
        register.setOnClickListener {
            val intent = Intent(this,WorkerRegisterActivity::class.java)
            startActivity(intent)

        }
    }

}