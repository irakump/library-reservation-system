import ProfileMenu from "./ProfileMenu";
import DesktopMenu from "./DesktopMenu";
import {useMenu} from "../../contexts/MenuContext";

const NavBar = () => {

  const {isProfileMenuOpen, toggleMenu} = useMenu();

  //const toggleMenu = () => setIsProfileMenuOpen(!isProfileMenuOpen);

  return (
    <div>
      <div className="flex flex-row justify-between items-center bg-navbar p-2">
        <a href="/" className="font-bold text-3xl">MetBook</a>
        <div className="flex flex-row gap-3 items-center h-10">
          <img src="/language-icon.png" alt="Language" className="h-8" tabIndex={0} />

          <div className="max-sm:hidden">
            <DesktopMenu />
          </div>

          <div className="sm:hidden">
          {isProfileMenuOpen ? (
            <button onClick={toggleMenu} 
            className="text-6xl font-light pb-3 mr-2 w-8">
              &times;
            </button>
          ) : (
            <button className="h-10 mt-2" onClick={toggleMenu}><img src="/hamburger-menu.png" alt="Menu" className="h-10" 
            /></button>
          )}
          </div>
        </div>
      </div>
      {isProfileMenuOpen && <ProfileMenu />}
    </div>
  );
};

export default NavBar;
