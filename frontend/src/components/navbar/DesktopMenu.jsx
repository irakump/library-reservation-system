import React from "react";
import LoginButton from "./LoginButton";
import ProfileButton from "./ProfileButton";

const DesktopMenu = () => {
  return (
    <div className="flex flex-row gap-10 md:gap-15 pl-5 pr-10 md:px-10 font-bold text-2xl">
      <ProfileButton />
      <LoginButton />

    </div>
  );
};

export default DesktopMenu;
