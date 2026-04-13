import ProfileMenu from "./ProfileMenu";
import DesktopMenu from "./DesktopMenu";
import { useMenu } from "../../contexts/MenuContext";
import { useAuth } from "../../contexts/AuthContext";
import LoginModal from "../auth/LoginModal.jsx";
import RegisterModal from "../auth/RegisterModal.jsx";
import LogoutModal from "../auth/LogoutModal";
import { useTranslation } from "react-i18next";
import LanguageMenu from "./LanguageMenu.jsx";
import { useIsMobile } from "../../hooks/useIsMobile.js";

const NavBar = () => {
  const {
    isProfileMenuOpen,
    setIsProfileMenuOpen,
    toggleMenu,
    isLanguageMenuOpen,
    setIsLanguageMenuOpen,
    toggleLanguageMenu,
  } = useMenu();
  const { t } = useTranslation("navigation");

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

  const isMobile = useIsMobile();

  return (
    <div>
      <div className="flex flex-row justify-between items-center bg-navbar p-2">
        <div className="flex flex-row items-center">
          <a href="/">
            <img
              src="/book-logo.png"
              alt={t("navbar.book_logo_alt")}
              className="h-7 pr-2 pt-1 rtl:pr-0 rtl:pl-2"
              data-testid="book-logo"
            />
          </a>
          <a
            href="/"
            className="font-bold text-2xl max-[260px]:hidden"
            data-testid="site-title"
          >
              MetBook
          </a>
        </div>
        <div className="flex flex-row gap-3 items-center h-10">
          {isMobile && isLanguageMenuOpen ? (
            <button
              onClick={toggleLanguageMenu}
              className="text-6xl font-light pb-3 mr-0 w-8 hover:cursor-pointer"
            >
              &times;
            </button>
          ) : (
            <div className="sm:w-40 flex justify-end">
                <button onClick={() => {
                    toggleLanguageMenu();
                    setIsProfileMenuOpen(false);
                }}></button>
              <img
                src="/language-icon.png"
                alt={t("navbar.language_icon_alt")}
                className="h-7 mt-1 hover:cursor-pointer"
                tabIndex={0}
                onClick={() => {
                    toggleLanguageMenu();
                    setIsProfileMenuOpen(false);
                }}
              />
            </div>
          )}

          <div className="max-sm:hidden">
            <DesktopMenu />
          </div>

          <div className="sm:hidden">
            {isProfileMenuOpen ? (
              <button
                onClick={toggleMenu}
                className="text-6xl font-light pb-3 mr-2 w-8 hover:cursor-pointer"
              >
                &times;
              </button>
            ) : (
              <button
                className="h-10 mt-2 hover:cursor-pointer"
                onClick={() => {
                  toggleMenu();
                  setIsLanguageMenuOpen(false);
                }}
              >
                <img
                  src="/hamburger-menu.png"
                  alt={t("navbar.hamburger_menu_icon_alt")}
                  className="h-10"
                />
              </button>
            )}
          </div>
        </div>
      </div>
      {isLanguageMenuOpen && <LanguageMenu />}
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
