import UserInformation from "../components/profile/UserInformation.jsx";
import NavBar from "../components/navbar/NavBar.jsx";
import { MenuProvider } from "../contexts/MenuContext";
import ProfileStats from "../components/profile/ProfileStats.jsx";
import Footer from "../components/footer/Footer";

const ProfilePage = () => {
  return (
    <>
      <div className="min-h-screen flex flex-col">
        <MenuProvider>
          <NavBar />
        </MenuProvider>
        <main className="flex-1 bg-background pb-12">
          <h1 className="text-2xl font-bold text-center text-heading p-7 ">
            Hello, user123!
          </h1>
          <div className="mx-auto p-4 max-w-2xl space-y-4 bg-profileBackground rounded-sm">
            <ProfileStats />

            <UserInformation />
          </div>
        </main>
        <Footer />
      </div>
    </>
  );
};

export default ProfilePage;
