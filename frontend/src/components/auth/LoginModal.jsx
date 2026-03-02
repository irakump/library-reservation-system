import { useEffect, useState } from "react";
import { loginAPICall } from "../../api/authApi";
import { useAuth } from "../../contexts/AuthContext";

const LoginModal = ({ isOpen, onClose, onSwitchToRegister }) => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const { handleLogin, isLoggedIn } = useAuth();

  // Clear form when modal opens/closes
  useEffect(() => {
    if (!isOpen) {
      setEmail("");
      setPassword("");
      setError("");
    }
  }, [isOpen]);

  // Don't show modal if already logged in
  if (!isOpen || isLoggedIn) return null;

  const handleLoginForm = (e) => {
    e.preventDefault();

    // Validates inputs
    if (!email || !password) {
      setError("Please enter both email and password");
      return;
    }

    setError(""); // Clear previous errors

    const login = { email, password };

    console.log("Attempting login with: ", { email });

    // call login API
    loginAPICall(login)
      .then((response) => {
        console.log("Login successful: ", response.data);

        // Use AuthContext's handlelogin to store token and update state
        handleLogin(response.data);

        // Clear form
        setEmail("");
        setPassword("");
        setError("");
      })
      .catch((error) => {
        if (error.response) {
          // Backend returned an error
          setError(error.response.data);
        } else {
          setError("Login failed. Please try again.");
        }
        console.error("Login error: ", error);
      });
  };

  return (
    <div
      className="fixed inset-0  bg-black/40 flex items-center justify-center z-50"
      onClick={onClose}
    >
      <div
        className="bg-white rounded-lg max-w-md w-full mx-4 overflow-hidden"
        onClick={(e) => e.stopPropagation()}
      >
        {/* Header */}
        <div className="bg-navbar p-4 flex justify-between text-center">
          <h2 className="text-3xl font-bold text-gray-800">Login</h2>

          <button
            onClick={onClose}
            className="text-2xl font-bold text-gray-800 hover:text-black"
            aria-label="Close modal"
          >
            ✕
          </button>
        </div>

        {/* Form */}
        <div className="p-8">
          {/* Error message */}
          {error && (
            <div className="mb-4 p-3 bg-red-100 border border-red-400 text-red-700 rounded">
              {error}
            </div>
          )}

          {/* Email */}
          <div className="mb-6 mt-4">
            <label className="block text-gray-600 mb-2 text-sm">Email</label>
            <input
              type="email"
              placeholder="example@gmail.com"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              className="w-full px-4 py-3 font-medium text-lg border-2 border-gray-300 
             rounded-xl focus:outline-none focus:border-blue-400"
            />
          </div>

          {/* Password */}
          <div className="mb-12">
            <label className="block text-gray-600 mb-2 text-sm">Password</label>
            <input
              type="password"
              placeholder="Enter your Password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              className="w-full px-4 py-3 font-medium text-lg border-2 border-gray-300 
             rounded-xl focus:outline-none focus:border-blue-400"
            />
          </div>

          {/* Login button */}
          <button
            onClick={handleLoginForm}
            className="w-full text-center bg-loginButton hover:bg-blue-500 font-semibold text-white py-3 rounded-xl mb-8"
          >
            Login
          </button>

          {/* Register link */}
          <div className="flex justify-center">
            <button
              onClick={onSwitchToRegister}
              className="text-center font-semibold text-gray-800 hover:text-blue-600 text-lg"
            >
              Register
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default LoginModal;
