import { useAuth } from "../../contexts/AuthContext";

const LoginButton = () => {
  const { isLoggedIn, openLogin, openLogout } = useAuth();

  return (
    <div>
      {isLoggedIn ? (
        <button className="w-30 text-xl hover:cursor-pointer" onClick={openLogout}>
          Log out
        </button>
      ) : (
        <button className="w-30 text-xl hover:cursor-pointer" onClick={openLogin}>
          Login
        </button>
      )}
    </div>
  );
};

export default LoginButton;
