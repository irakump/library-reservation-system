import React from "react";

const ProfileMenu = () => {

  return (
    <div className="absolute max-sm:w-full sm:right-32 text-center">
      <ul className="flex flex-col w-full sm:w-50 bg-navbar border border-t-0 *:border-t *:p-2 text-2xl font-bold">
        <li><a href="/profile">My Page</a></li>
        <li><a href="/profile/loans">Loans</a></li>
        <li><a href="/profile/reservations">Reservations</a></li>
        <li><a href="/profile/history">History</a></li>
        <li><a href="/profile/favorites">Favorites</a></li>
        <li className="sm:hidden"><button>Log Out →</button></li>
      </ul>
    </div>
  );
};

export default ProfileMenu;
