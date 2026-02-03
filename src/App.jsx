import { BrowserRouter, Routes, Route } from "react-router-dom";
import Home from "./pages/Home";
import Events from "./pages/Events";
import Booking from "./pages/Booking";
import Dashboard from "./pages/Dashboard";
import Contact from "./pages/Contact";
import EventGallery from "./pages/EventGallery";
import DeveloperSignup from "./pages/DeveloperSignup";
import DeveloperLogin from "./pages/DeveloperLogin";
import UserLogin from "./pages/UserLogin";
import UserSignup from "./pages/UserSignup";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home />} />

        {/* EVENTS */}
        <Route path="/events" element={<Events />} />
        <Route path="/events/:type" element={<EventGallery />} />

        {/* BOOKING (FIXED) */}
        <Route path="/booking/:type" element={<Booking />} />

        {/* AUTH */}
        <Route path="/login/user" element={<UserLogin />} />
        <Route path="/signup/user" element={<UserSignup />} />
        <Route path="/login/developer" element={<DeveloperLogin />} />
        <Route path="/signup/developer" element={<DeveloperSignup />} />

        {/* OTHER */}
        <Route path="/dashboard" element={<Dashboard />} />
        <Route path="/contact" element={<Contact />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
