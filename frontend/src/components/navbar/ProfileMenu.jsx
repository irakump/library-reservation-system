import React from "react";

const ProfileMenu = () => {
  return (
    <div>
      <ul className="flex flex-col w-full bg-navbar border border-t-0 *:border-t *:p-2 text-3xl font-bold">
        <li>My Page</li>
        <li>Loans</li>
        <li>Reservations</li>
        <li>History</li>
        <li>Favorites</li>
        <li>Log Out →</li>
      </ul>
    </div>
  );
};

export default ProfileMenu;
