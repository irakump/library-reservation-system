import { useTranslation } from "react-i18next";
import { useAuth } from "../../contexts/AuthContext";
import {localizeDate} from "../../utils/DateUtils.js";

const UserInformation = () => {
  const { user, isLoggedIn } = useAuth();
  const { t } = useTranslation("profile");

  if (!isLoggedIn || !user) {
    return (
      <div className="bg-white rounded-lg p-4 sm:p-6 shadow-sm">
        <h3 className="text-lg sm:text-xl font-semibold mb-4 text-start">
          {t("my_page.user_information")}
        </h3>
        <p className="text-sm sm:text-base text-gray-500">
          {t("my_page.not_logged_in_info")}
        </p>
      </div>
    );
  }

  const userInfo = [
    { id: "nickname", label: t("my_page.nickname"), value: user.nickname },
    { id: "email", label: t("my_page.email"), value: user.email },
    {
      id: "created",
      label: t("my_page.user_creation"),
      value: localizeDate(user.createdAt)
    },
  ];

  return (
    <div className="bg-white rounded-lg p-4 sm:p-6 shadow-sm h-fit">
      <h3 className="text-lg sm:text-xl font-semibold mb-4 text-start">
        {t("my_page.user_information")}
      </h3>
      <div className="space-y-3 px-4">
        {userInfo.map((info) => (
          <div key={info.id} className="flex">
            <span className="w-32 sm:w-40 text-sm sm:text-base font-semibold text-start">
              {info.label}
            </span>
            <span className="text-sm sm:text-base font-medium ms-4 ">
              {info.value}
            </span>
          </div>
        ))}
      </div>
    </div>
  );
};

export default UserInformation;
