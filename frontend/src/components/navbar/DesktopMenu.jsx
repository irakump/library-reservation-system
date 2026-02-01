import React from "react";
import LoginButton from "./LoginButton";

const DesktopMenu = () => {
  return (
    <div className="flex flex-row gap-10 pl-5 pr-10 font-bold text-2xl">
      <div>Browse</div>
      <div>Profile</div>
      <LoginButton />
    </div>
  );
};

export default DesktopMenu;
