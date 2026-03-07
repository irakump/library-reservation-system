import { useAuth } from "../../contexts/AuthContext";

const UserInformation = () => {
  const { user, isLoggedIn } = useAuth();

  if (!isLoggedIn || !user) {
    return (
      <div className="bg-white rounded-lg p-4 sm:p-6 shadow-sm">
        <h3 className="text-lg sm:text-xl font-semibold mb-4 text-left">
          User Information
        </h3>
        <p className="text-sm sm:text-base text-gray-500">
          You are not logged in.
        </p>
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
    <div className="bg-white rounded-lg p-4 sm:p-6 shadow-sm h-fit">
      <h3 className="text-lg sm:text-xl font-semibold mb-4 text-left">
        User Information
      </h3>
      <div className="space-y-3 px-4">
        {userInfo.map((info) => (
          <div key={info.id} className="flex">
            <span className="w-32 sm:w-40 text-sm sm:text-base font-semibold text-left">
              {info.label}
            </span>
            <span className="text-sm sm:text-base font-medium ml-4 ">
              {info.value}
            </span>
          </div>
        ))}
      </div>
    </div>
  );
};

export default UserInformation;
