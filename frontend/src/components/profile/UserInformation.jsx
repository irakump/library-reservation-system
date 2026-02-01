const UserInformation = () => {
  const userInfo = [
    { id: "nickname", label: "Nickname:", value: "User123" },
    { id: "email", label: "Email:", value: "user123@metropolia.fi" },
    { id: "created", label: "User Created:", value: "1.1.2024" },
  ];

  return (
    <>
      <div className="bg-white rounded-lg p-3 shadow-sm">
        <h3 className="text-lg font-semibold mb-4 text-left">
          User Information
        </h3>
        <div className="space-y-2">
          {userInfo.map((info) => (
            <div key={info.id} className="flex gap-4">
              <span className="w-32 text-sm font-semibold">{info.label}</span>
              <span className="text-sm font-medium break-all">
                {info.value}
              </span>
            </div>
          ))}
        </div>
      </div>
    </>
  );
};

export default UserInformation;
