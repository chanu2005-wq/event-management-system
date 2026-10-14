import Navbar from "../components/Navbar";
import EventCard from "../components/EventCard";

const Events = () => {
  return (
    <>
      <Navbar />

      <div className="events-page">
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
      </div>
    </>
  );
};

export default Events;
