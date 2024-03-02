import { useState } from "react";
import { Link } from "react-router-dom";
import { toast } from "react-toastify";
import '../user/main.css'
//import config from '../../config'
import axios from "axios";
import { useNavigate } from "react-router-dom";


const Signup = () => {
  // get user inputs
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [email, setEmail] = useState("");
  const [mobileNo, setMobileNo] = useState("");
  const [password, setPassword] = useState("");
  const [userRole, setUserRole] = useState("USER");
  const [identity , setIdentityCard] = useState('');

  // this function is used to navigate from one component to another programmatically
  // userNavigate() returns a function reference
  const navigate = useNavigate();

  const signup =() => {
    // check if user has really entered any value
    
   /^[a-zA-Z]{}$/
    if (firstName.length === 0) {
      toast.error("please enter first name");
    } else if (lastName.length === 0) {
      toast.error("please enter last name");
    } else if (email.length === 0) {
      toast.error("please enter email");
    } else if (mobileNo.length === 0 || mobileNo.length>=11) {
      toast.error("please enter phone number");
    } else if (password.length === 0) {
      toast.error("please enter password");
    } else if(identity.length===0 || identity.length>=12){
      toast.error("Please Check Identity ")
    }
    else {
      // make the API call to check if user exists
       axios
        .post("http://localhost:9095/parkingUser/signUp", {
          firstName,
          lastName,
          email,
          identityCard:identity,
          password,
          mobileNo,
          userRole
        })
        .then((response) => {
          // get the data returned by server
          const result = response.data;

          // check if user's authentication is successfull
          if (result["status"] === "error") {
            toast.error("invalid email or password");
          } else {
            toast.success("successfully registered a new user");

            // navigate to the singin page
            navigate("/signIn");
          }
        })
        .catch((error) => {
          console.log("error");
          console.log(error);
        });
    }
  };

  return (
    <div className="login">
      <div >
        <div className="mb-3">
          <label>First Name</label>
          <input
            onChange={(event) => {
              setFirstName(event.target.value);
            }}
            className="form-control"
            type="text"
          />
        </div>

        <div className="mb-3">
          <label>Last Name</label>
          <input
            onChange={(event) => {
              setLastName(event.target.value);
            }}
            className="form-control"
            type="text"
          />
        </div>

        <div className="mb-3">
          <label>Mobile Number</label>
          <input
            onChange={(event) => {
              setMobileNo(event.target.value);
            }}
            className="form-control"
            type="tel"
          />
        </div>
        <div className="mb-3">
          <label> Identity </label>
          <input
            onChange={(event) => {
              setIdentityCard(event.target.value);
            }}
            className="form-control"
            type="identityCard"
          />
        </div>

        <div className="mb-3">
          <label>Email</label>
          <input
            onChange={(event) => {
              setEmail(event.target.value);
            }}
            className="form-control"
            type="email"
          />
        </div>

        <div className="mb-3">
          <label>Password</label>
          <input
            onChange={(event) => {
              setPassword(event.target.value);
            }}
            className="form-control"
            type="password"
          />
        </div>

        <div className="mb-3" >
          <div>
            Already have an account? <Link to="/signin">Signin here</Link>
          </div>
          <button onClick={signup} >
            Signup
          </button>
        </div>
      </div>
    </div>
  );
};


export default Signup;
