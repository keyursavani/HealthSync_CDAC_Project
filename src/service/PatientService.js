import axios from 'axios';
// let baseUrl="http://localhost:8082/patient";
// let baseUrl2 = "http://localhost:8083/medicalrecord/";
let baseUrl="http://localhost:8181/patient";
let baseUrl2 = "http://localhost:8181/medicalrecord/";

class PatientService{
 
patientSignup(p){
   // let myheaders={'content-Type':'application/json'}
   return axios.post(baseUrl+"/signup",p);
}

patientSignin(p){
      let myheaders={'content-Type':'application/json'}
   return axios.post(baseUrl+"/signin",p,{headers:myheaders});
}

patientSignout(p){
   return axios.post(baseUrl+"/signout",p)
}

patientRecords(patientId,page,size,authToken){
   let myheaders={
      'Authorization':`Bearer ${authToken}`,
      'content-Type':'application/json'}
   return axios.get(baseUrl2+"patient/"+patientId+"?page="+page+"&size="+size,{headers:myheaders});
}

updateemployee(p,empno){
   let myheaders={'content-Type':'application/json'}
   return axios.put(baseUrl+"employees/"+empno,p,{headers:myheaders})
}

deleteById(empno){
   return axios.delete(baseUrl+"employees/"+empno)
}

getById(empno){
   return axios.get(baseUrl+"employees/"+empno)
}

}

export default new PatientService();