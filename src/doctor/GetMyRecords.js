import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import MedicalRecord from "./MedicalRecord";
import DoctorService from "../service/DoctorService";

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
        const result = await DoctorService.doctorRecords(user.id, 0, 20, authToken);
        setmedicalrecordarr(result.data.body.medicalRecords);
    } catch (error) {
        console.error("Error fetching medical records:", error);
    }
};

 const deleterecord = (recordid) => {
        let p = { doctorId: user.id};
        console.log(p);
        DoctorService.deletemedicalrecord(p,recordid,authToken)
          .then((result) => {
            if (result.data.statusCode === 200 && result.data.message === "Success") {
               fetchdata();
              }
          })
          .catch((err) => {
            console.log(err)
          });
      }

useEffect(() => {
}, [medicalrecordarr]); 

   return(
      <div className="container">
      <div className='row'>
          {medicalrecordarr.map((element,index) => {
              return <div className="col-md-4" key={element.id}>
               <MedicalRecord obj={element} deleterecord={deleterecord} ></MedicalRecord>
              </div>
          })}
      </div>
  </div>
   )
}