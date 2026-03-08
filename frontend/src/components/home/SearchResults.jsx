import BookDataPage from '../../pages/BookDataPage';
import { useSearchResult } from '../../contexts/SearchResultContext.jsx';
import { useState, useEffect, useRef } from 'react';

import ChevronLeft from '@heroicons/react/24/solid/ChevronLeftIcon';
import ChevronRight from '@heroicons/react/24/solid/ChevronRightIcon';

export const SearchResults = () => {
  const searchResultsStartRef = useRef(null);

  const { searchResults } = useSearchResult();
  //console.log('books: ', searchResults.length, searchResults);

  const resultsPerPage = 8; // !!!
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
    //searchResultsStartRef.current.focus();
  }, [currentPage]);

  let firstDotsRendered = false;
  let lastDotsRendered = false;

  return (
    <div>
      {/* Search result header */}
      {searchResults.length > 0 ? (
        <>
          <div className="flex flex-row justify-between mb-2 px-4 sm:px-1">
            <h2 ref={searchResultsStartRef}>Search Results</h2>
            <h2>
              {startIndex + 1} -{' '}
              {endIndex < searchResults.length
                ? endIndex
                : searchResults.length}{' '}
              / {searchResults.length}
            </h2>
          </div>

          <div className="sm:max-w-4xl mx-auto">
            <BookDataPage
              title=" "
              books={searchResults.slice(startIndex, endIndex)}
              pageType="favourite"
            />
          </div>

          {/* Search result navigation */}
          <div className="flex flex-row justify-center items-center mt-4 mb-18 [&>button]:bg-filter [&>button]:p-1.5 [&>button]:rounded-md [&>button]:cursor-pointer [&>button]:hover:bg-sky-500 sm:max-w-4xl mx-auto">
            <button
              key="previous-page"
              data-testid="previous-page"
              onClick={(e) => {
                e.preventDefault();
                setCurrentPage((previous) => Math.max(1, previous - 1));
              }}
            >
              <ChevronLeft className="h-full size-5" />
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
                      className={
                        currentPage === page ? 'font-bold ' : undefined
                      }
                    >
                      {i + 1}
                    </a>
                  );
                }
              })}
            </div>

            <button
              key="next-page"
              data-testid="next-page"
              onClick={(e) => {
                e.preventDefault();
                setCurrentPage((previous) =>
                  Math.min(previous + 1, pagesToShow),
                );
              }}
            >
              <ChevronRight className="h-full size-5" />
            </button>
          </div>
        </>
      ) : (
        <div className="flex justify-center mb-18 sm:max-w-4xl mx-auto">
          <h2>No Search Results</h2>
        </div>
      )}
    </div>
  );
};

export default SearchResults;
