import './App.css';
import { Navigate, Route, Routes} from 'react-router-dom';
import WelcomePage from './pages/WelcomePage';
import PatientSignup from './patient/PatientSignup';
import PatientSignin from './patient/PatientSignin';
import PatientHomePage from './patient/PatientHomePage';
// import PatientRecords from './patient/PatientRecords';
import DoctorSignup from './doctor/DoctorSignup';
import DoctorSignin from './doctor/DoctorSignin';
import DoctorHome from './doctor/DoctorHome';
import GetMyRecords from './patient/GetMyRecords';
import GetDoctorRecord from "./doctor/GetMyRecords"
import ReferPatient from './doctor/ReferPatient';
import EditMedicalRecord from './doctor/EditMedicalRecord';
import MyImage from './component/MyImage';

function App() {
  return (
    <div>
      <Routes>
        <Route path="/" element={<Navigate replace to = "/index"></Navigate>}></Route>
        <Route path='/index' element={<WelcomePage></WelcomePage>}></Route>
        <Route path='/patient'>
           <Route path='signup' element={<PatientSignup></PatientSignup>}></Route>
           <Route path='signin' element={<PatientSignin></PatientSignin>}></Route>
           <Route path='home' element={<PatientHomePage></PatientHomePage>}></Route>
           <Route path='records' element={<GetMyRecords></GetMyRecords>}></Route>
        </Route>
        <Route path='/doctor'>
           <Route path='signup' element={<DoctorSignup></DoctorSignup>}></Route>
           <Route path='signin' element={<DoctorSignin></DoctorSignin>}></Route>
           <Route path='home' element={<DoctorHome></DoctorHome>}></Route>
           <Route path='records' element={<GetDoctorRecord></GetDoctorRecord>}></Route>
           <Route path='refer' element={<ReferPatient></ReferPatient>}></Route>
        </Route>
           <Route path='/edit/:rid' element={<EditMedicalRecord></EditMedicalRecord>}></Route>
           <Route path='/image' element={<MyImage></MyImage>}></Route>
      </Routes>
    </div>
  );
}

export default App;
