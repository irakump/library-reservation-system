import React from "react";

const NavBar = () => {
  return (
    <div className="flex flex-row justify-between items-center bg-navbar p-2">
      <p className="font-bold text-3xl">MetBook</p>
      <div className="flex flex-row gap-3 items-center">
        <img src="/language-icon.png" alt="Language" className="h-8" />
        <img src="/hamburger-menu.png" alt="Menu" className="h-10" />
      </div>
    </div>
  );
};

export default NavBar;
