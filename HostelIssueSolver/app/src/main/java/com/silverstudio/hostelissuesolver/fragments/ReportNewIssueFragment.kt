package com.silverstudio.hostelissuesolver.fragments

import android.app.ProgressDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.widget.AppCompatAutoCompleteTextView
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton
import com.silverstudio.hostelissuesolver.R
import com.silverstudio.hostelissuesolver.helper.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReportNewIssueFragment : Fragment() {

    private lateinit var name:    AppCompatEditText
    private lateinit var regNo: AppCompatEditText
    private lateinit var issueType: AppCompatAutoCompleteTextView
    private lateinit var specialization: AppCompatAutoCompleteTextView
    private lateinit var description:AppCompatEditText
    private lateinit var preferredTime: AppCompatEditText
    var dialog: ProgressDialog? = null
    private lateinit var sharedPref : SharedPreferences

    private lateinit var reportButton: MaterialButton



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_report_new_issue, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        name = view.findViewById(R.id.text_username)
        regNo = view.findViewById(R.id.text_regd)
        issueType = view.findViewById(R.id.issueType)
        specialization = view.findViewById(R.id.specialization)
        description = view.findViewById(R.id.text_desc)
        preferredTime = view.findViewById(R.id.text_time)

        reportButton = view.findViewById(R.id.btn_report)


        name.isEnabled = false
        regNo.isEnabled = false

        sharedPref = requireActivity().getSharedPreferences("myPrefs", Context.MODE_PRIVATE)

        val fullNameT = sharedPref.getString("fullName", "");
        val regdNoT = sharedPref.getString("regdNo", "");

        name.setText(fullNameT)
        regNo.setText(regdNoT)

        val items = listOf("Cleaner","Electrician", "Carpenter", "Plumber")
        val adapter = ArrayAdapter(requireActivity(), R.layout.list_item, items)
        issueType.setAdapter(adapter)


        issueType.doOnTextChanged { text, start, before, count ->

            if (issueType.text.toString() == "Electrician") {
                val items1 =
                    listOf("Wiring", "Fan", "TubeLight", "Shot circuit", "Cooler")
                val adapter1 = ArrayAdapter(requireActivity(), R.layout.list_item, items1)
                specialization.setAdapter(adapter1)
            }
            else if (issueType.text.toString() == "Cleaner") {
                val items1 =
                    listOf("Room Cleaner", "Toilet Cleaner", "Washroom Cleaner", "Bicycle Cleaner")
                val adapter1 = ArrayAdapter(requireActivity(), R.layout.list_item, items1)
                specialization.setAdapter(adapter1)
            }
            else if (issueType.text.toString() == "Carpenter") {
                val items1 =
                    listOf("Table", "Chair", "Bed", "Doors")
                val adapter1 = ArrayAdapter(requireActivity(), R.layout.list_item, items1)
                specialization.setAdapter(adapter1)
            }
            else if (issueType.text.toString() == "Plumber") {
                val items1 =
                    listOf("Tap", "Leakage", "Washrooms")
                val adapter1 = ArrayAdapter(requireActivity(), R.layout.list_item, items1)
                specialization.setAdapter(adapter1)
            }

        }


        reportButton.setOnClickListener {

            val _idT = sharedPref.getString("_id", "");
            val nameT = sharedPref.getString("fullName", "");
            val regNo = sharedPref.getString("regdNo", "");
            val issueTp = issueType.text.toString()
            val specializationTp = specialization.text.toString()
            val descTp = description.text.toString()
            val preffTp = preferredTime.text.toString()
            val roomNo = sharedPref.getString("roomNo", "");
            val hostelT = sharedPref.getString("hostel", "");
            val floorT = sharedPref.getString("floor", "");
            val blockT = sharedPref.getString("block", "");
            val status = "pending"
            val upvotes = 0
            val isprivate = "No"
            val mobileNoT = sharedPref.getString("mobileNo", "");



            val reportIssueData = IssueReportData(
                id = _idT.toString(),
                name = nameT.toString(),
                regdNo = regdNoT.toString(),
                issueType = issueTp.toString(),
                specialization = specializationTp.toString(),
                description = descTp.toString(),
                preferredTimings = preferredTime.toString(),
                roomNo = roomNo.toString(),
                hostel = hostelT.toString(),
                floor = floorT.toString(),
                block = blockT.toString(),
                status = status.toString(),
                upvotes =  upvotes.toString(),
                isprivate = isprivate.toString(),
                number = mobileNoT.toString()
            )



            dialog = ProgressDialog.show(requireActivity(), "", "Please wait...", true);

            val response = RetrofitHelper.buildService(RetrofitInterface::class.java)
            response.issueReport(reportIssueData).enqueue(
                object : Callback<IssueReportResult> {
                    override fun onResponse(
                        call: Call<IssueReportResult>,
                        response: Response<IssueReportResult>
                    ) {


                        dialog?.dismiss()

                        if (response.code() == 200) {



                            Toast.makeText(
                                requireActivity(),
                                "Issue Created successfully",
                                Toast.LENGTH_LONG
                            ).show()

                            findNavController().navigate(R.id.action_reportNewIssueFragment_to_studentHomeFragment)


                        } else {

                            Toast.makeText(
                                requireActivity(),
                                "Something went wrong",
                                Toast.LENGTH_LONG
                            ).show()

                        }

                    }

                    override fun onFailure(call: Call<IssueReportResult>, t: Throwable) {
                        Toast.makeText(
                            requireActivity(),
                            "Error entering details",
                            Toast.LENGTH_LONG
                        ).show()
                        Toast.makeText(
                            requireActivity(),
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