import UserInformation from "../components/profile/UserInformation.jsx";
import Navbar from "../components/navbar/Navbar.jsx";

const ProfilePage = () => {
  return (
    <>
      <Navbar />
      <h1 className="text-2xl font-bold text-center text-heading p-4">
        Hello, user123!
      </h1>
      <div className="mx-auto p-4 space-y-4 bg-profileBackground ">
        <UserInformation />
      </div>
    </>
  );
};

export default ProfilePage;
