package com.silverstudio.hostelissuesolver.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.silverstudio.hostelissuesolver.R
import com.silverstudio.hostelissuesolver.helper.allIssueReportResult

class IssuesAdapter(private val contactList: List<allIssueReportResult>) : RecyclerView.Adapter<IssuesAdapter.ContactViewHolder>(){



    class ContactViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val name: TextView = itemView.findViewById(R.id.fullName)
        val regdNo: TextView = itemView.findViewById(R.id.regNo)
        val issueType: TextView = itemView.findViewById(R.id.issueType)
        val specialization: TextView = itemView.findViewById(R.id.specialization)
        val roomNo: TextView = itemView.findViewById(R.id.room_no)
        val hostel: TextView = itemView.findViewById(R.id.hostel)
        val upvotes: TextView = itemView.findViewById(R.id.upvotes)




    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_issues, parent, false)
        return ContactViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {

        val currentItem = contactList[position]

        holder.name.text = "Name: " + currentItem.name
        holder.regdNo.text = "Reg No: " + currentItem.regdNo
        holder.issueType.text = "Issue Type: " + currentItem.issueType
        holder.specialization.text = "Status: " + currentItem.status
        holder.roomNo.text = "Room no: " + currentItem.roomNo
        holder.hostel.text = "Hostel: " + currentItem.hostel
        holder.upvotes.text = "Upvotes: " + currentItem.upvotes

    }

    override fun getItemCount(): Int {

        return contactList.size

    }

}