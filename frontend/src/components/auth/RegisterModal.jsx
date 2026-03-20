import { useState } from "react";
import { registerAPICall } from "../../api/authApi";
import { useTranslation } from "react-i18next";

const RegisterModal = ({ isOpen, onClose, onSwitchToLogin }) => {
  if (!isOpen) return null;

  const { t } = useTranslation(["auth", "button", "book_card"]);

  const [nickname, setNickname] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const [errors, setErrors] = useState({
    nickname: "",
    email: "",
    password: "",
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
        alert(t("success.register_success", { ns: "auth" })); // Feedback to user after successful registration
        onSwitchToLogin(); // Close registration, open login
      })
      .catch((error) => {
        if (error.response) {
          // Display response error message (unsuccessful registration)
          console.error("Reqistration error:", error.response.data);
        } else {
          // Other errors
          console.error("Error: ", error.message);
          alert(t("error.general", { ns: "auth" }));
        }
      });
  };

  const validateRegisterForm = (register) => {
    const newErrors = {
      nickname: "",
      email: "",
      password: [],
    };

    // Nickname
    if (!register.nickname.trim()) {
      newErrors.nickname = t("error.nickname_required", { ns: "auth" });
    }

    // Email
    if (!register.email.trim()) {
      newErrors.email = t("error.email_required", { ns: "auth" });
    } else if (!isValidEmail(register.email)) {
      newErrors.email = t("error.email_format", { ns: "auth" });
    }

    // Password
    if (!register.password.trim()) {
      newErrors.password.push(t("error.password_required", { ns: "auth" }));
    } else {
      if (register.password.length < 8) {
        newErrors.password.push(t("error.password.length", { ns: "auth" }));
      }
      if (!/[a-z]/.test(register.password)) {
        newErrors.password.push(t("error.password.lowercase_letter", { ns: "auth" }));
      }
      if (!/[A-Z]/.test(register.password)) {
        newErrors.password.push(t("error.password.uppercase_letter", { ns: "auth" }));
      }
      if (!hasNumber(register.password)) {
        newErrors.password.push(t("error.password.number", { ns: "auth" }));
      }
      if (!hasSpecialCharacter(register.password)) {
        newErrors.password.push(t("error.password.special_character", { ns: "auth" }));
      }
    }

    setErrors({
      ...newErrors,
      // Set password array to string
      password: newErrors.password.join(", "),
    });

    if (
      newErrors.nickname ||
      newErrors.email ||
      newErrors.password.length > 0
    ) {
      return false;
    }

    // Successful validation
    return true;
  };

  const isValidEmail = (email) => {
    return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email);
  };

  const hasNumber = (password) => {
    return /\d/.test(password);
  };

  const hasSpecialCharacter = (password) => {
    return /[^a-zA-Z0-9]/.test(password);
  };

  return (
    <div
      className="fixed inset-0 bg-black/40 flex items-center justify-center z-50 p-4"
      onMouseDown={(e) => {
        if (e.target === e.currentTarget) {
          onClose();
        }
      }}
    >
      <div className="bg-white rounded-lg max-w-md w-full max-h-[90vh] flex flex-col overflow-hidden">
        {/* Header */}
        <div className="bg-navbar p-4 flex justify-between text-center flex-0">
          <h2 className="text-3xl font-bold text-gray-800">{t("register.title", { ns: "auth" })}</h2>

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
          {/* Nickname */}
          <div className="mb-6 mt-4">
            <label className="flex flex-row gap-3 text-gray-600 mb-2 text-sm">
              {t("register.nickname", { ns: "auth" })}*
              {errors.nickname && (
                <p className="text-red-600 font-semibold">{errors.nickname}</p>
              )}
            </label>
            <input
              type="text"
              placeholder={t("register.nickname_placeholder", { ns: "auth" })}
              onChange={(e) => setNickname(e.target.value)}
              className="w-full px-4 py-3 font-medium text-lg border-2 border-gray-300 rounded-xl focus:outline-none focus:border-blue-400"
            />
          </div>

          {/* Email */}
          <div className="mb-6 mt-4">
            <label className="flex flex-row gap-3 text-gray-600 mb-2 text-sm">
              {t("common.email", { ns: "auth" })}*
              {errors.email && (
                <p className="text-red-600 font-semibold">{errors.email}</p>
              )}
            </label>
            <input
              type="email"
              placeholder={t("common.email_placeholder", { ns: "auth" })}
              onChange={(e) => setEmail(e.target.value)}
              className="w-full px-4 py-3 font-medium text-lg border-2 border-gray-300 rounded-xl focus:outline-none focus:border-blue-400"
            />
          </div>

          {/* Password */}
          <div className="mb-12">
            <label className="flex flex-row gap-3 text-gray-600 mb-2 text-sm">
              {t("common.password", { ns: "auth" })}*
              {errors.password && (
                <p className="text-red-600 font-semibold">{errors.password}</p>
              )}
            </label>
            <input
              type="password"
              placeholder={t("common.password_placeholder", { ns: "auth" })}
              onChange={(e) => setPassword(e.target.value)}
              className="w-full px-4 py-3 font-medium text-lg border-2 border-gray-300 rounded-xl focus:outline-none focus:border-blue-400"
            />
            <p className="text-gray-600 mt-1 text-sm">
              {t("register.password_guide", { ns: "auth" })}
            </p>
          </div>

          {/* Register button */}
          <button
            className="w-full text-center bg-loginButton hover:bg-blue-500 font-semibold text-white py-3 rounded-xl mb-8"
            onClick={(e) => handleRegisterForm(e)}
          >
            {t("register", { ns: "button" })}
          </button>

          {/* Login link */}
          <div className="flex justify-center">
            <button
              onClick={onSwitchToLogin}
              className="text-center font-semibold text-gray-800 hover:text-blue-600 text-lg"
            >
              {t("login", { ns: "button" })}
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default RegisterModal;
