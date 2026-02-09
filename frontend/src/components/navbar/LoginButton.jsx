import React from "react";

import { useState } from "react";
import LoginModal from "../auth/LoginModal.jsx";
import RegisterModal from "../auth/RegisterModal.jsx";

const LoginButton = () => {
  const [isLoginOpen, setIsLoginOpen] = useState(false);
  const [isRegisterOpen, setIsRegisterOpen] = useState(false);
  const [isLoggedIn, setIsLoggedIn] = useState(false); // TODO: Replace with actual authentication state

  const handleSwitchToRegister = () => {
    setIsLoginOpen(false);
    setIsRegisterOpen(true);
  };

  const handleSwitchToLogin = () => {
    setIsRegisterOpen(false);
    setIsLoginOpen(true);
  };

  return (
    <>
      <div>
        {isLoggedIn ? (
          <button className="w-30">Log out</button>
        ) : (
          <button className="w-30" onClick={() => setIsLoginOpen(true)}>
            Login
          </button>
        )}
      </div>
      <LoginModal
        isOpen={isLoginOpen}
        onClose={() => setIsLoginOpen(false)}
        onSwitchToRegister={handleSwitchToRegister}
      ></LoginModal>

      <RegisterModal
        isOpen={isRegisterOpen}
        onClose={() => setIsRegisterOpen(false)}
        onSwitchToLogin={handleSwitchToLogin}
      ></RegisterModal>
    </>
  );
};

export default LoginButton;
