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
        <Route path="/profile/loans" element={<LoansPage />} />
        <Route path="/profile/reservations" element={<ReservationPage />} />
        <Route path="/profile/history" element={<HistoryPage />} />
        <Route path="/profile/favorites" element={<FavouritePage />} />
      </Routes>
    </>
  );
}

export default App;
