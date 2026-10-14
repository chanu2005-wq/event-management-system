import Navbar from "../components/Navbar";

const Dashboard = () => {
  return (
    <>
      <Navbar />
      <div style={{ padding: "40px" }}>
        <h2>User Dashboard</h2>
        <p>Welcome to Royal Events Dashboard</p>
        <ul>
          <li>My Bookings</li>
          <li>Profile Settings</li>
          <li>Logout</li>
        </ul>
      </div>
    </>
  );
};

export default Dashboard;