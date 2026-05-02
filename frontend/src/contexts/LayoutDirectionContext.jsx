import { createContext, useContext, useEffect, useState } from "react";
import i18n from "../i18n";
import PropTypes from "prop-types";

const LayoutDirectionContext = createContext();

export const LayoutDirectionProvider = ({ children }) => {
  const [isRTL, setIsRTL] = useState(() => {
    return i18n.language === "ar-u-nu-arab";
  });

  useEffect(() => {
    const handleLanguageChange = (lng) => {
      setIsRTL(lng === "ar-u-nu-arab");
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

LayoutDirectionProvider.propTypes = {
    children: PropTypes.node.isRequired,
};
