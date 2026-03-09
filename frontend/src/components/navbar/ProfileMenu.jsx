import React from "react";
import { useAuth } from "../../contexts/AuthContext";
import { useMenu } from "../../contexts/MenuContext";

const ProfileMenu = () => {
  const { isLoggedIn, openLogin, openLogout } = useAuth();
  const { toggleMenu } = useMenu();

  const handleAuthClick = () => {
    toggleMenu();
    if (isLoggedIn) {
      openLogout();
    } else {
      openLogin();
    }
  };

  return (
    
    <div className="absolute max-sm:w-full sm:right-32 text-center z-50">
      {isLoggedIn ? (
      <ul className="flex flex-col w-full sm:w-50 bg-navbar border border-t-0 *:border-t *:p-2 text-xl font-bold">
        <a href="/profile" className="w-full">
          <li>My Page</li>
        </a>

        <a href="/profile/loans" className="w-full">
          <li>Loans</li>
        </a>

        <a href="/profile/reservations" className="w-full">
          <li>Reservations</li>
        </a>

        <a href="/profile/history" className="w-full">
          <li>History</li>
        </a>

        <a href="/profile/favorites" className="w-full">
          <li>Favorites</li>
        </a>

        <button onClick={handleAuthClick} className="w-full sm:hidden hover:cursor-pointer">
          {isLoggedIn ? "Log out" : "Login"}
        </button>
      </ul>) : (
        <ul className="flex flex-col w-full sm:w-50 bg-navbar border border-t-0 *:border-t *:p-2 text-xl font-bold">
          <button onClick={handleAuthClick} className="w-full sm:hidden hover:cursor-pointer">
          {isLoggedIn ? "Log out" : "Login"}
        </button>
        </ul>)}
    </div>
  );
};

export default ProfileMenu;
