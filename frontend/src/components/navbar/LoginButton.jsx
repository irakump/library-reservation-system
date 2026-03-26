import { useTranslation } from "react-i18next";
import { useAuth } from "../../contexts/AuthContext";
import { useMenu } from "../../contexts/MenuContext";

const LoginButton = () => {
  const { isLoggedIn, openLogin, openLogout } = useAuth();
  const { setIsProfileMenuOpen, setIsLanguageMenuOpen } = useMenu();
  const { t } = useTranslation("button")

  const closeMenu = () => {
    setIsProfileMenuOpen(false);
    setIsLanguageMenuOpen(false);
  }

  return (
    <div>
      {isLoggedIn ? (
        <button className="w-30 text-xl hover:cursor-pointer" 
        onClick={() => {
          closeMenu();
          openLogout();
        }}>
          {t("logout")}
        </button>
      ) : (
        <button className="w-30 text-xl hover:cursor-pointer" 
        onClick={() => {
          closeMenu();
          openLogin();
        }}>
          {t("login")}
        </button>
      )}
    </div>
  );
};

export default LoginButton;
