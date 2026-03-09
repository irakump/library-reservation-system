import React from "react";
import { useMenu } from "../../contexts/MenuContext";
import { useAuth } from "../../contexts/AuthContext";

const ProfileButton = () => {
  const { toggleMenu } = useMenu();
  const { isLoggedIn } = useAuth();

  return (
    isLoggedIn && (
    <div>
        <button onClick={toggleMenu}
         className="flex flex-row gap-2 items-center w-50 justify-center text-xl mt-1 hover:cursor-pointer"
         >Profile
            <img src="/down.png" alt="dropdown icon" className="h-4 mt-1" />
        </button>
      
    </div>
));
};

export default ProfileButton;
