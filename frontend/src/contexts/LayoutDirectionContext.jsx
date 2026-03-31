import { createContext, useContext, useEffect, useState } from "react";
import i18n from "../i18n";

const LayoutDirectionContext = createContext();

export const LayoutDirectionProvider = ({ children }) => {
  const [isRTL, setIsRTL] = useState(() => {
    return i18n.language === "ar-u-nu-arab";
  });
  const [language, setLanguage] = useState(() => i18n.language)

  useEffect(() => {
    const handleLanguageChange = (lng) => {
      setIsRTL(lng === "ar-u-nu-arab");
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
      if (!date) {
          return null;
      }
      const locales = {
          'ar-u-nu-arab': "ar-u-nu-arab",
          'ja-JP': "ja-JP",
          'en-US': "en-US",
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
