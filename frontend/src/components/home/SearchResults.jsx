import BookDataPage from '../../pages/BookDataPage';
import { useSearchResult } from '../../contexts/SearchResultContext.jsx';
import { useState, useEffect, useRef } from 'react';

export const SearchResults = () => {
  const searchResultsStartRef = useRef(null);

  const { searchResults } = useSearchResult();
  //console.log('books: ', searchResults.length, searchResults);

  const resultsPerPage = 4;
  const pagesToShow = Math.ceil(searchResults.length / resultsPerPage);
  const [currentPage, setCurrentPage] = useState(1);
  const [startIndex, setStartIndex] = useState(
    currentPage * resultsPerPage - resultsPerPage,
  );
  const [endIndex, setEndIndex] = useState(currentPage * resultsPerPage);

  useEffect(() => {
    if (searchResults) {
      setStartIndex(currentPage * resultsPerPage - resultsPerPage);
      setEndIndex(currentPage * resultsPerPage);
    }
  }, [searchResults, currentPage]);

  useEffect(() => {
    setCurrentPage(1);
  }, [searchResults]);

  useEffect(() => {
    searchResultsStartRef.current.focus();
  }, [currentPage]);

  let firstDotsRendered = false;
  let lastDotsRendered = false;

  return (
    <>
      {/* Search result header */}
      <div
        ref={searchResultsStartRef}
        className="flex flex-row justify-between mb-2"
      >
        <h2>Search Results</h2>
        <h2>
          {startIndex + 1} -{' '}
          {endIndex < searchResults.length ? endIndex : searchResults.length} /{' '}
          {searchResults.length}
        </h2>
      </div>

      <div>
        <BookDataPage
          title=" "
          books={searchResults.slice(startIndex, endIndex)}
          pageType="favourite"
        />
      </div>

      {/* Search result navigation */}
      <div className="flex flex-row justify-center items-center mt-4 mb-18 [&>button]:bg-filter [&>button]:p-1.5 [&>button]:rounded-md [&>button]:cursor-pointer">
        <button
          onClick={(e) => {
            e.preventDefault();
            setCurrentPage((previous) => Math.max(1, previous - 1));
          }}
        >
          {'<'}
        </button>

        {/* Links to search result pages */}
        <div className="flex flex-row mx-4 [&>a]:mx-1 gap-1">
          {[...Array(pagesToShow)].map((_, i) => {
            const page = i + 1;
            if (
              page !== 1 &&
              page !== pagesToShow &&
              page !== currentPage &&
              page !== currentPage - 1 &&
              page !== currentPage + 1
            ) {
              if (page < currentPage && !firstDotsRendered) {
                firstDotsRendered = true;
                return <p key={'first-nav-dots'}>...</p>;
              } else if (page > currentPage && !lastDotsRendered) {
                lastDotsRendered = true;
                return <p key={'second-nav-dots'}>...</p>;
              }
              return;
            } else {
              return (
                <a
                  key={page}
                  href="#"
                  onClick={(e) => {
                    e.preventDefault();
                    setCurrentPage(page);
                  }}
                  className={currentPage === page ? 'font-bold ' : undefined}
                >
                  {i + 1}
                </a>
              );
            }
          })}
        </div>

        <button
          onClick={(e) => {
            e.preventDefault();
            setCurrentPage((previous) => Math.min(previous + 1, pagesToShow));
          }}
        >
          {'>'}
        </button>
      </div>
    </>
  );
};

export default SearchResults;
