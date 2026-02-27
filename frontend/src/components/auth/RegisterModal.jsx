import { useState } from "react";
import { registerAPICall } from "../../api/AuthApi";

const RegisterModal = ({ isOpen, onClose, onSwitchToLogin }) => {
  if (!isOpen) return null;

  const [nickname, setNickname] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const [errors, setErrors] = useState({
    nickname: "",
    email: "",
    password: ""
  });

  const handleRegisterForm = (e) => {
    e.preventDefault();
    const register = { nickname, email, password };

    if (!validateRegisterForm(register)) {
      return;
    }

    // Send data to backend after successful validation
    registerAPICall(register)
      .then((response) => {
        console.log("Success:", response.data);
        alert("Successful registration. Please log in."); // Feedback to user after successful registration
        onSwitchToLogin(); // Close registration, open login
      })
      .catch((error) => {
        if (error.response) {
          // Display response error message (unsuccessful registration)
          console.error("Reqistration error:", error.response.data);
        } else {
          // Other errors
          console.error("Error: ", error.message);
          alert("Error occurred. Please try again.");
        }
      });
  };

  const validateRegisterForm = (register) => {
    const newErrors = {
      nickname: "",
      email: "",
      password: "",
    };

    if (!register.nickname) {
      newErrors.nickname = "Nickname is required";
    } 
    
    if (!register.email) {
      newErrors.email = "Email is required";
    }
    
    if (!register.password) {
      newErrors.password = "Password is required";
    }

    setErrors(newErrors);

    if (newErrors.nickname || newErrors.email || newErrors.password) {
      return false;
    }

    // Successful validation
    return true;
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
          <h2 className="text-3xl font-bold text-gray-800">Register</h2>

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
          {/* Nickname */}
          <div className="mb-6 mt-4">
            <label className="flex flex-row gap-3 text-gray-600 mb-2 text-sm">
              Nickname*{" "}
              {errors.nickname && (
                <p className="text-red-600 font-semibold">{errors.nickname}</p>
              )}
            </label>
            <input
              type="text"
              placeholder="Enter your nickname"
              onChange={(e) => setNickname(e.target.value)}
              className="w-full px-4 py-3 font-medium text-lg border-2 border-gray-300 rounded-xl focus:outline-none focus:border-blue-400"
            />
          </div>

          {/* Email */}
          <div className="mb-6 mt-4">
            <label className="block text-gray-600 mb-2 text-sm">
              Email*
              {errors.email && (
                <p className="text-red-600 font-semibold">{errors.email}</p>
              )}
            </label>
            <input
              type="email"
              placeholder="example@gmail.com"
              onChange={(e) => setEmail(e.target.value)}
              className="w-full px-4 py-3 font-medium text-lg border-2 border-gray-300 rounded-xl focus:outline-none focus:border-blue-400"
            />
          </div>

          {/* Password */}
          <div className="mb-12">
            <label className="block text-gray-600 mb-2 text-sm">
              Password*
              {errors.password && (
                <p className="text-red-600 font-semibold">{errors.password}</p>
              )}
            </label>
            <input
              type="password"
              placeholder="Enter your Password"
              onChange={(e) => setPassword(e.target.value)}
              className="w-full px-4 py-3 font-medium text-lg border-2 border-gray-300 rounded-xl focus:outline-none focus:border-blue-400"
            />
          </div>

          {/* Register button */}
          <button
            className="w-full text-center bg-loginButton hover:bg-blue-500 font-semibold text-white py-3 rounded-xl mb-8"
            onClick={(e) => handleRegisterForm(e)}
          >
            Register
          </button>

          {/* Login link */}
          <div className="flex justify-center">
            <button
              onClick={onSwitchToLogin}
              className="text-center font-semibold text-gray-800 hover:text-blue-600 text-lg"
            >
              Login
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default RegisterModal;
