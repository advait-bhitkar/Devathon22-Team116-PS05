const mongoose=require("mongoose");
const schema=new mongoose.Schema(
    {
        student_id:{
            type: String,
            
        },
        name:{
            type:String,
        },
        regno:{
            type:Number,
        },
        number:{
            type:String,
        },
        issueType:{
            type:String,
        },
        required_person:{
            type: String,
        },
        specification:{
            type: String,
        },
        hostel:{
            type: String,
            enum: ["NEW LH", "Priyadarshini", "Sarojini", "1.8k","1k","ISH","Blocks"],
            required: true,
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
        },
        is_private:{
            type:String,
        }
    }
)
module.exports = mongoose.model("Issue", schema);