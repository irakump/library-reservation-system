import React from "react";

import { useState } from "react";
import LoginModal from "../auth/LoginModal.jsx";
import RegisterModal from "../auth/RegisterModal.jsx";
import LogoutModal from "../auth/LogoutModal.jsx";

const LoginButton = () => {
  const [isLoginOpen, setIsLoginOpen] = useState(false);
  const [isRegisterOpen, setIsRegisterOpen] = useState(false);
  const [isLogoutOpen, setIsLogoutOpen] = useState(false);
  const [isLoggedIn, setIsLoggedIn] = useState(true); // TODO: Replace with actual authentication state

  const handleSwitchToRegister = () => {
    setIsLoginOpen(false);
    setIsRegisterOpen(true);
  };

  const handleSwitchToLogin = () => {
    setIsRegisterOpen(false);
    setIsLoginOpen(true);
  };

  const handleLogout = () => {
    setIsLoggedIn(false);
    setIsLogoutOpen(false);
    // TODO: Add actual logout logic here
  };

  return (
    <>
      <div>
        {/* {isLoggedIn ? ( */}
        <button className="w-30" onClick={() => setIsLogoutOpen(true)}>
          Log out
        </button>
        {/* ) : ( */}
        <button className="w-30" onClick={() => setIsLoginOpen(true)}>
          Login
        </button>
        {/* )} */}
      </div>
      <LoginModal
        isOpen={isLoginOpen}
        onClose={() => setIsLoginOpen(false)}
        onSwitchToRegister={handleSwitchToRegister}
      />

      <RegisterModal
        isOpen={isRegisterOpen}
        onClose={() => setIsRegisterOpen(false)}
        onSwitchToLogin={handleSwitchToLogin}
      />

      <LogoutModal
        isOpen={isLogoutOpen}
        onClose={() => setIsLogoutOpen(false)}
        onConfirm={handleLogout}
      />
    </>
  );
};

export default LoginButton;
