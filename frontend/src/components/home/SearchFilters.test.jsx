import { render, screen, act, fireEvent } from '@testing-library/react';
import { describe, expect, it, vi } from 'vitest';

// mock SearchResultContext
vi.mock('../../contexts/SearchResultContext.jsx', () => ({
  useSearchResult: () => ({
    fetchSearchResults: vi.fn(),
  }),
}));

// mock SearchFilterContext
vi.mock('../../contexts/SearchFilterContext.jsx', () => ({
  useSearchFilters: () => ({
    searchFilters: {
      genre: [],
      language: [],
      years: [],
      available: true,
    },
    setSearchFilters: vi.fn(),
  }),
}));

// import component after mocking
import SearchFilters from './SearchFilters.jsx';

describe('SearchFilters', () => {

  // search filters render correctly
  it.each([
    { languages: ['english'], genres: ['history'], years: [] },
    { languages: ['english'], genres: ['history'], years: [2023] },
    { languages: ['finnish'], genres: ['nonfiction'], years: [2024, 2025] },
    { languages: [], genres: ['children'], years: [2026] },
  ])(
    'renders correct search filters: $languages, $genres, $years',

    async ({ languages, genres, years }) => {
      await act(async () => {
        await render(<SearchFilters />);
      });

      // simulate user interaction (select item from dropdown menu)
      [
        [languages, "language-select"],
        [genres, "genre-select"],
        [years, "year-select"],
      ].forEach(([items, testId]) => {
        for (const item of items) {
          const select = screen.getByTestId(testId);
          fireEvent.change(select, {
            target: { value: String(item) },
          });
        }
      });

      //screen.debug();

      // verify filters are displayed in active filters section (FilterTag buttons)
      const filterButtons = screen.getAllByRole('button');
      [languages, genres, years].forEach((items) => {
        for (const item of items) {
          const itemButton = filterButtons.find(
            (button) =>
              button.querySelector('span')?.textContent === String(item),
          );
          expect(itemButton).toBeInTheDocument();
        }
      });
    },
  );
});
