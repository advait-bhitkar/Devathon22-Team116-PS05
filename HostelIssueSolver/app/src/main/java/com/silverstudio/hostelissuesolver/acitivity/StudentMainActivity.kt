package com.silverstudio.hostelissuesolver.acitivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.silverstudio.hostelissuesolver.R
import com.silverstudio.hostelissuesolver.fragments.StudentAccountFragment
import com.silverstudio.hostelissuesolver.fragments.StudentHomeFragment
import com.silverstudio.hostelissuesolver.fragments.StudentIssuesFragment

class StudentMainActivity : AppCompatActivity() {
    lateinit var bottomNav : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_main2)
        val navView: BottomNavigationView = findViewById(R.id.bottomNav)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
//        val appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.homeFragment2, R.id.contactsFragment2, R.id.notificationsFragment3
//            )
//        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


    }
    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}



    private fun initComponents(){

    }

