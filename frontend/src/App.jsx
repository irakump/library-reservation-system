import "./App.css";
import Home from "./pages/Home.jsx";
import ProfilePage from "./pages/ProfilePage.jsx";
import LoansPage from "./pages/LoansPage.jsx";
import ReservationPage from "./pages/ReservationPage.jsx";
import HistoryPage from "./pages/HistoryPage.jsx";
import FavouritePage from "./pages/FavouritePage.jsx";

import { Routes, Route } from "react-router";

function App() {
  return (
    <>
      <main></main>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/profile" element={<ProfilePage />} />

        {/* TODO: Add elements AND Change profile route names, if they are not separate pages (e.g. different content is rendered to same page) */}
        <Route path="/profile/loans" />
        <Route path="/profile/reservations" />
        <Route path="/profile/history" />
        <Route path="/profile/favorites" />
      </Routes>
    </>
  );
}

export default App;
