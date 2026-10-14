import { Link, useLocation, useNavigate } from "react-router-dom";
import logo from "../assets/logo.png";
import "./Navbar.css";

const Navbar = () => {
  const location = useLocation();
  const navigate = useNavigate();

  // Scroll to section (works even if you are on another page)
  const scrollToSection = (id) => {
    if (location.pathname !== "/") {
      navigate("/");
      setTimeout(() => {
        document.getElementById(id)?.scrollIntoView({ behavior: "smooth" });
      }, 300);
    } else {
      document.getElementById(id)?.scrollIntoView({ behavior: "smooth" });
    }
  };

  return (
    <nav className="navbar">
      {/* LOGO */}
      <div className="navbar-left" onClick={() => navigate("/")}>
        <img src={logo} alt="Royal Events Logo" className="logo" />
        <span className="brand">Royal Events</span>
      </div>

      {/* MENU */}
      <ul className="navbar-links">
        <li onClick={() => scrollToSection("home")}>Home</li>
        <li onClick={() => scrollToSection("events")}>Events</li>
        <li onClick={() => scrollToSection("about")}>About</li>
        <li onClick={() => scrollToSection("contact")}>Contact</li>
        <li>
          <Link to="/login/user">Login</Link>
        </li>
        <li>
          <Link to="/signup/user" className="signup-btn">
            Sign Up
          </Link>
        </li>
      </ul>
    </nav>
  );
};

export default Navbar;
