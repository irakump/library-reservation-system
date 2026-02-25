import ProfileMenu from "./ProfileMenu";
import DesktopMenu from "./DesktopMenu";
import { useMenu } from "../../contexts/MenuContext";
import { useAuth } from "../../contexts/AuthContext";
import LoginModal from "../auth/LoginModal.jsx";
import RegisterModal from "../auth/RegisterModal.jsx";
import LogoutModal from "../auth/LogoutModal";

const NavBar = () => {
  const { isProfileMenuOpen, toggleMenu } = useMenu();

  //const toggleMenu = () => setIsProfileMenuOpen(!isProfileMenuOpen);

  const {
    isLoginOpen,
    isRegisterOpen,
    isLogoutOpen,
    closeLogin,
    closeRegister,
    closeLogout,
    handleSwitchToRegister,
    handleSwitchToLogin,
    handleLogout,
  } = useAuth();

  return (
    <div>
      <div className="flex flex-row justify-between items-center bg-navbar p-2">
        <div className="flex flex-row items-center">
          <a href="/">
            <img
              src="/book-logo.png"
              alt="Stack of books"
              className="h-7 pr-2 pt-1"
            />
          </a>
          <a href="/" className="font-bold text-2xl">
            MetBook
          </a>
        </div>
        <div className="flex flex-row gap-3 items-center h-10">
          <img
            src="/language-icon.png"
            alt="Language"
            className="h-7 mt-1"
            tabIndex={0}
          />

          <div className="max-sm:hidden">
            <DesktopMenu />
          </div>

          <div className="sm:hidden">
            {isProfileMenuOpen ? (
              <button
                onClick={toggleMenu}
                className="text-6xl font-light pb-3 mr-2 w-8"
              >
                &times;
              </button>
            ) : (
              <button className="h-10 mt-2" onClick={toggleMenu}>
                <img src="/hamburger-menu.png" alt="Menu" className="h-10" />
              </button>
            )}
          </div>
        </div>
      </div>
      {isProfileMenuOpen && <ProfileMenu />}

      <LoginModal
        isOpen={isLoginOpen}
        onClose={closeLogin}
        onSwitchToRegister={handleSwitchToRegister}
      />
      <RegisterModal
        isOpen={isRegisterOpen}
        onClose={closeRegister}
        onSwitchToLogin={handleSwitchToLogin}
      />
      <LogoutModal
        isOpen={isLogoutOpen}
        onClose={closeLogout}
        onConfirm={handleLogout}
      />
    </div>
  );
};

export default NavBar;
