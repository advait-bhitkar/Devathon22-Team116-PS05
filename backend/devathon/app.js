const express = require("express");
const bodyParser = require("body-parser");
const mongoose = require("mongoose");
const cors = require("cors");
const fs = require("fs");
const app = express();
const port = 4444;
const dburl="mongodb+srv://pavi:x4HczVNr29dBJ4jn@cluster0.lltaiyz.mongodb.net/?retryWrites=true&w=majority";
mongoose
  .connect(dburl, {
    useNewUrlParser: true,
    useUnifiedTopology: true
  })
  .then((res) => console.log("Connected to Mongo DB"))
  .catch((err) => console.log(err));

app.use(bodyParser.json()); 
app.use(bodyParser.urlencoded({ extended: true })); 

app.use(cors());
app.use(express.json());

app.use("/api", require("./routes/apiRoutes"));
app.listen(port, () => {
  console.log(`Server started on port ${port}!`);
});
