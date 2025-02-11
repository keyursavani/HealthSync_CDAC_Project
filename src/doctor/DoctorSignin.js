import { useState } from "react"
import DoctorService from "../service/DoctorService";
import { useNavigate } from "react-router-dom";

export default function DoctorSignin() {
  const [formdetails, setformdetails] = useState({ email: '', password: '' });

  const navigate = useNavigate();

  const handlechange = (event) => {
    const name = event.target.name;
    const value = event.target.value;
    setformdetails({ ...formdetails, [name]: value });
  }

  const updatedoctor = () => {
    if (formdetails.email === "" || formdetails.password === "") {
      alert("Please fill all details")
    } else {
      let p = { email: formdetails.email, password: formdetails.password };
      DoctorService.doctorSignin(p)
        .then((result) => {
          if (result.data.statusCode === 200 && result.data.message === "Success") {
            localStorage.setItem("user", JSON.stringify(result.data.body));
            localStorage.setItem("authToken", result.data.body.authToken);
            navigate("/doctor/home");
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
          <div class="card border-secondary lg-8">
            <div class="card-body text-secondary">
              <div className="form-group">
                <label htmlFor="email">Email</label>
                <input type="email" className="form-control" id="email" name='email'
                  value={formdetails.email} onChange={handlechange} />
              </div>
              <div className="form-group">
                <label htmlFor="password">Password</label>
                <input type="password" className="form-control" id="password" name='password'
                  value={formdetails.password} onChange={handlechange} />
              </div>
              <button type="button" className="btn btn-primary" onClick={updatedoctor}>Signin</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}