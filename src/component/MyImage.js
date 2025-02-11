import { useEffect } from "react";
import { useLocation } from "react-router-dom"

export default function MyImage(){
   const location = useLocation();
  const imagepath = "./patientImage/"+location.state.robj.imageName;
   return (
      <div>
          <img src={imagepath} alt="profile" width="80%" height="80%" />
      </div>
   )
}