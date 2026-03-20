import { useTranslation } from "react-i18next";
import { useAuth } from "../../contexts/AuthContext";


const LogoutModal = ({ isOpen, onClose, onConfirm }) => {
  if (!isOpen) return null;

  const {t} = useTranslation(["auth", "button", "book_card"]);

  const { handleLogout } = useAuth();

  const handleLogoutClick = () => {
    handleLogout(); // Clear localstorage and update state
    onConfirm();
  };

  return (
    <div
      className="fixed inset-0 bg-black/40 flex items-center justify-center z-50"
      onMouseDown={(e) => {
        if (e.target === e.currentTarget) {
          onClose();
        }
      }}
    >
      <div className="relative bg-tag rounded-xl p-6 max-w-sm w-full mx-4 shadow-lg">
        <button
          onClick={onClose}
          className="absolute top-1 right-2 w-8 h-8 flex items-center justify-center text-xl font-bold text-gray-700 hover:text-gray-900 "
          aria-label={t("close_modal_label", { ns: "book_card" })}
        >
          ✕
        </button>

        <div className="bg-white rounded-lg p-6 mt-2">
          <h2 className="text-xl font-medium text-center mb-6">
            {t("logout.confirm_logout", { ns: "auth" })}
          </h2>

          <div className="flex justify-center gap-10">
            <button
              onClick={onClose}
              className="bg-actionButton hover:bg-actionButtonHover font-medium text-base px-5 py-2 rounded-lg"
            >
              {t("cancel", { ns: "button" })}
            </button>
            <button
              onClick={handleLogoutClick}
              className="bg-actionButton hover:bg-actionButtonHover font-medium text-base px-5 py-2 rounded-lg"
            >
              {t("logout", { ns: "button" })}
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default LogoutModal;
