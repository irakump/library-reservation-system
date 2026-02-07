import { useState } from 'react';
import BookCard from '../Books/BookCard';
import BookModal from '../Books/BookModal';
import SearchResultsNavigation from './SearchResultsNavigation';
import BookDataPage from '../../pages/BookDataPage';

export const SearchResults = () => {
  const [open, setOpen] = useState(null);

  const books = [{id: 1, title: "nimi", author: "author", genre: "genre", year: 2000, availibility: "available"}, {id: 2, title: "nimi", author: "author", genre: "genre", year: 2004, availibility: "available"}, {id: 3, title: "nimi", author: "author", genre: "genre", year: 1960, availibility: "available"}, {id: 4, title: "nimi", author: "author", genre: "genre", year: 1870, availibility: "available"}]  //mockdata

  return (
    <>
      {/* Search result header */}
      <div className="flex flex-row justify-between mb-2">
        <h2>Search Results</h2>
        <h2>1 - 20 / 53</h2>
      </div>

      <div>
          <BookDataPage title=' '
          books={books}
          pageType="favourite"
          />
      </div>
      
      {/* Search result navigation */}
      <div className="flex justify-center mt-2">
        <SearchResultsNavigation />
      </div>
    </>
  );
};

export default SearchResults;
