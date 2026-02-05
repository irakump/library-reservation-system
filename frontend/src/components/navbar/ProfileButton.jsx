import React from "react";
import { useMenu } from "../../contexts/MenuContext";

const ProfileButton = () => {
  const {toggleMenu} = useMenu();

  return (
    <div>
        <button onClick={toggleMenu}
         className="flex flex-row gap-2 items-center w-50 justify-center"
         >Profile
            <img src="down.png" alt="dropdown icon" className="h-5 mt-1" />
        </button>
      
    </div>
  );
};

export default ProfileButton;
