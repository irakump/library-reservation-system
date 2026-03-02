import {
  Children,
  createContext,
  useContext,
  useState,
  useEffect,
} from "react";

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [isLoginOpen, setIsLoginOpen] = useState(false);
  const [isRegisterOpen, setIsRegisterOpen] = useState(false);
  const [isLogoutOpen, setIsLogoutOpen] = useState(false);
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [user, setUser] = useState(null); // Store user info

  // Check if user is already logged in (on app load)
  useEffect(() => {
    const token = localStorage.getItem("token");
    const userId = localStorage.getItem("userId");
    const email = localStorage.getItem("email");
    const nickname = localStorage.getItem("nickname");
    const role = localStorage.getItem("role");
    const createdAt = localStorage.getItem("createdAt");

    if (token && userId) {
      setIsLoggedIn(true);
      setUser({ userId, email, nickname, role, token, createdAt });
    }
  }, []);

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

  // Login function - stores token and user info
  const handleLogin = (loginResponse) => {
    const { token, userId, email, nickname, role, createdAt } = loginResponse;

    // Store in localStorage
    localStorage.setItem("token", token);
    localStorage.setItem("userId", userId);
    localStorage.setItem("email", email);
    localStorage.setItem("nickname", nickname);
    localStorage.setItem("role", role);
    localStorage.setItem("createdAt", createdAt);

    // Update state
    setIsLoggedIn(true);
    setUser({ userId, email, nickname, role, token, createdAt });
    closeLogin();
  };

  const handleLogout = () => {
    // Clear localStorage
    localStorage.removeItem("token");
    localStorage.removeItem("userId");
    localStorage.removeItem("email");
    localStorage.removeItem("nickname");
    localStorage.removeItem("role");
    localStorage.removeItem("createdAt");

    // Update state
    setIsLoggedIn(false);
    setUser(null);
    closeLogout();
  };

  return (
    <AuthContext.Provider
      value={{
        isLoginOpen,
        isRegisterOpen,
        isLogoutOpen,
        isLoggedIn,
        user,
        openLogin,
        closeLogin,
        openRegister,
        closeRegister,
        openLogout,
        closeLogout,
        handleSwitchToRegister,
        handleSwitchToLogin,
        handleLogin,
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
