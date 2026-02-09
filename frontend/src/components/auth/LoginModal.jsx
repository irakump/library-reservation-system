const LoginModal = ({ isOpen, onClose, onSwitchToRegister }) => {
  if (!isOpen) return null;

  return (
    <div
      className="fixed inset-0 flex items-center justify-center bg-black/40 z-50"
      onClick={onClose}
    >
      <div
        className="bg-white rounded-lg max-w-md w-full mx-4 overflow-hidden"
        onClick={(e) => e.stopPropagation()}
      >
        {/* Header */}
        <div className="bg-navbar p-6">
          <h2 className="text-3xl font-bold text-gray-800">Login</h2>
        </div>

        {/* Form */}
        <div className="p-8"></div>
        {/* Email */}
        <div className="mb-6">
          <label className="block text-gray-600 mb-2 text-sm">Email</label>
          <input
            type="email"
            placeholder="example@gmail.com"
            className="w-full py-3 px-4 font-medium text-lg border-2 border-gray-300 
             rounded-xl focus:outline-none focus:border-blue-400"
          />
        </div>

        {/* Password */}
        <div className="mb-6">
          <label className="block text-gray-600 mb-2 text-sm">Password</label>
          <input
            type="password"
            placeholder="Enter your Password"
            className="w-full py-3 px-4 font-medium text-lg border-2 border-gray-300 
             rounded-xl focus:outline-none focus:border-blue-400"
          />
        </div>

        {/**/}
      </div>
    </div>
  );
};

export default LoginModal;
