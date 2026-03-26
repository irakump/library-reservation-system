import { createContext, useContext, useEffect, useState } from "react";
import i18n from "../i18n";

const LayoutDirectionContext = createContext();

export const LayoutDirectionProvider = ({ children }) => {
  const [isRTL, setIsRTL] = useState(() => {
    return i18n.language === "ar";
  });

  useEffect(() => {
    const handleLanguageChange = (lng) => {
      setIsRTL(lng === "ar");
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

  return (
    <LayoutDirectionContext.Provider value={{ isRTL }}>
      {children}
    </LayoutDirectionContext.Provider>
  );
};

export const useLayoutDirection = () => useContext(LayoutDirectionContext);
