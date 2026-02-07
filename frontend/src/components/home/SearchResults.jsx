import SearchResultsNavigation from './SearchResultsNavigation';
import BookDataPage from '../../pages/BookDataPage';


export const SearchResults = () => {

  const books = [{id: 1, title: "kirja1", author: "author", genre: "genre", year: 2000, available: false}, {id: 2, title: "kirja2", author: "author", genre: "genre", year: 2004, available: false}, {id: 3, title: "kirja3", author: "author", genre: "genre", year: 1960, available: true}, {id: 4, title: "kirja4", author: "author", genre: "genre", year: 1870, available: true}]  //mockdata

  return (
    <>
      {/* Search result header */}
      <div className="flex flex-row justify-between mb-2">
        <h2>Search Results</h2>
        <h2>1 - 20 / 53</h2>
      </div>

      <div>
          <BookDataPage 
          title=' '
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
