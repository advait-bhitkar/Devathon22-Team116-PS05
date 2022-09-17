const mongoose=require("mongoose")
const { stringify } = require("querystring")
let schema=new mongoose.Schema(
    {
        name: {
            type: String,
            // required: true,
        },
        password:{
            type: String,
            unique: true,
            required: true,
        },
        gender:{
            type:String,
            enum:["female","male","other"],
        },
        email: {
            type: String,
            unique: true,
            required: true,
            validate: {
                validator: function (v) {
                    let index=v.search(/nitw.ac.in/);
                    if(index<0){
                        throw new Error("Email is invalid!");
                    } 
                },
                msg: "Email is invalid!",
            },
        },
        regno: {
            type:Number,
            // required:true,
        },
        rollno: {
            type: String,
            // required: true,
        },
        mobileno: {
            type: Number,
            // required: true,
        },
        branch: {
            type: String,
            // required: true,
        },

        year: {
            type: Number,
            // required: true
        },
        hostel:{
            type: String,
            enum: ["NEW LH", "Priyadarshini", "Sarojini", "1.8k","1k","ISH","Blocks"],
            // required: true,
        },
        block_no:{
            type: Number,
        },
        floor:{
            type: Number,

        },
        room_no:{
            type: Number,
            // required: true,
        }
    }
)
module.exports = mongoose.model("User", schema);