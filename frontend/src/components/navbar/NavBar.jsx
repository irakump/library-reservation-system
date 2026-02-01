import React, { useState } from "react";
import ProfileMenu from "./ProfileMenu";
import DesktopMenu from "./DesktopMenu";

const NavBar = () => {
  const [profileMenuOpen, setProfileMenuOpen] = useState(false);

  const toggleMenu = () => setProfileMenuOpen(!profileMenuOpen);

  return (
    <div>
      <div className="flex flex-row justify-between items-center bg-navbar p-2">
        <p className="font-bold text-3xl">MetBook</p>
        <div className="flex flex-row gap-3 items-center h-10">
          <img src="/language-icon.png" alt="Language" className="h-8" />

          <div className="max-sm:hidden">
            <DesktopMenu />
          </div>

          <div className="sm:hidden">
          {profileMenuOpen ? (
            <button onClick={toggleMenu} 
            className="text-6xl font-light pb-3 mr-2 w-8">
              &times;
            </button>
          ) : (
            <img src="/hamburger-menu.png" alt="Menu" className="h-10" 
            onClick={toggleMenu}/>
          )}
          </div>
        </div>
      </div>
      {profileMenuOpen && <ProfileMenu />}
    </div>
  );
};

export default NavBar;
