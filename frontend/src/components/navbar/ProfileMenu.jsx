import React from "react";

const ProfileMenu = () => {

    // TODO: change profile page routes (all components are not separate pages)
    const openPage = () => {
        console.log('MyPage pressed!');
    }

    // TODO: set profile menu fixed under profile button

  return (
    <div className="flex sm:justify-end">
      <ul className="flex flex-col w-full sm:w-55 bg-navbar border border-t-0 *:border-t *:p-2 text-2xl font-bold">
        <li onClick={openPage}><a href="/profile">My Page</a></li>
        <li><a href="/profile/loans">Loans</a></li>
        <li><a href="/profile/reservations">Reservations</a></li>
        <li><a href="/profile/history">History</a></li>
        <li><a href="/profile/favorites">Favorites</a></li>
        <li>Log Out →</li>
      </ul>
    </div>
  );
};

export default ProfileMenu;
