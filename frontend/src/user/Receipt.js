import React, { useEffect,useState } from 'react';
import html2canvas from 'html2canvas';
import jsPDF from 'jspdf';
import Cookies from 'js-cookie';
import { getBookingCookie, getCookiesObject, getVehicleCookie } from './getCookieObject';
import { useNavigate } from 'react-router-dom';
import { isLoggedInCheck } from './isLoggedInCheck';
import axios from 'axios';
import { toast } from 'react-toastify';




const Receipt = () => {
     const navigate =useNavigate();
    const [getBookingId , setBookingId] =useState(0);
    const [getVehicleTypeId ,setVehicleTypeId] =useState(0);
     const [cookie , setCookie] = useState(getCookiesObject());
     const [vcookie , setVcookie] =useState(getVehicleCookie());
     const [bCookie , setBcookie] = useState(getBookingCookie());
     const [actualParking , setParking] = useState('');
     const [fare,setFare] = useState(0);
     const [amount,setAmount] =useState(0);
     const [timestamp, setTimestamp] = useState(new Date());
     const [duration,setDuration] = useState(0);

     useEffect(() => {
          isLoggedInCheck(navigate);
          console.log(getCookiesObject())
          console.log(getVehicleCookie());
          console.log(vcookie)
          setBookingId(bCookie.bookingId);
          setVehicleTypeId(vcookie.vehicleTypeId);
     }, []);
     
     const intervalId = setInterval(() => {
          setTimestamp(new Date());
        }, 1000);

const getData= async()=>{
try{
     await axios.get(`http://localhost:9095/ParkingPayment/${cookie.userId}/${vcookie.vehicleType}`)
     .then((response)=>{
          console.log(response.data);
          console.log(cookie.userId)
          console.log(vcookie.vehicleNo);
          setParking(response.data.parking);
          setFare(response.data.fare);
          setAmount(response.data.amount);
          setBookingId(response.data.bookingid);
          setVehicleTypeId(response.data.vehicletypeId);
          setDuration(response.data.parikingDuration)
     });
}catch(error){
          console.log(error)
     }
}

     const payMoney= async ()=>{
          await axios
          .post(`http://localhost:9095/ParkingPayment/getPayment/${cookie.userId}` ,{
               bookingid:getBookingId,
               vehicletypeId:getVehicleTypeId
          })
          .then((response)=>{
               console.log(response.data);
              toast.success("Payment successfully done");
          })
          .catch((error)=>{
               console.log(error);
               console.log(cookie.userId);
               toast.error("payment failed");
          })

          
     }

     const downloadPDF = () => {
          const input = document.getElementById('invoice');
          html2canvas(input, {
               scale: 5, // Increase scale for higher quality
               logging: false, // Disable logging (optional)
               useCORS: true, // Enable CORS for rendering external images
               width: 1400 /* specify width */, // Specify the desired width of the canvas
               height: 1400,
          }).then(canvas => {
               const imgData = canvas.toDataURL('image/jpeg', 0.5); // Use JPEG format for better quality
               const pdf = new jsPDF('l', 'mm', 'A6');
               pdf.addImage(imgData, 'JPEG', 5, 5, 300, 275); // Adjust width and height as needed
               pdf.save('invoice.pdf');
          });
     };
     
     const handleLogOut=()=>{
          Cookies.remove('user');
          navigate('/signin')
     }
     return (
          <div className="container mt-5 login">
               <div className="row justify-content-center">
                    <div className="col-md-6 ">
                         <div className="invoice-header text-center " style={{color:'#6C5B7B'}}>
                         <h1>Park Safe</h1>
                         </div>
                         <div id="invoice">
                              <table className="table">
                                   <thead style={{color:'#C06C84'}}>
                                   <h2 >Parking Invoice</h2>
                                        <tr>
                                             <td colSpan={9} style={{ border: '1px solid #000' }}>
                                                       <h6>Date {timestamp.toLocaleString()}</h6>
                                            
                                             </td>
                                        </tr>
                                        <tr>
                                             <td colSpan="9" style={{ border: '1px solid #000' }}>
                                                  <h3> Vehicle No : {vcookie.vehicleNo}</h3>
                                                  <h6>Slot Number : {vcookie.slots}</h6>
                                                  <h6>Fare : {fare} </h6>
                                                  <h6>Parking :{actualParking} </h6>
                                             </td>
                                             
                                        </tr>
                                        <tr>
                                             <td className="col-6" style={{ border: '1px solid #000' }}>
                                                  Amount : {duration*fare}
                                             </td>
                                        </tr>
                                   </thead>
                                   <tbody>
                                   </tbody>
                              </table>

               <h1 style={{color:'#6C5B7B'}}>Thank You ! Visit Again </h1>
                         </div>
                         <button className="btn btn-primary"  onClick={payMoney}> Pay 
                              </button>

                         <div>
                              <button onClick={getData}> Generate Invoice</button>
                         </div>
                         <div className="text-center">
                              <button className="btn btn-primary" onClick={downloadPDF}>
                                   Download Invoice
                              </button>
                         </div>
                         <div className="text-center">
                              <button className="btn btn-primary" onClick={handleLogOut}>
                                   Logout
                              </button>
                         </div>
                    </div>
               </div>
          </div>
     );
};

export default Receipt;