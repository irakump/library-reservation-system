import { useAuth } from "../../contexts/AuthContext";

const UserInformation = () => {
  const { user, isLoggedIn } = useAuth();

  if (!isLoggedIn || !user) {
    return (
      <div className="bg-white rounded-lg p-3 shadow-sm">
        <h3 className="text-lg font-semibold mb-4 text-left">
          User Information
        </h3>
        <p className="text-sm text-gray-500">You are not logged in.</p>
      </div>
    );
  }

  const userInfo = [
    { id: "nickname", label: "Nickname:", value: user.nickname },
    { id: "email", label: "Email:", value: user.email },
    {
      id: "created",
      label: "User Created:",
      value: user.createdAt,
    },
  ];

  return (
    <>
      <div className="bg-white rounded-lg p-3 shadow-sm">
        <h3 className="text-lg font-semibold mb-4 text-left">
          User Information
        </h3>
        <div className="space-y-2">
          {userInfo.map((info) => (
            <div key={info.id} className="flex ">
              <span className="w-32  text-sm font-semibold text-left ml-2">
                {info.label}
              </span>
              <span className="text-sm font-medium">{info.value}</span>
            </div>
          ))}
        </div>
      </div>
    </>
  );
};

export default UserInformation;
