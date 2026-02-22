import { Children, createContext, useContext, useState } from "react";

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [isLoginOpen, setIsLoginOpen] = useState(false);
  const [isRegisterOpen, setIsRegisterOpen] = useState(false);
  const [isLogoutOpen, setIsLogoutOpen] = useState(false);
  const [isLoggedIn, setIsLoggedIn] = useState(false); // TODO: Replace with actual auth state

  const openLogin = () => setIsLoginOpen(true);
  const closeLogin = () => setIsLoginOpen(false);

  const openRegister = () => setIsRegisterOpen(true);
  const closeRegister = () => setIsRegisterOpen(false);

  const openLogout = () => setIsLogoutOpen(true);
  const closeLogout = () => setIsLogoutOpen(false);

  const handleSwitchToRegister = () => {
    closeLogin();
    openRegister();
  };

  const handleSwitchToLogin = () => {
    closeRegister();
    openLogin();
  };

  const handleLogout = () => {
    setIsLoggedIn(false);
    closeLogout();
    // TODO: add actual logout logic
  };

  return (
    <AuthContext.Provider
      value={{
        isLoginOpen,
        isRegisterOpen,
        isLogoutOpen,
        isLoggedIn,
        openLogin,
        closeLogin,
        openRegister,
        closeRegister,
        openLogout,
        closeLogout,
        handleSwitchToRegister,
        handleSwitchToLogin,
        handleLogout,
      }}
    >
      {children}
    </AuthContext.Provider>
  );
};

const useAuth = () => {
  const context = useContext(AuthContext);
  if (!context) {
    throw new Error("useAuth must be used within Authprovider");
  }
  return context;
};

export { useAuth, AuthContext };
