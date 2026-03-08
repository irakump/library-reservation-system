import { render, screen, act } from '@testing-library/react';
import { describe, expect, it, vi } from 'vitest';
import bookData from '../../book-data.json';

// mock SearchResultContext so useSearchResult returns mockBooks
vi.mock('../../contexts/SearchResultContext.jsx', () => ({
  useSearchResult: () => ({ searchResults: bookData.books }),
}));

// import component after mocking
import SearchResults from './SearchResults.jsx';

describe('SearchResults', () => {
  const resultsPerPage = 8;  // !! update to match actual value in SearchResults.jsx

  it.each([
    { pageForwardClicks: 0, pageBackwardClicks: 0 },
    { pageForwardClicks: 1, pageBackwardClicks: 0 },
    { pageForwardClicks: 2, pageBackwardClicks: 0 },
    { pageForwardClicks: 4, pageBackwardClicks: 0 },
    { pageForwardClicks: 8, pageBackwardClicks: 0 },
    { pageForwardClicks: 200, pageBackwardClicks: 0 },
    { pageForwardClicks: 1, pageBackwardClicks: 1 },
    { pageForwardClicks: 10, pageBackwardClicks: 1 },
    { pageForwardClicks: 10, pageBackwardClicks: 12 },
    { pageForwardClicks: 10, pageBackwardClicks: 3 },
  ])(
    'renders SearchResults header correctly when page is increased $pageForwardClicks times and decreased $pageBackwardClicks times',
    async ({ pageForwardClicks, pageBackwardClicks }) => {
      await act(async () => {
        await render(<SearchResults />);
      });

      await act(async () => {
        // simulate user interaction
        for (let i = 0; i < pageForwardClicks; i++) {
          await screen.getByTestId('next-page').click();
        }

        for (let i = 0; i < pageBackwardClicks; i++) {
          await screen.getByTestId('previous-page').click();
        }
      });

      const bookCount = bookData.books.length;
      const pagesToShow = Math.ceil(bookCount / resultsPerPage);

      // limit current page calculation with min and max
      let currentPage = 1;
      for (let i = 0; i < pageForwardClicks; i++) {
        currentPage = Math.min(currentPage + 1, pagesToShow);
      }
      for (let i = 0; i < pageBackwardClicks; i++) {
        currentPage = Math.max(currentPage - 1, 1);
      }

      const startIndex = (currentPage - 1) * resultsPerPage + 1;
      const endIndex = Math.min(currentPage * resultsPerPage, bookCount);

      //console.log(startIndex, endIndex);
      //screen.debug();

      expect(
        screen.getByText(`${startIndex} - ${endIndex} / ${bookCount}`),
      ).toBeInTheDocument();
    },
  );
});
