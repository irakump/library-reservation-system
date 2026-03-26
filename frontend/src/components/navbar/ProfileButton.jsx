import React from "react";
import { useMenu } from "../../contexts/MenuContext";
import { useAuth } from "../../contexts/AuthContext";
import { useTranslation } from "react-i18next";

const ProfileButton = () => {
  const { toggleMenu, setIsLanguageMenuOpen } = useMenu();
  const { isLoggedIn } = useAuth();
  const { t } = useTranslation("navigation");

  return (
    isLoggedIn && (
    <div>
        <button onClick={() => {
            toggleMenu();
            setIsLanguageMenuOpen(false);
        }}
         className="flex flex-row gap-2 items-center w-50 justify-center text-xl mt-1 hover:cursor-pointer"
         >{t("navbar.dropdown.profile")}
            <img src="/down.png" alt={t("navbar.arrow_down_icon_alt")} className="h-4 mt-1" />
        </button>
      
    </div>
));
};

export default ProfileButton;
