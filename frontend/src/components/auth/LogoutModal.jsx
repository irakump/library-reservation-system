import { useAuth } from "../../contexts/AuthContext";

const LogoutModal = ({ isOpen, onClose, onConfirm }) => {
  if (!isOpen) return null;

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
          aria-label="Close modal"
        >
          ✕
        </button>

        <div className="bg-white rounded-lg p-6 mt-2">
          <h2 className="text-xl font-medium text-center mb-6">
            Are you sure you
            <br /> want to log out?
          </h2>

          <div className="flex justify-center gap-10">
            <button
              onClick={onClose}
              className="bg-actionButton hover:bg-actionButtonHover font-medium text-base px-5 py-2 rounded-lg"
            >
              Cancel
            </button>
            <button
              onClick={handleLogoutClick}
              className="bg-actionButton hover:bg-actionButtonHover font-medium text-base px-5 py-2 rounded-lg"
            >
              Log out
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default LogoutModal;
