import React from "react";
import LoginButton from "./LoginButton";
import ProfileButton from "./ProfileButton";

const DesktopMenu = () => {
  return (
    <div className="flex flex-row font-bold text-2xl">
      <ProfileButton />
      <LoginButton />

    </div>
  );
};

export default DesktopMenu;
