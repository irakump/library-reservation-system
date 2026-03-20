import { useTranslation } from "react-i18next";
import { useAuth } from "../../contexts/AuthContext";

const LoginButton = () => {
  const { isLoggedIn, openLogin, openLogout } = useAuth();
  const { t } = useTranslation("button")

  return (
    <div>
      {isLoggedIn ? (
        <button className="w-30 text-xl hover:cursor-pointer" onClick={openLogout}>
          {t("logout")}
        </button>
      ) : (
        <button className="w-30 text-xl hover:cursor-pointer" onClick={openLogin}>
          {t("login")}
        </button>
      )}
    </div>
  );
};

export default LoginButton;
