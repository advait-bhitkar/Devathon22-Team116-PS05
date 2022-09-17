const mongoose=require("mongoose");
const bcrypt = require("bcrypt");
const schema=new mongoose.Schema({
    email:{
        type: String,
        required: true
    },
    password:{
        type:String,
        required:true,
    },
    type_of_user:{
        type:String,
        enum:["student","worker"]
    }
}
);
schema.pre("save", function (next) {
    let user = this;
  
    // if the data is not modified
    if (!user.isModified("password")) {
      return next();
    }
  
    bcrypt.hash(user.password, 10, (err, hash) => {
      if (err) {
        return next(err);
      }
      user.password = hash;
      next();
    });
  });
  
  // Password verification upon login
  schema.methods.login = function (password) {
    let user = this;
  
    return new Promise((resolve, reject) => {
      bcrypt.compare(password, user.password, (err, result) => {
        if (err) {
          reject(err);
        }
        if (result) {
          resolve();
        } else {
          reject();
        }
      });
    });
  };
  module.exports=mongoose.model("defaultuser",schema);