import Home from "./pages/Home.jsx";
import ProfilePage from "./pages/ProfilePage.jsx";
import LoansPage from "./pages/LoansPage.jsx";
import ReservationPage from "./pages/ReservationPage.jsx";
import HistoryPage from "./pages/HistoryPage.jsx";
import FavouritePage from "./pages/FavouritePage.jsx";
import NavBar from "./components/navbar/NavBar.jsx";
import { AuthProvider } from "./contexts/AuthContext.jsx";
import { MenuProvider } from "./contexts/MenuContext.jsx";
import Footer from "./components/footer/Footer.jsx";
import { Routes, Route } from "react-router";
import Notification from "./components/notification/Notification.jsx";
import { NotificationProvider } from "./contexts/NotificationContext.jsx";
import { BookProvider } from "./contexts/FavoritesContext.jsx";
import { LoanProvider } from "./contexts/LoanContext.jsx";

function App() {
  return (
    <>
      <AuthProvider>
        <MenuProvider>
          <LoanProvider>
            <BookProvider>
              <NavBar />

              <NotificationProvider>
                <Notification />
              </NotificationProvider>
              <main>
                <Routes>
                  <Route path="/" element={<Home />} />
                  <Route path="/profile" element={<ProfilePage />} />
                  <Route path="/profile/loans" element={<LoansPage />} />
                  <Route
                    path="/profile/reservations"
                    element={<ReservationPage />}
                  />
                  <Route path="/profile/history" element={<HistoryPage />} />
                  <Route
                    path="/profile/favorites"
                    element={<FavouritePage />}
                  />
                </Routes>
              </main>
              <Footer />
            </BookProvider>
          </LoanProvider>
        </MenuProvider>
      </AuthProvider>
    </>
  );
}

export default App;
