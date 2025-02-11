// private String firstName;
// private String lastName;
// private String dob;
// private Gender geneder;
// private long contactNumber;
// private String email;
// private String address;
// private String password;

import { useState } from "react";
import PatientService from "../service/PatientService";
import { useNavigate } from "react-router-dom";

export default function PatientSignup() {
  const [formdetails, setformdetails] = useState({ firstName: '', lastName: '', dob: '', geneder: '', contactNumber: '', email: '', address: '', password: '' })
  const navigate = useNavigate();

  const handlechange = (event) => {
    const name = event.target.name;
    const value = event.target.value;
    setformdetails({ ...formdetails, [name]: value });
  }

  const updatepatient = () => {
    if (formdetails.firstName === "" || formdetails.dob === "" || formdetails.geneder === "" || formdetails.contactNumber === "" || formdetails.email === "" || formdetails.address === "" || formdetails.password === "") {
      alert("Please fill all details")
    } else {
      let p = { firstName: formdetails.firstName, lastName: formdetails.lastName, dob: formdetails.dob, geneder: formdetails.geneder, contactNumber: parseInt(formdetails.contactNumber), email: formdetails.email, address: formdetails.address, password: formdetails.password };
      PatientService.patientSignup(p)
        .then((result) => {
          if (result.data.statusCode === 201 && result.data.messags === "Success") {
            navigate("/patient/signin");
          }
        }).catch((err) => {
          console.log(err);
        })
    }
  }
  return (
    <div className="container" style={{ paddingTop: "50px", paddingBottom: "50px" }}>
      <div className="row justify-content-md-center">
        <div className="col col-lg-8">
          <div className="card border-secondary lg-8">
            <div className="card-body text-secondary">
              <form>
                <div className="form-group">
                  <label htmlFor="firstName">First Name</label>
                  <input type="text" className="form-control" id="firstName" name='firstName'
                    value={formdetails.firstName} onChange={handlechange} />
                </div>
                <div className="form-group">
                  <label htmlFor="lastName">Last Name</label>
                  <input type="text" className="form-control" id="lastName" name='lastName'
                    value={formdetails.lastName} onChange={handlechange} />
                </div>
                <div className="form-group">
                  <label htmlFor="dob">DOB</label>
                  <input type="date" className="form-control" id="dob" name='dob'
                    value={formdetails.dob} onChange={handlechange} />
                </div>
                <div className="form-group">
                  <label >Gender</label>
                  <br></br>
                  <input type="radio" name="geneder" id="male" value="MALE" onChange={handlechange} />   <span></span>  <span></span>
                  <label htmlFor="male">Male</label>
                  <span></span>  <span></span>
                  <input type="radio" name="geneder" id="female" value="FEMALE" onChange={handlechange} />  <span></span>  <span></span>
                  <label htmlFor="female">Female</label>
                </div>
                <div className="form-group">
                  <label htmlFor="contactNumber">Contact Number</label>
                  <input type="number" className="form-control" id="contactNumber" name='contactNumber'
                    value={formdetails.contactNumber} onChange={handlechange} />
                </div>
                <div className="form-group">
                  <label htmlFor="email">Email</label>
                  <input type="email" className="form-control" id="email" name='email'
                    value={formdetails.email} onChange={handlechange} />
                </div>
                <div className="form-group">
                  <label htmlFor="address">Address</label>
                  <textarea className="form-control" id="address" name='address'
                    value={formdetails.address} onChange={handlechange}> </textarea>
                </div>
                <div className="form-group">
                  <label htmlFor="password">Password</label>
                  <input type="password" className="form-control" id="password" name='password'
                    value={formdetails.password} onChange={handlechange} />
                </div>
                <button type="button" className="btn btn-primary" onClick={updatepatient}>Signup</button>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}