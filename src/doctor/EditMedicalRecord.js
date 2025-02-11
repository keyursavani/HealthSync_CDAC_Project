import { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import DoctorService from "../service/DoctorService";

export default function EditMedicalRecord(){
   const [formdetails, setformdetails] = useState({ doctorId: '', patientId: '', date: '', prescription: '' });
   const [file, setFile] = useState(null);
   const [user, setUser] = useState(null);
   const [authToken, setauthToken] = useState(null);
 
 
   const navigate = useNavigate();
   const location = useLocation();

   useEffect(()=>{
      setformdetails(prevState => ({
         ...location.state.robj, 
         patientId: location.state.robj.patientDetails.id
     })); 
   //   setFile(location.state.robj.image);
  },[]);
 
   useEffect(() => {
     const storedUser = localStorage.getItem("user");
     const token = localStorage.getItem("authToken");
         if(token){
          setauthToken(token);
         }
     if (storedUser) {
       setUser(JSON.parse(storedUser));
     } else {
       navigate("/index")
     }
   }, []);
 
   const handlechange = (event) => {
     const name = event.target.name;
     const value = event.target.value;
     setformdetails({ ...formdetails, [name]: value });
   }
 
   const handleFileChange = (event) => {
     setFile(event.target.files[0]);
   };
 
 
   const addrecord = () => {
     if (formdetails.patientId === "" || formdetails.date === "" || formdetails.prescription === "") {
       alert("Please fill all details")
     } else {
       let medicalrecord = { doctorId: user.id, patientId:formdetails.patientId, date: formdetails.date, prescription: formdetails.prescription };
       DoctorService.updateMedicalRecord(file,medicalrecord,authToken,location.state.robj.id)
         .then((result) => {
           if (result.data.statusCode === 200 && result.data.message === "Success") {
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
           <div className="card border-secondary lg-8">
             <div className="card-body text-secondary">
               <div className="form-group">
                 <label htmlFor="patientId">Patient Id</label>
                 <input type="number" className="form-control" id="patientId" name='patientId'
                   value={formdetails.patientId} onChange={handlechange} />
               </div>
               <div className="form-group">
                 <label htmlFor="date">Date</label>
                 <input type="date" className="form-control" id="date" name='date'
                   value={formdetails.date} onChange={handlechange} />
               </div>
               <div className="form-group">
                 <label htmlFor="prescription">Prescription</label>
                 <textarea rows={5} className="form-control" id="prescription" name='prescription'
                   value={formdetails.prescription} onChange={handlechange} />
               </div>
               <div className="form-group">
                 <label htmlFor="file">Photo</label>
                 <input type="file" className="form-control" id="file" name='file' onChange={handleFileChange} />
               </div>
               <button type="button" className="btn btn-primary" onClick={addrecord}>update</button>
             </div>
           </div>
         </div>
       </div>
     </div>
   )
}