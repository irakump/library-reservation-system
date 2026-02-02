import React from "react";
import { useMenu } from "../../contexts/MenuContext";

const ProfileButton = () => {
  const {toggleMenu} = useMenu();

  return (
    <div>
        <button onClick={toggleMenu}
         className="flex flex-row gap-2 items-center"
         >Profile
            <img src="dropdown.png" alt="dropdown icon" className="h-7 w-7 mt-1" />
        </button>
      
    </div>
  );
};

export default ProfileButton;
