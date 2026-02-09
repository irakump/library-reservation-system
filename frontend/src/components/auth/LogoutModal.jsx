const LogoutModal = ({ isOpen, onClose, onConfirm }) => {
  if (!open) return null;
  return (
    <div
      className="fixed inset-0 bg-black/40 flex items-center justify-center z-50"
      onClick={onClose}
    >
      <div
        className="bg-blue-200 rounded-lg p-6 max-w-sm w-full mx-4"
        onClick={(e) => e.stopPropagation()}
      >
        <div className="flex justify-end mb-2">
          <button
            onClick={onClose}
            className="text-2xl font-bold hover:text-gray-600"
          >
            ✕
          </button>
        </div>
      </div>
    </div>
  );
};

export default LogoutModal;
