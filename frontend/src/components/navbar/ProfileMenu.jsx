import React from "react";

const ProfileMenu = () => {
  return (
    <div className="absolute max-sm:w-full sm:right-32 text-center">
      <ul className="flex flex-col w-full sm:w-50 bg-navbar border border-t-0 *:border-t *:p-2 text-2xl font-bold">
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

        <a href="/logout" className="w-full sm:hidden">
          <li>Log out →</li>
        </a>
      </ul>
    </div>
  );
};

export default ProfileMenu;
