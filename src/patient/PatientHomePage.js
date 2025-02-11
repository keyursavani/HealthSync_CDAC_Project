import { useEffect,useState } from "react";
import PatientNavbar from "../component/PatientNavbar";
import { useNavigate } from "react-router-dom";

export default function PatientHomePage(){
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

   useEffect(() => {
    if(user){
    }
  },[user]);
     
   return (
      <div>
        <PatientNavbar></PatientNavbar>
      </div>
   );
}