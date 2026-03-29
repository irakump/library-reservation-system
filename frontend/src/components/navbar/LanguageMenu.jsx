import React from "react";
import { useTranslation } from "react-i18next";
import { useAuth } from "../../contexts/AuthContext";

const LanguageMenu = () => {
  const { t, i18n } = useTranslation(["navigation"]);
  const { isLoggedIn } = useAuth();

    const lngs = {
        en: {nativeName: 'English'},
        ja: {nativeName: '日本語'},
        ar: {nativeName: 'العربية'},
    };

  return (
    <div className={`absolute max-sm:w-full ${isLoggedIn ? "ltr:sm:right-75 rtl:sm:left-78" : "ltr:sm:right-30 rtl:sm:left-33"} text-center z-50`}>
      <ul className="flex flex-col w-full sm:w-40 bg-navbar border border-t-0 *:border-t *:p-2 text-xl font-bold *:hover:cursor-pointer">
          {Object.keys(lngs).map((lng) => (
              <button
                  type="submit"
                  key={lng}
                  onClick={() => i18n.changeLanguage(lng)}
                  disabled={i18n.resolvedLanguage === lng}
              >
                  <li>{lngs[lng].nativeName}</li>
              </button>
          ))
          }
        
      </ul>
    </div>
  );
};

export default LanguageMenu;
