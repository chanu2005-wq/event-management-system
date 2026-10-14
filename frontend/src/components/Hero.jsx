import { useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";

import hero1 from "../assets/hero1.jpg";
import hero2 from "../assets/hero2.jpg";
import hero3 from "../assets/hero3.jpg";

const images = [hero1, hero2, hero3];

const Hero = () => {
  const navigate = useNavigate();
  const [current, setCurrent] = useState(0);

  useEffect(() => {
    const interval = setInterval(() => {
      setCurrent((prev) => (prev === images.length - 1 ? 0 : prev + 1));
    }, 4000);

    return () => clearInterval(interval);
  }, []);

  return (
    <section id="home" className="hero">
      {/* SLIDER */}
      <div
        className="hero-slider"
        style={{ transform: `translateX(-${current * 100}%)` }}
      >
        {images.map((img, i) => (
          <div
            key={i}
            className="hero-slide"
            style={{
              backgroundImage: `linear-gradient(
                rgba(0,0,0,0.6),
                rgba(0,0,0,0.6)
              ), url(${img})`,
            }}
          />
        ))}
      </div>

      {/* CONTENT */}
      <div className="hero-content">
        <h4>WELCOME TO</h4>
        <h1>Royal Events</h1>
        <p>WE PLAN • YOU ENJOY</p>

        <button className="btn primary" onClick={() => navigate("/login/user")}>
          Get Started
        </button>
      </div>
    </section>
  );
};

export default Hero;
