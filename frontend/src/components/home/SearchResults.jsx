import { useState } from 'react';
import BookInfo from './BookInfo';
import BookModal from './BookModal';
import SearchResultsNavigation from './SearchResultsNavigation';

export const SearchResults = () => {
  const [open, setOpen] = useState(null);

  const books = [{id: 1, name: "nimi", author: "author", genre: "genre", year: 2000, availibility: "available"}, {id: 2,name: "nimi", author: "author", genre: "genre", year: 2004, availibility: "available"}, {id: 3, name: "nimi", author: "author", genre: "genre", year: 1960, availibility: "available"}, {id: 4, name: "nimi", author: "author", genre: "genre", year: 1870, availibility: "available"}]  //mockdata

  return (
    <>
      {/* Search result header */}
      <div className="flex flex-row justify-between mb-2">
        <h2>Search Results</h2>
        <h2>1 - 20 / 53</h2>
      </div>

      <div className='grid grid-cols-1 lg:grid-cols-2 md:mx-15 lg:mx-15 xl:mx-49 md:grid-rows-7 bg-tag p-15 gap-4'>
        {books.map(book => 
          <BookInfo 
          key={book.id}
          book={book}
          setOpen={setOpen}
          />
        )}
      </div>
      {open && (
      <BookModal
      open={open}
      setOpen={setOpen}
      />
      )}
      
      {/* Search result navigation */}
      <div className="flex justify-center mt-2">
        <SearchResultsNavigation />
      </div>
    </>
  );
};

export default SearchResults;
