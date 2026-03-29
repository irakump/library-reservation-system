import { createContext, useContext, useEffect, useState } from "react";
import i18n from "../i18n";

const LayoutDirectionContext = createContext();

export const LayoutDirectionProvider = ({ children }) => {
  const [isRTL, setIsRTL] = useState(() => {
    return i18n.language === "ar";
  });
  const [language, setLanguage] = useState(() => i18n.language)

  useEffect(() => {
    const handleLanguageChange = (lng) => {
      setIsRTL(lng === "ar");
      setLanguage(lng)
    };

    i18n.on("languageChanged", handleLanguageChange);

    return () => {
      i18n.off("languageChanged", handleLanguageChange);
    };
  }, []);

  useEffect(() => {
    document.documentElement.setAttribute("dir", isRTL ? "rtl" : "ltr");
    document.documentElement.setAttribute("lang", i18n.language);
  }, [isRTL]);

  const formatDate = (date) => {
      const locales = {
          ar: "ar-u-nu-arab",
          ja: "ja-JP",
          en: "en-US",
      }
      return new Intl.DateTimeFormat(locales[language], {
          dateStyle: "long"
      }).format(new Date(date));
  }

  return (
    <LayoutDirectionContext.Provider value={{ isRTL, formatDate }}>
      {children}
    </LayoutDirectionContext.Provider>
  );
};

export const useLayoutDirection = () => useContext(LayoutDirectionContext);
