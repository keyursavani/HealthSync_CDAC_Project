import { Link } from "react-router-dom";

export default function MedicalRecord(props) {

    const deleteRecord = (recordid) => {
        props.deleterecord(recordid);
    }

    return (
        <div className='my-3'>
            <div className="card">
                <div className="card-body">
                    <h6 className="card-title">Patient Deatils</h6>
                    <p className="card-text"> Name : {props.obj.patientDetails.firstName + " " + props.obj.patientDetails.lastName} </p>
                    <p className="card-text"> ContactNumber : {props.obj.patientDetails.contactNumber}</p>
                    <p className="card-text"> Email : {props.obj.patientDetails.email}</p>
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
                    <br />
                    {/* ebdm ftqv bvek jbjc */}
                    <br />
                    <Link to={`/edit/${props.obj.id}`} state={{ robj: props.obj }}>
                        <button className='btn btn-primary' type='button' name='edit' id='edit'
                            value="edit">edit</button>&nbsp;&nbsp;&nbsp;
                    </Link>
                    <button className='btn btn-danger' type='button' name='detele' id='detele'
                        onClick={() => { deleteRecord(props.obj.id) }}
                        value="detele">detele</button>&nbsp;&nbsp;&nbsp;
                </div>
            </div>
        </div>
    )
}