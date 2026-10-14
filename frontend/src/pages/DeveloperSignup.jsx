import Navbar from "../components/Navbar";
import { Link } from "react-router-dom";
import { useState } from "react";

const DeveloperSignup = () => {
  const [events, setEvents] = useState([]);

  const handleEventChange = (e) => {
    const value = e.target.value;
    setEvents(
      e.target.checked
        ? [...events, value]
        : events.filter((event) => event !== value),
    );
  };

  return (
    <>
      <Navbar />
      <div className="form-container">
        <h2>Developer Signup</h2>

        <input type="text" placeholder="Full Name" />
        <input type="email" placeholder="Business Email" />
        <input type="text" placeholder="Company Name" />

        {/* EVENT TYPE SELECTION */}
        <div style={{ textAlign: "left", marginTop: "15px" }}>
          <p>
            <strong>Events You Handle</strong>
          </p>

          {[
            "Wedding",
            "Birthday",
            "Corporate Event",
            "Concert",
            "Exhibition",
            "Festival",
          ].map((event) => (
            <label key={event} style={{ display: "block" }}>
              <input
                type="checkbox"
                value={event}
                onChange={handleEventChange}
              />{" "}
              {event}
            </label>
          ))}
        </div>

        <input type="password" placeholder="Password" />

        <button className="btn primary">Submit for Approval</button>

        <p style={{ textAlign: "center" }}>
          Already registered? <Link to="/login/developer">Login</Link>
        </p>
      </div>
    </>
  );
};

export default DeveloperSignup;
