package com.silverstudio.hostelissuesolver.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton
import com.silverstudio.hostelissuesolver.R

class StudentHomeFragment : Fragment() {

    private lateinit var nameText: TextView
    private lateinit var sharedPref : SharedPreferences

    private lateinit var reportIssue: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_home, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPref = requireActivity().getSharedPreferences("myPrefs", Context.MODE_PRIVATE)

        val name = sharedPref.getString("fullName", "Your Name");

        nameText = view.findViewById(R.id.textView9)
        nameText.text = name

        reportIssue = view.findViewById(R.id.btn_report)

        reportIssue.setOnClickListener {

            findNavController().navigate(R.id.action_studentHomeFragment_to_reportNewIssueFragment)
        }


    }
}