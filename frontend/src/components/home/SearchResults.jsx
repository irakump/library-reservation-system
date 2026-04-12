import BookDataPage from '../../pages/BookDataPage';
import { useSearchResult } from '../../contexts/SearchResultContext.jsx';
import { useState, useEffect, useRef } from 'react';

import { useTranslation } from 'react-i18next';
import PaginationNext from '../buttons/PaginationNext.jsx';
import PaginationPrevious from '../buttons/PaginationPrevious.jsx';

export const SearchResults = () => {
  const { t, i18n } = useTranslation('search');
  const searchResultsStartRef = useRef(null);

  const { searchResults } = useSearchResult();

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

  let firstDotsRendered = false;
  let lastDotsRendered = false;

  const scrollToSearchResultsHeader = () => {
    try {
      searchResultsStartRef.current.scrollIntoView();
    } catch (error) {
      // handle error, which is triggered during testing
      console.warn('Error with scrolling to search result header:', error);
    }
  };

  return (
    <div>
      {/* Search result header */}
      {searchResults.length > 0 ? (
        <>
          <div className="flex flex-row justify-between mb-2 px-4 sm:px-1">
            <h2 ref={searchResultsStartRef}>{t('search_results_header')}</h2>
            <h2>
              {new Intl.NumberFormat(i18n.language).format(startIndex + 1)} -{' '}
              {Math.min(endIndex, searchResults.length)}{' '}
              /{' '}
              {new Intl.NumberFormat(i18n.language).format(
                searchResults.length,
              )}
            </h2>
          </div>

          <div className="sm:max-w-4xl mx-auto">
            <BookDataPage
              title=" "
              books={searchResults.slice(startIndex, endIndex)}
              pageType="home"
            />
          </div>

          {/* Search result navigation */}
          <div className="flex flex-row justify-center items-center mt-4 mb-18 [&>button]:bg-actionButton [&>button]:p-1.5 [&>button]:rounded-md [&>button]:cursor-pointer [&>button]:hover:bg-actionButtonHover sm:max-w-4xl mx-auto">
            <PaginationPrevious
              setCurrentPage={setCurrentPage}
              scrollToSearchResultsHeader={scrollToSearchResultsHeader}
            />

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
                } else {
                  return (
                    <a
                      key={page}
                      href="#"
                      onClick={(e) => {
                        e.preventDefault();
                        setCurrentPage(page);
                        scrollToSearchResultsHeader();
                      }}
                      className={
                        currentPage === page ? 'font-bold ' : undefined
                      }
                    >
                      {new Intl.NumberFormat(i18n.language).format(i + 1)}
                    </a>
                  );
                }
              })}
            </div>

            <PaginationNext
              setCurrentPage={setCurrentPage}
              scrollToSearchResultsHeader={scrollToSearchResultsHeader}
              pagesToShow={pagesToShow}
            />
          </div>
        </>
      ) : (
        <div className="flex justify-center mb-18 sm:max-w-4xl mx-auto">
          <h2>{t('search_results_header_none')}</h2>
        </div>
      )}
    </div>
  );
};

export default SearchResults;
