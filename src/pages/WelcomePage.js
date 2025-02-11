import { NavLink, useNavigate } from "react-router-dom";
import { useEffect,useState } from "react";
export default function WelcomePage() {
  
   const navigate = useNavigate();
   const [user, setUser] = useState(null);

  useEffect(() => {
      const storedUser = localStorage.getItem("user");
      if (storedUser) {
        setUser(JSON.parse(storedUser));
      }
  }, []);


  useEffect(() => {
    if(user){
    if(user.role === "PATIENT"){
      navigate("/patient/home");
    }
    if(user.role === "DOCTOR"){
     navigate("/doctor/home");
    }
  }
  },[user]);
 

  return (
    <div>
      <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
        <NavLink className="navbar-brand" to="/index">HealthSync</NavLink>
        <div className="collapse navbar-collapse" id="navbarNav">
        </div>
        <div className="d-flex" >
          <NavLink className="navbar-brand" to="/patient/signin">Signin as a Patient</NavLink>
          <NavLink className="navbar-brand" to="/doctor/signin">Signin as a Doctor</NavLink>
          <NavLink className="navbar-brand" to="/patient/signup">Signup as a Patient</NavLink>
          <NavLink className="navbar-brand" to="/doctor/signup">Signup as a Doctor</NavLink>
        </div>
      </nav>
     {/* <div style={{backgroundImage:{img}, height:"1000px"}}>Hello</div> */}
    </div>
  )
}