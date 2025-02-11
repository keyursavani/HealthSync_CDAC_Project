import axios from 'axios';
// let baseUrl ="http://localhost:8082/doctor";
// let baseUrl2 = "http://localhost:8083/medicalrecord/";
let baseUrl ="http://localhost:8181/doctor";
let baseUrl2 = "http://localhost:8181/medicalrecord/";


class DoctorService{

   doctorSignup(p){
      return axios.post(baseUrl+"/signup",p);
   }

   doctorSignin(p){
      return axios.post(baseUrl+"/signin",p);
   }

   doctorSignout(p){
      return axios.post(baseUrl+"/signout",p);
   }

   doctorRecords(doctorId,page,size,authToken){
      let myheaders={
         'Authorization':`Bearer ${authToken}`,
         'content-Type':'application/json'}
      return axios.get(baseUrl2+"doctor/"+doctorId+"?page="+page+"&size="+size,{headers:myheaders});
   }


   addmedicalrecord(file,medicalrecord,authToken){
      const formData = new FormData();
      if(file){
         formData.append("file", file);
      }
      formData.append(
          "medicalRecord",
          new Blob([JSON.stringify(medicalrecord)], { type: "application/json" })
      );
      let myheaders={
         'Authorization':`Bearer ${authToken}`,
         "Content-Type": "multipart/form-data",
      }
      return axios.post(baseUrl2+"add",formData,{headers:myheaders});
   }


   updateMedicalRecord(file,medicalrecord,authToken,rid){
      const formData = new FormData();
      if(file){
         formData.append("file", file);
      }
      formData.append(
          "medicalRecord",
          new Blob([JSON.stringify(medicalrecord)], { type: "application/json" })
      );
      let myheaders={
         'Authorization':`Bearer ${authToken}`,
         "Content-Type": "multipart/form-data",
      }
      return axios.patch(baseUrl2+"update/"+rid,formData,{headers:myheaders});
   }

   deletemedicalrecord(p,recordId,authToken){
      console.log(p);
      let myheaders={
         'Authorization':`Bearer ${authToken}`,
         'content-Type':'application/json'}
      return axios.delete(baseUrl2+"delete/"+recordId,{data:p,headers:myheaders});
   }

  
}

export default new DoctorService();