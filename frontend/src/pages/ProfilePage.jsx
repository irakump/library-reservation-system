import UserInformation from "../components/profile/UserInformation.jsx";
import ProfileStats from "../components/profile/ProfileStats.jsx";
import { useAuth } from "../contexts/AuthContext";
import { useTranslation } from "react-i18next";

const ProfilePage = () => {
  const { user, isLoggedIn } = useAuth();
  const { t } = useTranslation("profile");

  return (
    <div className="min-h-screen flex flex-col">
      <main className="flex-1 bg-background pb-12">
        <h1 className="text-2xl sm:text-3xl font-bold text-center text-heading py-6 sm:py-8">
          {isLoggedIn && user ? t("my_page.greeting_user", { name: user.nickname }) : t("my_page.greeting_guest")}
        </h1>
        <div className="mx-auto px-4 max-w-md sm:max-w-lg lg:max-w-2xl">
          <div className="space-y-6 bg-profileBackground rounded-lg p-4 sm:p-6 ">
            <ProfileStats />

            <UserInformation />
          </div>
        </div>
      </main>
    </div>
  );
};

export default ProfilePage;
