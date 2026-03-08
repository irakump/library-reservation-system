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
      <div className="bg-tag rounded-lg p-6 max-w-sm w-full mx-4">
        <div className="flex justify-end mb-2">
          <button
            onClick={onClose}
            className="text-2xl font-bold text-gray-800 hover:text-black"
          >
            ✕
          </button>
        </div>

        <div className="bg-white rounded-lg p-4">
          <h2 className="text-xl font-bold text-center mb-6">
            Are you sure you
            <br /> want to log out?
          </h2>

          <div className="flex justify-center gap-8">
            <button
              onClick={onClose}
              className="bg-filter hover:bg-blue-300 font-medium text-lg px-6 py-2 rounded-lg"
            >
              Cancel
            </button>
            <button
              onClick={handleLogoutClick}
              className="bg-filter hover:bg-blue-300 font-medium text-lg px-6 py-2 rounded-lg"
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
