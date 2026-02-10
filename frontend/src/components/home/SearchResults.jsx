import SearchResultsNavigation from './SearchResultsNavigation';
import BookDataPage from '../../pages/BookDataPage';
import { useSearchResult } from '../../contexts/SearchResultContext.jsx';

export const SearchResults = () => {
  const { searchResults } = useSearchResult();
  console.log('books: ', searchResults);

  return (
    <>
      {/* Search result header */}
      <div className="flex flex-row justify-between mb-2">
        <h2>Search Results</h2>
        <h2>1 - 20 / 53</h2>
      </div>

      <div>
        <BookDataPage title=" " books={searchResults} pageType="favourite" />
      </div>

      {/* Search result navigation */}
      <div className="flex justify-center mt-2">
        <SearchResultsNavigation />
      </div>
    </>
  );
};

export default SearchResults;
