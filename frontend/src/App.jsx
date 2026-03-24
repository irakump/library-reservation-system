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
import { LoanProvider } from "./contexts/LoanContext.jsx";
import { FavoritesProvider } from "./contexts/FavoritesContext.jsx";
import { ReservationProvider } from "./contexts/ReservationContext.jsx";
import { Suspense } from "react";
import Loading from "./pages/Loading.jsx";

function App() {
  return (
    <>
      <AuthProvider>
        <MenuProvider>
          <ReservationProvider>
            <LoanProvider>
              <FavoritesProvider>
                <Suspense fallback={<Loading />}>
                  <NavBar/>
                </Suspense>

                <NotificationProvider>
                  <Notification />
                </NotificationProvider>
                <main>
                  <Suspense fallback={<Loading />}>
                    <Routes>
                        <Route path="/" element={<Home />} />
                      <Route path="/profile" element={<ProfilePage />} />
                      <Route path="/profile/loans" element={<LoansPage />} />
                      <Route
                        path="/profile/reservations"
                        element={<ReservationPage />}
                      />
                      <Route
                        path="/profile/history"
                        element={<HistoryPage />}
                      />
                      <Route
                        path="/profile/favorites"
                        element={<FavouritePage />}
                      />
                    </Routes>
                  </Suspense>
                </main>
                <Footer>
                </Footer>
              </FavoritesProvider>
            </LoanProvider>
          </ReservationProvider>
        </MenuProvider>
      </AuthProvider>
    </>
  );
}

export default App;
