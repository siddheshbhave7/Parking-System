import { useNavigate } from "react-router-dom";
import axios from "axios";
import { useEffect, useState } from "react";
import { toast } from "react-toastify";
import { isLoggedInCheck } from "./isLoggedInCheck";

const Payment = () => {
  const [bookings, setBookings] = useState([]);
  const navigate = useNavigate();
  

  useEffect(() => {
    book();
    isLoggedInCheck(navigate);
  }, []);
  const book = async () => {
    await axios
      .get(`http://localhost:9095/ParkingPaymentManager/getPayment/1`)
      .then((response) => {
        const result = response.data;
        console.log(result.data);
        setBookings(result.data);
      });
  };
  return (
    <div>
      <h1 style={{ textAlign: "center", color: "red" }}> Booking Details</h1>
      <br></br>
      <br></br>

      <table style={styles.table}>
        <thead>
          <tr>
            <td> bookingId </td>
            <td>exitTime</td>
            <td>pid</td>
            <td>slots</td>
            <td>startTime</td>
            <td>userId </td>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td> {bookings.bookingId} </td>
            <td>{bookings.exitTime}</td>
            <td>{bookings.pid}</td>
            <td>{bookings.slots}</td>
            <td>{bookings.startTime}</td>
            <td>{bookings.userId} </td>
          </tr>
        </tbody>
      </table>
      <br></br>

      <br></br>
      <div style={styles.container}>
        {" "}
        {/* <div onChange={this.onChangeValue}> */}
        
        <br></br>
        <input type="radio" value="Card" name="gender" /> CARD
        <input type="radio" value="NetBanking" name="gender" /> NETBANKING
        <input type="radio" value="Upi" name="gender" /> UPI
        {/* </div> */}
        <button
          style={{ marginLeft: 50 }}
          className="btn btn-primary"
          onClick={() => {
            toast.success("Payment done successfully");
          }}
        >
          Pay
        </button>
        <br></br>
        <button
          style={{ marginLeft: 20 }}
          className="btn btn-primary"
          onClick={() => {
            navigate("/myhome");
          }}
        >
          Back
        </button>
      </div>
      <br></br>
      <br></br>
    </div>
  );
};
const styles = {
  container: {
    textAlign: "center",
    color: "black",
    backgroundColor: "grey",
    width: 400,
    height: 200,
    padding: 20,
    position: "relative",
    top: 0,
    left: 0,
    right: 0,
    bottom: 0,
    margin: "auto",
    borderColor: "black",
    borderRadius: 10,
    broderWidth: 1,
    borderStyle: "solid",
    boxShadow: "1px 1px 20px 5px #C9C9C9",
  },
  table: {
    width: 800,
    color: "black",
    backgroundColor: "cyan",
    borderColor: "black",
    borderRadius: 10,
    broderWidth: 1,
    borderStyle: "solid",
    textAlign: "center",
    border: 5,
    marginLeft: 400,
  },
  button: {
    marginLeft: 700,
  },
};

export default Payment;
