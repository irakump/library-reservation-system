import NavBar from "../components/navbar/NavBar.jsx";
import { MenuProvider } from "../contexts/MenuContext.jsx";

const BookDataPage = ({ title }) => {
  return (
    <>
      <MenuProvider>
        <NavBar />
      </MenuProvider>
      <h1>{title}</h1>
    </>
  );
};
export default BookDataPage;
