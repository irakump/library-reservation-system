import "./App.css";
import Home from "./pages/Home.jsx";
import ProfilePage from "./pages/ProfilePage.jsx";
import LoansPage from "./pages/LoansPage.jsx";

import { Routes, Route } from "react-router";

function App() {
  return (
    <>
      <main></main>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/profile" element={<ProfilePage />} />
        <Route path="/loans" element={<LoansPage />} />
      </Routes>
    </>
  );
}

export default App;
