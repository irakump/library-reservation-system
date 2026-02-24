import { useState } from "react";
import { registerAPICall } from "../../api/AuthApi";

const RegisterModal = ({ isOpen, onClose, onSwitchToLogin }) => {
  if (!isOpen) return null;

  const [nickname, setNickname] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const handleRegisterForm = (e) => {
    e.preventDefault();
    const register = { nickname, email, password };

    // Send data to backend
    registerAPICall(register)
      .then((response) => {
        console.log("Success:", response.data);
        onClose(); // Close modal only when registration is successful
      })
      .catch((error) => {
        if (error.response) {
          // Display response error message (unsuccessful registration)
          console.error("Reqistration error:", error.response.data);
        } else {
          // Other errors
          console.error("Error: ", error.message);
        }
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
            <label className="block text-gray-600 mb-2 text-sm">Nickname</label>
            <input
              type="text"
              placeholder="Enter your nickname"
              onChange={(e) => setNickname(e.target.value)}
              className="w-full px-4 py-3 font-medium text-lg border-2 border-gray-300 rounded-xl focus:outline-none focus:border-blue-400"
            />
          </div>

          {/* Email */}
          <div className="mb-6 mt-4">
            <label className="block text-gray-600 mb-2 text-sm">Email</label>
            <input
              type="email"
              placeholder="example@gmail.com"
              onChange={(e) => setEmail(e.target.value)}
              className="w-full px-4 py-3 font-medium text-lg border-2 border-gray-300 rounded-xl focus:outline-none focus:border-blue-400"
            />
          </div>

          {/* Password */}
          <div className="mb-12">
            <label className="block text-gray-600 mb-2 text-sm">Password</label>
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
