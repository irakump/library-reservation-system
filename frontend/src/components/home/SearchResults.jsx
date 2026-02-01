import BookInfo from './BookInfo';
import SearchResultsNavigation from './SearchResultsNavigation';

export const SearchResults = () => {

  const books = [{id: 1, name: "nimi", author: "author", genre: "genre", year: "year", availibility: "available"}, {id: 2,name: "nimi", author: "author", genre: "genre", year: "year", availibility: "available"}, {id: 3, name: "nimi", author: "author", genre: "genre", year: "year", availibility: "available"}]  //mockdata

  return (
    <>
      <div>SearchResults</div>
      <div className='grid grid-cols-1 sm:grid-cols-2 gap-x-2 gap-y-3 bg-tag'>
        {books.map(book => 
          <BookInfo 
          key={book.id}
          book={book}
          />
        )}
      </div>
      <SearchResultsNavigation />
    </>
  );
};
