package com.silverstudio.hostelissuesolver.fragments

import android.app.PendingIntent.getActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.silverstudio.hostelissuesolver.R
import com.silverstudio.hostelissuesolver.helper.RetrofitHelper
import com.silverstudio.hostelissuesolver.helper.RetrofitInterface
import com.silverstudio.hostelissuesolver.helper.allIssueReportResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class StudentIssuesFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: IssuesAdapter
    private lateinit var issuesList: List<allIssueReportResult>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_issues, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerView2)

        initComponent()
    }

    private fun initComponent(){

        issuesList = ArrayList<allIssueReportResult>()



        val response = RetrofitHelper.buildService(RetrofitInterface::class.java)
        response.allIssueReport().enqueue(
            object : Callback<List<allIssueReportResult>> {
                override fun onResponse(
                    call: Call<List<allIssueReportResult>>,
                    response: Response<List<allIssueReportResult>>
                ) {

                    if (response.code() == 200) {

                        for (i in response.body()!!){
                            issuesList = issuesList + i
                        }

                        adapter = IssuesAdapter(issuesList)

                        recyclerView.adapter = adapter
                        recyclerView.layoutManager = LinearLayoutManager(requireContext())
                        recyclerView.setHasFixedSize(true)





                    } else {

                        Toast.makeText(
                            requireActivity(),
                            "Something went wrong",
                            Toast.LENGTH_LONG
                        ).show()

                    }

                }

                override fun onFailure(call: Call<List<allIssueReportResult>>, t: Throwable) {
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

                }

            }
        )

    }


    }


