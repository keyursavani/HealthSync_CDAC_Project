// 	private long doctorId;
// 	private String firstName;
// 	private String lastName;
// 	 private String specialization;
// 	private long contactNumber;
// 	private String email;
// 	private String address;
// 	private String password;

import { useState } from "react";
import { useNavigate } from "react-router-dom";
import DoctorService from "../service/DoctorService";

export default function DoctorSignup() {
  const [formdetails, setformdetails] = useState({ doctorId: '', firstName: '', lastName: '', specialization: '', contactNumber: '', email: '', address: '', password: '' })
  const navigate = useNavigate();

  const handlechange = (event) => {
    const name = event.target.name;
    const value = event.target.value;
    setformdetails({ ...formdetails, [name]: value });
  }

  const updatedoctor = () => {
    if (formdetails.doctorId === "" || formdetails.firstName === "" || formdetails.specialization === "" || formdetails.contactNumber === "" || formdetails.email === "" || formdetails.address === "" || formdetails.password === "") {
      alert("Please fill all details")
    } else {
      let p = { doctorId: parseInt(formdetails.doctorId), firstName: formdetails.firstName, lastName: formdetails.lastName, specialization: formdetails.specialization, contactNumber: parseInt(formdetails.contactNumber), email: formdetails.email, address: formdetails.address, password: formdetails.password };
      DoctorService.doctorSignup(p)
        .then((result) => {
          if (result.data.statusCode === 201 && result.data.message === "Success") {
            navigate("/doctor/signin");
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
                  <label htmlFor="doctorId">Doctor Id</label>
                  <input type="number" className="form-control" id="doctorId" name='doctorId'
                    value={formdetails.doctorId} onChange={handlechange} />
                </div>
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
                  <label htmlFor="specialization">Specialization</label>
                  <input type="text" className="form-control" id="specialization" name='specialization'
                    value={formdetails.specialization} onChange={handlechange} />
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
                <button type="button" className="btn btn-primary" onClick={updatedoctor}>Signup</button>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}