import React, { createContext, useContext, useState } from "react";

const MenuContext = createContext();
import PropTypes from "prop-types";

export const MenuProvider = ({ children }) => {
  const [isProfileMenuOpen, setIsProfileMenuOpen] = useState(false);
  const [isLanguageMenuOpen, setIsLanguageMenuOpen] = useState(false);

  const toggleMenu = () => setIsProfileMenuOpen(!isProfileMenuOpen);
  const toggleLanguageMenu = () => setIsLanguageMenuOpen(!isLanguageMenuOpen);

  return (
    <MenuContext.Provider
      value={{
        isProfileMenuOpen,
        setIsProfileMenuOpen,
        isLanguageMenuOpen,
        setIsLanguageMenuOpen,
        toggleMenu,
        toggleLanguageMenu,
      }}
    >
      {children}
    </MenuContext.Provider>
  );
};

MenuProvider.propTypes = {
  children: PropTypes.node.isRequired,
};

export const useMenu = () => useContext(MenuContext);
