import React, { createContext, useContext, useState } from "react";

const MenuContext = createContext();

export const MenuProvider = ({children}) => {
  const [isProfileMenuOpen, setIsProfileMenuOpen] = useState(false);

  const toggleMenu = () => setIsProfileMenuOpen(!isProfileMenuOpen);

  return (
    <MenuContext.Provider value={{isProfileMenuOpen, setIsProfileMenuOpen, toggleMenu}}>
        {children}
    </MenuContext.Provider>
  );
};

export const useMenu = () => useContext(MenuContext);
