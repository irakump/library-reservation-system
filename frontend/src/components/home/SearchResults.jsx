import BookInfo from './BookInfo';
import BookModal from './BookModal';
import SearchResultsNavigation from './SearchResultsNavigation';

export const SearchResults = () => {

  const books = [{id: 1, name: "nimi", author: "author", genre: "genre", year: "year", availibility: "available"}, {id: 2,name: "nimi", author: "author", genre: "genre", year: "year", availibility: "available"}, {id: 3, name: "nimi", author: "author", genre: "genre", year: "year", availibility: "available"}, {id: 4, name: "nimi", author: "author", genre: "genre", year: "year", availibility: "available"}]  //mockdata

  return (
    <>
      <div>SearchResults</div>
      <div className='grid grid-cols-1 lg:grid-cols-2 md:mx-15 lg:mx-15 xl:mx-49 md:grid-rows-7 bg-tag p-15 gap-4'>
        {books.map(book => 
          <BookInfo 
          key={book.id}
          book={book}
          />
        )}
      </div>
      <BookModal></BookModal>
      <SearchResultsNavigation />
    </>
  );
};
