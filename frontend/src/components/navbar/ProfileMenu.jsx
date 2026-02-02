import React from "react";

const ProfileMenu = () => {

    // TODO: change profile page routes (all components are not separate pages, check App.jsx)

  return (
    <div className="absolute max-sm:w-full sm:right-32">
      <ul className="flex flex-col w-full sm:w-50 bg-navbar border border-t-0 *:border-t *:p-2 text-2xl font-bold">
        <li><a href="/profile">My Page</a></li>
        <li><a href="/profile/loans">Loans</a></li>
        <li><a href="/profile/reservations">Reservations</a></li>
        <li><a href="/profile/history">History</a></li>
        <li><a href="/profile/favorites">Favorites</a></li>
        <li><button>Log Out →</button></li>
      </ul>
    </div>
  );
};

export default ProfileMenu;
