package com.silverstudio.hostelissuesolver.fragments

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import com.google.android.material.button.MaterialButton
import com.silverstudio.hostelissuesolver.R
import com.silverstudio.hostelissuesolver.acitivity.StudentMainActivity
import com.silverstudio.hostelissuesolver.helper.RetrofitHelper
import com.silverstudio.hostelissuesolver.helper.RetrofitInterface
import com.silverstudio.hostelissuesolver.helper.StudentInfoData
import com.silverstudio.hostelissuesolver.helper.StudentInfoResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StudentAccountFragment : Fragment() {

    var dialog: ProgressDialog? = null
    private lateinit var sharedPref : SharedPreferences


    private lateinit var gender: AutoCompleteTextView
    private lateinit var hostel: AutoCompleteTextView
    private lateinit var fullName: AppCompatEditText
    private lateinit var branch: AppCompatEditText
    private lateinit var year: AppCompatEditText
    private lateinit var mobileNo: AppCompatEditText
    private lateinit var roomNo: AppCompatEditText
    private lateinit var floor: AppCompatEditText
    private lateinit var block: AppCompatEditText


    private lateinit var btnGender: MaterialButton
    private lateinit var btnHostel: MaterialButton
    private lateinit var btnFullName: MaterialButton
    private lateinit var btnBranch: MaterialButton
    private lateinit var btnYear: MaterialButton
    private lateinit var btnMobileNo: MaterialButton
    private lateinit var btnRoomNo: MaterialButton
    private lateinit var btnFloor: MaterialButton
    private lateinit var btnBlock: MaterialButton

    private lateinit var btnSaveData: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return  inflater.inflate(R.layout.fragment_student_account, container, false)



    }

    override fun onResume() {
        super.onResume()

        sharedPref = requireActivity().getSharedPreferences("myPrefs", Context.MODE_PRIVATE)

        gender = requireView().findViewById(R.id.gender)
        hostel = requireView().findViewById(R.id.hostel)
        fullName = requireView().findViewById(R.id.fullName)
        branch = requireView().findViewById(R.id.branch)
        year = requireView().findViewById(R.id.text_year)
        mobileNo = requireView().findViewById(R.id.text_mobile_number)
        roomNo = requireView().findViewById(R.id.text_room_number)
        floor = requireView().findViewById(R.id.text_floor)
        block = requireView().findViewById(R.id.text_block)

        btnGender = requireView().findViewById(R.id.editGender)
        btnHostel = requireView().findViewById(R.id.editHostel)
        btnFullName = requireView().findViewById(R.id.editBtnName)
        btnBranch = requireView().findViewById(R.id.editBtnbranch)
        btnYear = requireView().findViewById(R.id.editBtnYear)
        btnMobileNo = requireView().findViewById(R.id.editMobileNumber)
        btnRoomNo = requireView().findViewById(R.id.editRoom)
        btnFloor = requireView().findViewById(R.id.editFloor)
        btnBlock = requireView().findViewById(R.id.editBlock)

        btnSaveData = requireView().findViewById(R.id.btn_continue)

        btnSaveData.visibility = View.GONE


        val fullNameT = sharedPref.getString("fullName", "");
        val branchT = sharedPref.getString("branch", "");
        val yearT = sharedPref.getString("year", "");
        val mobileNoT = sharedPref.getString("mobileNo", "");
        val genderT = sharedPref.getString("gender", "");
        val hostelT = sharedPref.getString("hostel", "");
        val roomNoT = sharedPref.getString("roomNo", "");
        val floorT = sharedPref.getString("floor", "");
        val blockT = sharedPref.getString("block", "");

        fullName.setText(fullNameT)
        branch.setText(branchT)
        year.setText(yearT)
        mobileNo.setText(mobileNoT)
        gender.setText(genderT)
        hostel.setText(hostelT)
        roomNo.setText(roomNoT)
        floor.setText(floorT)
        block.setText(blockT)

        gender.setHint(genderT)

        val items = listOf("Male","Female", "Other")
        val adapter = ArrayAdapter(requireActivity(), R.layout.list_item, items)
        gender.setAdapter(adapter)

        val items1 = listOf("NEW LH","Priyadarshini","Sarojini","1.8k","1k","ISH","Blocks")
        val adapter1 = ArrayAdapter(requireActivity(), R.layout.list_item, items1)
        hostel.setAdapter(adapter1)


    }


    override fun onStart() {
        super.onStart()

        sharedPref = requireActivity().getSharedPreferences("myPrefs", Context.MODE_PRIVATE)

        gender = requireView().findViewById(R.id.gender)
        hostel = requireView().findViewById(R.id.hostel)
        fullName = requireView().findViewById(R.id.fullName)
        branch = requireView().findViewById(R.id.branch)
        year = requireView().findViewById(R.id.text_year)
        mobileNo = requireView().findViewById(R.id.text_mobile_number)
        roomNo = requireView().findViewById(R.id.text_room_number)
        floor = requireView().findViewById(R.id.text_floor)
        block = requireView().findViewById(R.id.text_block)

        btnGender = requireView().findViewById(R.id.editGender)
        btnHostel = requireView().findViewById(R.id.editHostel)
        btnFullName = requireView().findViewById(R.id.editBtnName)
        btnBranch = requireView().findViewById(R.id.editBtnbranch)
        btnYear = requireView().findViewById(R.id.editBtnYear)
        btnMobileNo = requireView().findViewById(R.id.editMobileNumber)
        btnRoomNo = requireView().findViewById(R.id.editRoom)
        btnFloor = requireView().findViewById(R.id.editFloor)
        btnBlock = requireView().findViewById(R.id.editBlock)

        btnSaveData = requireView().findViewById(R.id.btn_continue)



        val fullNameT = sharedPref.getString("fullName", "");
        val branchT = sharedPref.getString("branch", "");
        val yearT = sharedPref.getString("year", "");
        val mobileNoT = sharedPref.getString("mobileNo", "");
        val genderT = sharedPref.getString("gender", "");
        val hostelT = sharedPref.getString("hostel", "");
        val roomNoT = sharedPref.getString("roomNo", "");
        val floorT = sharedPref.getString("floor", "");
        val blockT = sharedPref.getString("block", "");

        fullName.setText(fullNameT)
        branch.setText(branchT)
        year.setText(yearT)
        mobileNo.setText(mobileNoT)
        gender.setText(genderT)
        hostel.setText(hostelT)
        roomNo.setText(roomNoT)
        floor.setText(floorT)
        block.setText(blockT)
        sharedPref = requireActivity().getSharedPreferences("myPrefs", Context.MODE_PRIVATE)




    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPref = requireActivity().getSharedPreferences("myPrefs", Context.MODE_PRIVATE)

        gender = view.findViewById(R.id.gender)
        hostel = view.findViewById(R.id.hostel)
        fullName = view.findViewById(R.id.fullName)
        branch = view.findViewById(R.id.branch)
        year = view.findViewById(R.id.text_year)
        mobileNo = view.findViewById(R.id.text_mobile_number)
        roomNo = view.findViewById(R.id.text_room_number)
        floor = view.findViewById(R.id.text_floor)
        block = view.findViewById(R.id.text_block)

        btnGender = view.findViewById(R.id.editGender)
        btnHostel = view.findViewById(R.id.editHostel)
        btnFullName = view.findViewById(R.id.editBtnName)
        btnBranch = view.findViewById(R.id.editBtnbranch)
        btnYear = view.findViewById(R.id.editBtnYear)
        btnMobileNo = view.findViewById(R.id.editMobileNumber)
        btnRoomNo = view.findViewById(R.id.editRoom)
        btnFloor = view.findViewById(R.id.editFloor)
        btnBlock = view.findViewById(R.id.editBlock)

        btnSaveData = view.findViewById(R.id.btn_continue)



        val fullNameT = sharedPref.getString("fullName", "");
        val branchT = sharedPref.getString("branch", "");
        val yearT = sharedPref.getString("year", "");
        val mobileNoT = sharedPref.getString("mobileNo", "");
        val genderT = sharedPref.getString("gender", "");
        val hostelT = sharedPref.getString("hostel", "");
        val roomNoT = sharedPref.getString("roomNo", "");
        val floorT = sharedPref.getString("floor", "");
        val blockT = sharedPref.getString("block", "");

        fullName.setText(fullNameT)
        branch.setText(branchT)
        year.setText(yearT)
        mobileNo.setText(mobileNoT)
        gender.setText(genderT)
        hostel.setText(hostelT)
        roomNo.setText(roomNoT)
        floor.setText(floorT)
        block.setText(blockT)

        gender.isEnabled = false
        hostel.isEnabled = false
        fullName.isEnabled = false
        branch.isEnabled = false
        year.isEnabled = false
        mobileNo.isEnabled = false
        roomNo.isEnabled = false
        floor.isEnabled = false
        block.isEnabled = false


        btnGender.setOnClickListener {
            if (btnGender.isEnabled){
                gender.isEnabled = true
                btnSaveData.visibility = View.GONE
            }
            else{
                gender.isEnabled = false
                btnSaveData.visibility = View.VISIBLE

            }
        }

        btnHostel.setOnClickListener {
            if (btnHostel.isEnabled){
                btnHostel.isEnabled = false
                btnSaveData.visibility = View.GONE
            }
            else{
                btnHostel.isEnabled = true
                btnSaveData.visibility = View.VISIBLE

            }
        }

        btnFullName.setOnClickListener {
                fullName.isEnabled = true
                btnSaveData.visibility = View.VISIBLE

        }

        btnBranch.setOnClickListener {
            branch.isEnabled = true
            btnSaveData.visibility = View.VISIBLE

        }

        btnYear.setOnClickListener {
            year.isEnabled = true
            btnSaveData.visibility = View.VISIBLE

        }


        btnRoomNo.setOnClickListener {
            roomNo.isEnabled = true
            btnSaveData.visibility = View.VISIBLE

        }

        btnFullName.setOnClickListener {
            fullName.isEnabled = true
            btnSaveData.visibility = View.VISIBLE

        }

        btnFloor.setOnClickListener {
            floor.isEnabled = true
            btnSaveData.visibility = View.VISIBLE

        }

        btnBlock.setOnClickListener {
            block.isEnabled = true
            btnSaveData.visibility = View.VISIBLE

        }


        btnSaveData.setOnClickListener {



            if (fullName.text!!.isEmpty() ||
                branch.text!!.isEmpty() ||
                year.text!!.isEmpty() ||
                mobileNo.text!!.isEmpty() ||
                gender.text.isEmpty() ||
                hostel.text.isEmpty() ||
                roomNo.text!!.isEmpty() ||
                floor.text!!.isEmpty() ||
                block.text!!.isEmpty()
            ){

                Toast.makeText(requireActivity(), "Enter all details", Toast.LENGTH_SHORT).show()
            }

            else {


                var sharedPref =
                    requireActivity().getSharedPreferences("myPrefs", Context.MODE_PRIVATE)

                val id = sharedPref.getString("_id", "");


                val studentInfoData = StudentInfoData(
                    fullName = fullName.text.toString(),
                    branch = branch.text.toString(),
                    year = year.text.toString(),
                    mobileNo = mobileNo.text.toString(),
                    gender = gender.text.toString(),
                    hostel = hostel.text.toString(),
                    roomNo = roomNo.text.toString(),
                    floor = floor.text.toString(),
                    block = block.text.toString(),
                    id = id.toString()
                )


                dialog = ProgressDialog.show(requireActivity(), "", "Please wait...", true);

                val response = RetrofitHelper.buildService(RetrofitInterface::class.java)
                response.studentInfo(studentInfoData).enqueue(
                    object : Callback<StudentInfoResult> {
                        override fun onResponse(
                            call: Call<StudentInfoResult>,
                            response: Response<StudentInfoResult>
                        ) {


                            dialog?.dismiss()

                            if (response.code() == 200) {

                                val editor = sharedPref.edit()
                                editor.putBoolean("isStudentDetail", true)
                                editor.putString("fullName", studentInfoData.fullName.toString())
                                editor.putString("branch", studentInfoData.branch.toString())
                                editor.putString("year", studentInfoData.year.toString())
                                editor.putString("mobileNo", studentInfoData.mobileNo.toString())
                                editor.putString("gender", studentInfoData.gender.toString())
                                editor.putString("hostel", studentInfoData.hostel.toString())
                                editor.putString("roomNo", studentInfoData.roomNo.toString())
                                editor.putString("floor", studentInfoData.floor.toString())
                                editor.putString("block", studentInfoData.block.toString())

                                editor.apply()


                                btnSaveData.visibility =View.GONE


                                Toast.makeText(
                                    requireActivity(),
                                    "Data updated successfully",
                                    Toast.LENGTH_LONG
                                ).show()


                            } else {

                                Toast.makeText(
                                    requireActivity(),
                                    "Something went wrong",
                                    Toast.LENGTH_LONG
                                ).show()

                            }

                        }

                        override fun onFailure(call: Call<StudentInfoResult>, t: Throwable) {
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
}