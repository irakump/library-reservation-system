import UserInformation from "../components/profile/UserInformation.jsx";
import ProfileStats from "../components/profile/ProfileStats.jsx";


const ProfilePage = () => {
  return (
    <>
      <div className="min-h-screen flex flex-col">
        <main className="flex-1 bg-background pb-12">
          <h1 className="text-2xl font-bold text-center text-heading p-7 ">
            Hello, user123!
          </h1>
          <div className="mx-auto p-4 max-w-2xl space-y-4 bg-profileBackground rounded-sm">
            <ProfileStats />

            <UserInformation />
          </div>
        </main>
      </div>
    </>
  );
};

export default ProfilePage;
