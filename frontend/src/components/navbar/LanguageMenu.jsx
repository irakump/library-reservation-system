import React from "react";
import { useTranslation } from "react-i18next";
import { useAuth } from "../../contexts/AuthContext";

const LanguageMenu = () => {
  const { t } = useTranslation(["navigation"]);
  const { isLoggedIn } = useAuth();

  return (
    <div className={`absolute max-sm:w-full ${isLoggedIn ? "ltr:sm:right-75 rtl:sm:left-78" : "ltr:sm:right-30 rtl:sm:left-33"} text-center z-50`}>
      <ul className="flex flex-col w-full sm:w-40 bg-navbar border border-t-0 *:border-t *:p-2 text-xl font-bold *:hover:cursor-pointer">

        <button>
            <li>{t("footer.language.english")}</li>
        </button>
        
        <button>
            <li>{t("footer.language.arabic")}</li>
        </button>

        <button>
            <li>{t("footer.language.japanese")}</li>
        </button>
        
      </ul>
    </div>
  );
};

export default LanguageMenu;
