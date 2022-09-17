const express = require("express");
const router = express.Router();
const Api_Controller = require("../controller/apicontroller");
router.post("/studentsignup", Api_Controller.studentsignUp);
// router.post("/student/profile",Api_Controller.studentprofile);
// router.post("/studentlogin",Api_Controller.studentlogin);
// router.post("/")
module.exports = router;