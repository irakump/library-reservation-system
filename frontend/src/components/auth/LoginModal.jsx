import { useEffect, useState } from "react";
import { loginAPICall } from "../../api/authApi";
import { useAuth } from "../../contexts/AuthContext";
import { useTranslation } from "react-i18next";
import PropTypes from "prop-types";

const LoginModal = ({ isOpen, onClose, onSwitchToRegister }) => {
  const { t } = useTranslation(["auth", "button", "book_card"]);

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
      setError(t("error.empty_email_password", { ns: "auth" }));
      return;
    }

    setError(""); // Clear previous errors

    const login = { email, password };

    // call login API
    loginAPICall(login)
      .then((response) => {
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
          setError(t("error.login_fail", { ns: "auth" }));
        }
        console.error("Login error: ", error);
      });
  };

  return (
    <div
      className="fixed inset-0  bg-black/40 flex items-center justify-center z-50 p-4"
      onMouseDown={(e) => {
        if (e.target === e.currentTarget) {
          onClose();
        }
      }}
    >
      <div className="bg-white rounded-lg max-w-md w-full max-h-[90vh] flex flex-col overflow-hidden">
        {/* Header */}
        <div className="bg-navbar p-4 flex justify-between text-center flex-0">
          <h2 className="text-3xl font-bold text-gray-800">{t("login.title", { ns: "auth" })}</h2>

          <button
            onClick={onClose}
            className="text-2xl font-bold text-gray-800 hover:text-black"
            aria-label={t("close_modal_label", { ns: "book_card" })}
          >
            ✕
          </button>
        </div>

        {/* Form */}
        <div className="p-8 overflow-y-auto">
          {/* Error message */}
          {error && (
            <div className="mb-4 p-3 bg-red-100 border border-red-400 text-red-700 rounded">
              {error}
            </div>
          )}

          {/* Email */}
          <div className="mb-6 mt-4">
            <label className="block text-gray-600 mb-2 text-sm">{t("common.email", { ns: "auth" })}</label>
            <input
              type="email"
              placeholder={t("common.email_placeholder", { ns: "auth" })}
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              className="w-full px-4 py-3 font-medium text-lg border-2 border-gray-300 
             rounded-xl focus:outline-none focus:border-blue-400"
            />
          </div>

          {/* Password */}
          <div className="mb-12">
            <label className="block text-gray-600 mb-2 text-sm">{t("common.password", { ns: "auth" })}</label>
            <input
              type="password"
              placeholder={t("common.password_placeholder", { ns: "auth" })}
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
            {t("login", { ns: "button" })}
          </button>

          {/* Register link */}
          <div className="flex justify-center">
            <button
              onClick={onSwitchToRegister}
              className="text-center font-semibold text-gray-800 hover:text-blue-600 text-lg"
            >
              {t("register", { ns: "button" })}
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};

LoginModal.propTypes = {
  isOpen: PropTypes.bool.isRequired,
  onClose: PropTypes.func.isRequired,
  onSwitchToRegister: PropTypes.func.isRequired,
};

export default LoginModal;
