import { useNavigate } from "react-router-dom";

const EventCard = ({ icon, title, description, type }) => {
  const navigate = useNavigate();

  return (
    <div className="event-card">
      <div className="event-icon">{icon}</div>
      <h3>{title}</h3>
      <p>{description}</p>

      <button
        className="btn primary"
        onClick={() => navigate(`/booking/${type}`)}
      >
        Book Event
      </button>
    </div>
  );
};

export default EventCard;
