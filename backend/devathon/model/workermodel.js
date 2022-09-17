const mongoose=require("mongoose");
const schema=new mongoose.Schema(
    {
        name:{
            type: String,
            required: true,
        },
        password:{
            type: String,
            unique: true,
            required: true,
        },
        empid:{
            type: Number,
            required:true,
        },
        email:{
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
        mobile:{
            type: Number,
            required: true,
        },
        role:{
            type: String,
            enum: ["cleaner","electrician","carpenter","plumber"],
            required: true,
        },
        specification:{
            type: String,
        },
        gender:{
            type: String,
            enum: ["female","male","other"],
        },
        hostel:{
            type:String,
            enum: ["NEW LH", "Priyadarshini", "Sarojini", "1.8k","1k","ISH","Blocks"],
            required: true,
        },
        block_No:{
            type: Number,
        },
        assigned_works:{
            type: Number,
        },
        completed_works:{
            type: Number,
        }
    }
)
module.exports = mongoose.model("Worker", schema);