import { useParams } from "react-router-dom";
import Navbar from "../components/Navbar";

/* 🎂 Birthday */
import b1 from "../assets/birthday/b1.jpg";
import b2 from "../assets/birthday/b2.jpg";
import b3 from "../assets/birthday/b3.jpg";
import b4 from "../assets/birthday/b4.jpg";
import b5 from "../assets/birthday/b5.jpg";
import b6 from "../assets/birthday/b6.jpg";
import b7 from "../assets/birthday/b7.jpg";

/* 💍 Wedding */
import w1 from "../assets/wedding/w1.jpg";
import w2 from "../assets/wedding/w2.jpg";
import w3 from "../assets/wedding/w3.jpg";
import w4 from "../assets/wedding/w4.jpg";
import w5 from "../assets/wedding/w5.jpg";
import w6 from "../assets/wedding/w6.jpg";
import w7 from "../assets/wedding/w7.jpg";

/* 🎉 Party */
import p1 from "../assets/party/p1.jpg";
import p2 from "../assets/party/p2.jpg";
import p3 from "../assets/party/p3.jpg";
import p4 from "../assets/party/p4.jpg";
import p5 from "../assets/party/p5.jpg";
import p6 from "../assets/party/p6.jpg";
import p7 from "../assets/party/p7.jpg";

/* 🎭 Stage */
import s1 from "../assets/stage/s1.jpg";
import s2 from "../assets/stage/s2.jpg";
import s3 from "../assets/stage/s3.jpg";
import s4 from "../assets/stage/s4.jpg";
import s5 from "../assets/stage/s5.jpg";
import s6 from "../assets/stage/s6.jpg";
import s7 from "../assets/stage/s7.jpg";

/* 🎊 Festival */
import f1 from "../assets/festival/f1.jpg";
import f2 from "../assets/festival/f2.jpg";
import f3 from "../assets/festival/f3.jpg";
import f4 from "../assets/festival/f4.jpg";
import f5 from "../assets/festival/f5.jpg";
import f6 from "../assets/festival/f6.jpg";
import f7 from "../assets/festival/f7.jpg";

/* 🔁 EVENT DATA */
const eventImages = {
  birthday: {
    title: "Birthday Events",
    images: [b1, b2, b3, b4, b5, b6, b7],
  },
  wedding: {
    title: "Wedding Events",
    images: [w1, w2, w3, w4, w5, w6, w7],
  },
  party: {
    title: "DJ & Party Events",
    images: [p1, p2, p3, p4, p5, p6, p7],
  },
  stage: {
    title: "Stage Decorations",
    images: [s1, s2, s3, s4, s5, s6, s7],
  },
  festival: {
    title: "Festival Celebrations",
    images: [f1, f2, f3, f4, f5, f6, f7],
  },
};

const EventGallery = () => {
  const { type } = useParams();
  const event = eventImages[type];

  if (!event) {
    return (
      <>
        <Navbar />
        <h2 style={{ textAlign: "center", marginTop: "50px" }}>
          Event Not Found
        </h2>
      </>
    );
  }

  return (
    <>
      <Navbar />

      <section className="event-gallery">
        <h2>{event.title}</h2>

        <div className="event-grid">
          {event.images.map((img, index) => (
            <img key={index} src={img} alt={`${event.title} ${index + 1}`} />
          ))}
        </div>
      </section>
    </>
  );
};

export default EventGallery;
