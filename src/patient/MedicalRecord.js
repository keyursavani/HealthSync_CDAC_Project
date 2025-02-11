import { Link } from "react-router-dom";

export default function MedicalRecord(props) {
    return (
        <div className='my-3'>
            <div className="card">
                <div className="card-body">
                    <h6 className="card-title">Doctor Deatils</h6>
                    <p className="card-text"> Name : {props.obj.doctorDetails.firstName+ " " + props.obj.doctorDetails.lastName} </p>
                    <p className="card-text"> Specialization : {props.obj.doctorDetails.specialization} </p>
                    <p className="card-text"> ContactNumber : {props.obj.doctorDetails.contactNumber}</p>
                    <p className="card-text"> Email : {props.obj.doctorDetails.email}</p>
                    <h6 className="card-title">Medical Deatils</h6>
                    <p className="card-text"> Prescription : {props.obj.prescription}</p>
                    <p className='card-text'><small className='text-muted'>Date : {props.obj.date}</small></p>
                    {props.obj.imageName ? (
                        <Link to="/image" state={{ robj: props.obj }} className="card-link">
                            Image
                        </Link>
                    ) : (
                        <div>No Image Available</div>
                    )}
                </div>
            </div>
        </div>
    )
}