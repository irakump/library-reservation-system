import React from "react";
import { useTranslation } from "react-i18next";
import { useAuth } from "../../contexts/AuthContext";
import { locales } from "../../utils/locales";

const LanguageMenu = () => {
  const { i18n } = useTranslation(["navigation"]);
  const { isLoggedIn } = useAuth();

  return (
    <div className={`absolute max-sm:w-full ${isLoggedIn ? "ltr:sm:right-75 rtl:sm:left-78" : "ltr:sm:right-30 rtl:sm:left-33"} text-center z-50`}>
      <ul className="flex flex-col w-full sm:w-40 bg-navbar border border-t-0 *:border-t *:p-2 text-xl font-bold *:hover:cursor-pointer">
          {Object.keys(locales).map((lng) => (
              <button
                  type="submit"
                  key={lng}
                  onClick={() => i18n.changeLanguage(lng)}
                  disabled={i18n.resolvedLanguage === lng}
              >
                  <li>{locales[lng].nativeName}</li>
              </button>
          ))
          }
        
      </ul>
    </div>
  );
};

export default LanguageMenu;
