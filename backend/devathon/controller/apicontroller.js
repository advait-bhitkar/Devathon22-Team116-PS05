const { sign } = require("crypto");
const express=require("express");
const router=express.Router();
const User=require("../model/usermodel");
const Worker=require("../model/workermodel")
const studentsignUp = (req, res) => {
        const data = req.body;
        let user = new User({
          // name: data.name,
          // gender: data.gender,
          email: data.email,
          password: data.password,
          regno: data.regno,
          // rollno: data.rollno,
          // mobileno: data.mobileno,
          // branch: data.branch,
          // year: data.year,
          // hostel: data.hostel,
          // block_no: data.block_no,
          // floor: data.floor,
          // room_no:data.room_no
        });
      
        user
          .save()
          .then((err,result) => {
            // var userId = User.findOne({ email:result.email});
            console.log(result);
            console.log(user);
            console.log(user._id.toString());
            // # access the id of the document
            // userId._id
                res.status(200).json({"msg":"successful", id:user._id.toString()
              // id:result.insertedId
              });
            })
          .catch((err) => {
            res.status(400).json({msg:"enter valid email"});
          });
}
const studentprofile=(req,res)=>{
    const data=req.body;
    const obj=User.find({_id:data.id});
    User.updateOne({_id:data.id},{$set: {
      name: data.name,
          gender: data.gender,
      rollno: data.rollno,
      mobileno: data.mobileno,
      branch: data.branch,
      year: data.year,
      hostel: data.hostel,
      block_no: data.block_no,
      floor: data.floor,
      room_no:data.room_no
    } })
    
}
const workersignup=(req,res)=>{
  const data = req.body;
  let worker = new Worker({
    password: data.password,
    email: data.email,
    assigned_works:0,
    completed_works:0,
  });

  Worker
    .save()
    .then(() => {
        res;
      })
    .catch((err) => {
      res.status(400).json(err);
    });
}
const workerprofile=(req,res)=>{
      const data=req.body;
      const worker=Worker.updateOne({_id:data.id},{$set: {
        name:data.name,
        mobileno: data.mobileno,
        gender: data.gender,
        type:data.type,
        specification: data.specification
      }})
}
const studlogin=(res,req)=>{
    const data=req.body;
    const user=User.find({_id:data.id});
    if(data.password!=user.password){
      res.status(200).json({msg:"incorrect password"});
    }
    else{
      res.status(200).json({msg:"logged in successfully"});
    }
}
const workerlogin=(res,req)=>{
  const data=req.body;
    const worker=Worker.find({_id:data.id});
    if(data.password!=worker.password){
      res.status(200).json({msg:"incorrect password"});
    }
    else{
      res.status(200).json({msg:"logged in successfully"});
    }
}
const postissues=(res,req)=>{
  const data=req.body;
  if(User.find({ $and: [{type: data.type},{specification: data.specification}, {hostel: data.hostel}]})){
    res.status(200).json({msg:"complaint already raised"});
  }
  else{
    
    const issue=new Issue({

    })
  }
}
const Api_Controller = { studentsignUp,workersignup,studlogin,workerlogin,workerprofile,studentprofile};
module.exports = Api_Controller;