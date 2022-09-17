const express = require("express");
const router = express.Router();
const Api_Controller = require("../controller/apicontroller");
// const jwtAuth = require("../lib/jwtAuth");
router.post("/studentsignup", Api_Controller.studentsignup);
router.post("/studentprofile",Api_Controller.studentprofile);
router.post("/studentlogin",Api_Controller.studentlogin);
router.post("/workersignup", Api_Controller.workersignup);
router.post("/workerprofile",Api_Controller.workerprofile);
router.post("/workerlogin",Api_Controller.workerlogin);
router.post("/postissue",Api_Controller.postissues);
router.get("/getissue",Api_Controller.getissues);
// router.post("/studentlogin",Api_Controller.studentlogin);
// router.post("/")
module.exports = router;