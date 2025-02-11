import { useEffect,useState } from "react";
import { useNavigate } from "react-router-dom";
import DoctorNavbar from "../component/DoctorNavbar";

export default function DoctorHome(){
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
   
    return (
       <div>
         <DoctorNavbar></DoctorNavbar>
        
       </div>
    );
 }

