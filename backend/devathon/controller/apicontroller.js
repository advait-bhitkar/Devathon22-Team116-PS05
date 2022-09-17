const { sign } = require("crypto");
const express=require("express");
const router=express.Router();
const User=require("../model/usermodel");
const Worker=require("../model/workermodel");
const Issue=require("../model/issuesmodel");
const studentsignup = (req, res) => {
        const data = req.body;
        let user = new User({
          // name: data.name,
          // gender: data.gender,
          email: data.email,
          password: data.password,
          regno: data.regno,
          
        });


        let index=data.email.search(/nitw.ac.in/);
        var flag=0;
        if(index<0){
                        res.status(201).send({msg:"enter valid email",id:"0000"});
                    }
        else{ 
      
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
              res.status(200).json({msg:"enter valid email"});
            });
          }
        }
        const workersignup=(req,res)=>{
          const data = req.body;
          let worker = new Worker({
            password: data.password,
            email: data.email,
            assigned_works:0,
            completed_works:0,
          });
        
          let index=data.email.search(/nitw.ac.in/);
                            if(index<0){
                                res.status(201).send({msg:"enter valid email",id:"0000"});
                            }
                else{ 
              
                worker
                  .save()
                  .then((err,result) => {
                    
                        res.status(200).json({"msg":"successful", id:worker._id.toString()
                      // id:result.insertedId
                      });
                    })
                  .catch((err) => {
                    res.status(200).json({msg:"enter valid email"});
                  });
                }
        }
const studentprofile=(req,res)=>{
    const data=req.body;
    // const obj=User.find({_id:data.id});
    console.log(data.id+' id');
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
    .then((err,result)=>{
      res.status(200).json({msg:"enter valid email"});
      
    })
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
const studentlogin=(res,req)=>{
    const data=req.body;
    const user=User.find({email:data.email});
    if(data.password!=user.password){
      res.status(201).json({msg:"incorrect password",status:201});
    }
    else{
      res.status(200).json({msg:"logged in successfully",id:user._id.toString()});
    }
}
const workerlogin=(res,req)=>{
  const data=req.body;
    const worker=Worker.find({email:data.email});
    if(data.password!=worker.password){
      res.status(201).json({msg:"incorrect password",status:201});
    }
    else{
      res.status(200).json({msg:"logged in successfully",status:200});
    }
}
const postissues=(req,res)=>{
  const data=req.body;
  // var array=[];
  // console.log("enetred");
  // Issue.find({$and:[{"specification": data.specification}, {"hostel": data.hostel},{"floor":data.floor}]}),(function(err, result) {
  //   console.log(result);
  //   result.forEach(function(data) {
  //     array.push(data); });
  //   console.log(result);
  //   if(array.length!=0){
  //     res.status(200).json({msg:"complaint already raised"});
  //   }
  //   else{
  //     console.log(data);
  //         let issue=new Issue({
  //             student_id: data.id,
  //             required_person:data.required_person,
  //             specification:data.specification,
  //             hostel:data.hostel,
  //             block_No:data.block_No,
  //             floor:data.floor,
  //             upvotes:0,
  //             description:data.description,
  //             status:data.status,
  //             preferred_timings:data.preferred_timings
  //         });
  //         issue
  //             .save()
  //             .then((err,result) => {
  //               // var userId = User.findOne({ email:result.email});
  //               // userId._id
  //                   res.status(200).json({"msg":"successful", id:issue._id.toString()});
  //               })
  //               .catch((err) => {
  //                 console.log(err);
  //                 res.status(200).json({msg:"some error",status:200});
  //               });
  //   }
  // });
  // var array = [];
  // Issue.find({}, function(err, result) {
  //   if (err) {
  //       console.log(err);
  //   } else {
  //       result.forEach(function(data) {
  //           array.push(data);
  //       });
  //   }})

  //     if(Issue.find({ "$and": [{"specification": data.specification}, {"hostel": data.hostel},{"floor":data.floor}]})){
  //       console.log(Issue.find({ $and: [{"specification": data.specification}, {"hostel": data.hostel},{"floor":data.floor}]}));
  //       res.status(200).json({msg:"complaint already raised"});
  //     }
  //     else{
        // console.log(data);
          let issue=new Issue({
              student_id: data.id,
              name:data.name,
              regno:data.regno,
              issueType:data.issueType,
              required_person:data.required_person,
              specification:data.specification,
              hostel:data.hostel,
              block_no:data.block_no,
              floor:data.floor,
              room_no:data.room_no,
              upvotes:0,
              is_private:data.is_private,
              number:data.number,
              description:data.description,
              status:data.status,
              preferred_timings:data.preferred_timings
          });
          issue
              .save()
              .then((err,result) => {
                // var userId = User.findOne({ email:result.email});
                // userId._id

                    res.status(200).json({"msg":"successful", id:issue._id.toString()});
                })
                .catch((err) => {
                  console.log(err);
                  res.status(200).json({msg:"some error",status:200});
                });
  //     }
}
const getissues=(req,res)=>{
  var body = req.body;
  var array = [];
  Issue.find({}, function(err, result) {
    if (err) {
        console.log(err);
    } else {
        result.forEach(function(data) {
            array.push(data);
        });
    }
    res.send(array);
});
}



const Api_Controller = { studentsignup,workersignup,studentlogin,workerlogin,workerprofile,studentprofile,postissues,getissues};
module.exports = Api_Controller;