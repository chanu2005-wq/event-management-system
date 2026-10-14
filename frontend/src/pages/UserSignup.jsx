import Navbar from "../components/Navbar";
import { Link } from "react-router-dom";

const UserSignup = () => {
  return (
    <>
      <Navbar />
      <div className="form-container">
        <h2>User Signup</h2>

        <input type="text" placeholder="Full Name" />
        <input type="email" placeholder="Email" />
        <input type="password" placeholder="Password" />

        <button className="btn primary">Sign Up</button>

        <p style={{ textAlign: "center" }}>
          Already have an account? <Link to="/login/user">Login</Link>
        </p>
      </div>
    </>
  );
};

export default UserSignup;
