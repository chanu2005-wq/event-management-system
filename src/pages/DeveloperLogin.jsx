import Navbar from "../components/Navbar";
import { Link } from "react-router-dom";

const DeveloperLogin = () => {
  return (
    <>
      <Navbar />
      <div className="form-container">
        <h2>Developer Login</h2>

        <input type="email" placeholder="Business Email" />
        <input type="password" placeholder="Password" />

        <button className="btn primary">Login</button>

        {/* Developer Signup */}
        <p style={{ textAlign: "center" }}>
          New event handler? <Link to="/signup/developer">Register here</Link>
        </p>

        {/* Back to User Login */}
        <p style={{ textAlign: "center", marginTop: "10px" }}>
          <Link to="/login/user">Back to User Login</Link>
        </p>
      </div>
    </>
  );
};

export default DeveloperLogin;
