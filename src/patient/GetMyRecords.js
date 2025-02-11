import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import PatientService from "../service/PatientService";
import MedicalRecord from "./MedicalRecord";

export default function GetMyRecords(){

   const [user, setUser] = useState(null);
   const navigate = useNavigate();
   const [authToken, setauthToken] = useState(null);
   const [medicalrecordarr, setmedicalrecordarr] = useState([]);  

    useEffect(() => {
        const storedUser = localStorage.getItem("user");
        const token = localStorage.getItem("authToken");
        if(token){
         setauthToken(token);
        }
        if (storedUser) {
            setUser(JSON.parse(storedUser));
        }else{
          navigate("/index")
        }

    }, []);
 
    useEffect(() => {
     if(user){
      fetchdata();
     }
   },[user]);

const fetchdata = async () => {
    try {
        const result = await PatientService.patientRecords(user.id, 0, 20, authToken);
        setmedicalrecordarr(result.data.body.medicalRecords);
    } catch (error) {
        console.error("Error fetching medical records:", error);
    }
};

useEffect(() => {
}, [medicalrecordarr]); 

   return(
      <div className="container">
      <div className='row'>
          {medicalrecordarr.map((element,index) => {
              return <div className="col-md-4" key={element.id}>
               <MedicalRecord obj={element}></MedicalRecord>
              </div>
          })}
      </div>
  </div>
   )
}