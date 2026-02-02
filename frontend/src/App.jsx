import "./App.css";
import Home from "./pages/Home.jsx";
import ProfilePage from "./pages/ProfilePage.jsx";
import { Routes, Route } from "react-router";

function App() {
  return (
    <>
      <main></main>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/profile" element={<ProfilePage />} />

        {/* TODO: Change profile route names, if they are not separate pages (e.g. different content is rendered to same page) */}
        <Route path="/profile/loans" />
        <Route path="/profile/reservations" />
        <Route path="/profile/history" />
        <Route path="/profile/favorites" />
      </Routes>
    </>
  );
}

export default App;
