import EventCard from "./EventCard";
import "./FlipCard.css";

const EventCards = () => {
  return (
    <div className="events-container">
      <EventCard
        icon="🎂"
        title="Birthday"
        description="Celebrate your special moments with an unforgettable birthday event."
        type="birthday"
      />

      <EventCard
        icon="💍"
        title="Wedding"
        description="Create beautiful memories with a professionally planned wedding."
        type="wedding"
      />

      <EventCard
        icon="🏢"
        title="Corporate"
        description="Organize professional corporate events with ease."
        type="corporate"
      />

      <EventCard
        icon="🎉"
        title="Party"
        description="Plan exciting parties and create memorable experiences."
        type="party"
      />
    </div>
  );
};

export default EventCards;
