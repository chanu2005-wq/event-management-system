import { useParams } from "react-router-dom";
import { useState } from "react";
import Navbar from "../components/Navbar";

const Booking = () => {
  const { type } = useParams();
  const [submitted, setSubmitted] = useState(false);

  const handleSubmit = (e) => {
    e.preventDefault();
    setSubmitted(true);
  };

  return (
    <>
      <Navbar />
      <br />
      <br />
      <section className="booking-page">
        <h2>Book {type.charAt(0).toUpperCase() + type.slice(1)} Event</h2>

        {!submitted ? (
          <form className="booking-form" onSubmit={handleSubmit}>
            {/* CUSTOMER DETAILS */}
            <input type="text" placeholder="Customer Name" required />
            <input type="email" placeholder="Email Address" required />
            <input type="tel" placeholder="Phone Number" required />

            {/* EVENT DETAILS */}
            <input type="date" required />
            <input type="text" placeholder="Event Location" required />
            <input type="number" placeholder="Number of Guests" required />

            {/* CHECKBOXES */}
            <h4>Services Required</h4>
            <div className="checkbox-group">
              <label>
                <input type="checkbox" /> Decoration
              </label>
              <label>
                <input type="checkbox" /> Catering
              </label>
              <label>
                <input type="checkbox" /> DJ & Music
              </label>
              <label>
                <input type="checkbox" /> Photography
              </label>
              <label>
                <input type="checkbox" /> Lighting
              </label>
            </div>

            {/* BUDGET */}
            <select required>
              <option value="">Select Budget Range</option>
              <option>Below ₹50,000</option>
              <option>₹50,000 – ₹1,00,000</option>
              <option>₹1,00,000 – ₹3,00,000</option>
              <option>₹3,00,000+</option>
            </select>

            {/* REQUIREMENTS */}
            <textarea
              placeholder="Mention additional requirements (theme, food preferences, stage design, etc.)"
              rows="4"
            ></textarea>

            <button className="btn primary">Confirm Booking</button>
          </form>
        ) : (
          /* CONFIRMATION POPUP */
          <div className="confirmation-box">
            <h3>🎉 Booking Confirmed!</h3>
            <p>
              Thank you for choosing <b>Royal Events</b>.
            </p>
            <p>Our team will contact you shortly to finalize the details.</p>
          </div>
        )}
      </section>
    </>
  );
};

export default Booking;
