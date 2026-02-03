import Navbar from "../components/Navbar";
import { Link } from "react-router-dom";

const UserLogin = () => {
  return (
    <>
      <Navbar />
      <div className="form-container">
        <h2>User Login</h2>

        <input type="email" placeholder="Email" />
        <input type="password" placeholder="Password" />

        <button className="btn primary">Login</button>

        {/* User Signup */}
        <p style={{ textAlign: "center" }}>
          Don’t have an account? <Link to="/signup/user">Sign Up</Link>
        </p>

        {/* Developer Login */}
        <p style={{ textAlign: "center", marginTop: "10px" }}>
          Are you an event handler?{" "}
          <Link to="/login/developer">Login as Developer</Link>
        </p>
      </div>
    </>
  );
};

export default UserLogin;
