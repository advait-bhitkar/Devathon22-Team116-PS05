const mongoose=require("mongoose");
const schema=new mongoose.Schema(
    {
        student_id:{
            type: String,
            
        },
        required_person:{
            type: String,
            enum: ["cleaner","electrician","carpenter","plumber"],
        },
        specification:{
            type: String,
            enum:["lights","fan","tap leakage","water blockage","room"],
        },
        hostel:{
            type: String,
            enum: ["NEW LH", "Priyadarshini", "Sarojini", "1.8k","1k","ISH","Blocks"],
            required: true,
        },
        block_No:{
            type: Number,
        },
        floor:{
            type: Number,

        },
        room_No:{
            type: Number,
            required: true,
        },
        upvotes:{
            type: Number,
        },
        description:{
            type: String,
        },
        status:{
            type: String,
            enum:["resolved","resolving","pending"],

        },
        preferred_timings:{
            type: String,
        }
    }
)
module.exports = mongoose.model("Issue", schema);