import UserInformation from "../components/profile/UserInformation.jsx";
import Navbar from "../components/navbar/Navbar.jsx";
import ProfileStats from "../components/profile/ProfileStats.jsx";

const ProfilePage = () => {
  return (
    <>
      <Navbar />
      <div className="bg-background min-h-screen">
        <h1 className="text-2xl font-bold text-center text-heading p-7 ">
          Hello, user123!
        </h1>
        <div className="mx-auto p-4 space-y-4 bg-profileBackground ">
          <ProfileStats />

          <UserInformation />
        </div>
        <img
          src="/book-icon.svg"
          alt="Book Icon"
          className="mx-auto h-30 p-2 "
        />
      </div>
    </>
  );
};

export default ProfilePage;
