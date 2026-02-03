import UserInformation from "../components/profile/UserInformation.jsx";
import NavBar from "../components/navbar/NavBar.jsx";
import { MenuProvider } from "../contexts/MenuContext";
import ProfileStats from "../components/profile/ProfileStats.jsx";
import Footer from "../components/footer/Footer";

const ProfilePage = () => {
  return (
    <>
      <MenuProvider>
        <NavBar />
      </MenuProvider>
      <div className="bg-background min-h-screen">
        <h1 className="text-2xl font-bold text-center text-heading p-7 ">
          Hello, user123!
        </h1>
        <div className="mx-auto p-4 space-y-4 bg-profileBackground rounded-sm">
          <ProfileStats />

          <UserInformation />
        </div>
        <Footer />
      </div>
    </>
  );
};

export default ProfilePage;
