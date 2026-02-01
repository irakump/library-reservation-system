import React from "react";

const ProfileMenu = () => {
    
    // TODO: open profile pages by clicking li elements
    const openPage = () => {
        console.log('MyPage pressed!');
    }

  return (
    <div>
      <ul className="flex flex-col w-full bg-navbar border border-t-0 *:border-t *:p-2 text-3xl font-bold">
        <li onClick={openPage}>My Page</li>
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
