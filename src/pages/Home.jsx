import { useNavigate } from "react-router-dom";

import Navbar from "../components/Navbar";
import Hero from "../components/Hero";
import EventCard from "../components/EventCard";

// GALLERY IMAGES
import img1 from "../assets/event1.jpg";
import img2 from "../assets/event2.jpg";
import img3 from "../assets/event3.jpg";
import img4 from "../assets/event4.jpg";
import festivalImg from "../assets/festival/f1.jpg";

const Home = () => {
  const navigate = useNavigate();

  return (
    <>
      {/* NAVBAR */}
      <Navbar />

      {/* HERO SECTION (ADD ID HERE ✅) */}
      <section id="home">
        <Hero />
      </section>

      {/* GALLERY SECTION */}
      <section className="gallery-section">
        <div className="gallery">
          <div
            className="gallery-card"
            onClick={() => navigate("/events/birthday")}
          >
            <img src={img1} alt="Birthday Event" />
            <div className="overlay">
              <h3>Birthday Events</h3>
              <p>Fun-filled birthday celebrations</p>
            </div>
          </div>

          <div
            className="gallery-card"
            onClick={() => navigate("/events/party")}
          >
            <img src={img2} alt="DJ Party" />
            <div className="overlay">
              <h3>DJ & Party Nights</h3>
              <p>High-energy music & lighting</p>
            </div>
          </div>

          <div
            className="gallery-card"
            onClick={() => navigate("/events/wedding")}
          >
            <img src={img3} alt="Wedding Event" />
            <div className="overlay">
              <h3>Wedding Events</h3>
              <p>Luxury wedding planning</p>
            </div>
          </div>

          <div
            className="gallery-card"
            onClick={() => navigate("/events/stage")}
          >
            <img src={img4} alt="Stage Decoration" />
            <div className="overlay">
              <h3>Stage Decoration</h3>
              <p>Elegant & royal decorations</p>
            </div>
          </div>

          {/* FESTIVAL MODULE */}
          <div
            className="gallery-card"
            onClick={() => navigate("/events/festival")}
          >
            <img src={festivalImg} alt="Festival Events" />
            <div className="overlay">
              <h3>Festival Events</h3>
              <p>Traditional & cultural celebrations</p>
            </div>
          </div>
        </div>
      </section>

      {/* EVENTS SECTION */}
      <section id="events" className="home-section">
        <h2>Our Events</h2>

        <EventCard
          icon="💍"
          title="Wedding Event"
          description="Luxury wedding planning"
          type="wedding"
        />

        <EventCard
          icon="🎂"
          title="Birthday Party"
          description="Celebrate with style"
          type="birthday"
        />

        <EventCard
          icon="🏢"
          title="Corporate Event"
          description="Professional corporate events"
          type="corporate"
        />
      </section>

      {/* ABOUT SECTION */}
      <section id="about" className="home-section light">
        <h2>About Us</h2>
        <p>
          Royal Events is a professional event management company specializing
          in weddings, birthdays, corporate events, and festivals with elegance
          and precision.
        </p>
      </section>

      {/* CONTACT SECTION */}
      <section id="contact" className="home-section">
        <h2>Contact Us</h2>
        <p>Email: info@royalevents.com</p>
        <p>Phone: +91 9876543210</p>
      </section>
    </>
  );
};

export default Home;
