import { NavLink } from "react-router-dom";
import { useEffect,useState } from "react";
import { useNavigate } from "react-router-dom";
import DoctorService from "../service/DoctorService";

export default function DoctorNavbar(){

      const [user, setUser] = useState(null);
      const navigate = useNavigate();
      
       useEffect(() => {
           const storedUser = localStorage.getItem("user");
           if (storedUser) {
               setUser(JSON.parse(storedUser));
           }else{
             navigate("/index")
           }
       }, []);
   
      const signout = () =>{
         let p = {email:user.email}
           DoctorService.doctorSignout(p)
           .then((result)=>{
               if(result.data.statusCode === 200 && result.data.message === "Success"){
                  localStorage.removeItem("user");
                  localStorage.removeItem("authToken");
                  navigate("/index");
               }
           }).catch((e)=>{
            console.log(e);
            alert(e.response.data.message);
            navigate("/index");
           });
      }
   return(
      <div>
         <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
            <NavLink className="navbar-brand" to="/doctor/home">HealthSync</NavLink>
            <div className="collapse navbar-collapse" id="navbarNav">
            </div>
            <div className="d-flex flex-column">
            </div>
            <div className="d-flex">
               <NavLink className="navbar-brand" to="/doctor/refer">Refer a patient</NavLink>
               <NavLink className="navbar-brand" to="/doctor/records">Get my records</NavLink>
               <button type="button" className="btn btn-warning" onClick={signout}>Signout</button>
            </div>
         </nav>
      </div>
   )
}