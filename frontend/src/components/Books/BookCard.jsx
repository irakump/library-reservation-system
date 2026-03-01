import Button from "../buttons/Button.jsx";
import FavoriteButton from "../buttons/FavoriteButton.jsx";
import BookButtons from "../../utils/BookButtons.jsx";

const BookCard = ({ book, pageType, setOpen }) => {

  return (
    <div
      className="bg-white rounded-lg p-2 flex gap-6 shadow hover:shadow-lg hover:opacity-90 transition-all "
      onClick={() => setOpen(book)}
    >
      <div className="w-24 h-32 mt-1.5 ml-1.5 bg-gray-300 rounded shrink-0"></div>

      <div className="flex-1">
        <div className="flex justify-between items-start">
          <h3 className="font-bold text-lg">{book.title}</h3>
          <div className="text-2xl cursor-pointer hover:text-red-700">
            <FavoriteButton book={book} />
          </div>
        </div>

        <p className="text-sm mb-1 text-left">
          {book.authors
            ?.map((author) => `${author.firstName} ${author.lastName}`)
            .join(", ")}
        </p>
        <p className="text-sm mb-1 text-left ">{book.year}</p>
        <p className="text-sm mb-1 text-left capitalize">{book.genre}</p>

        <BookButtons pageType={pageType} book={book} />

      </div>
    </div>
  );
};

export default BookCard;
