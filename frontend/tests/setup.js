import { expect, beforeAll, afterAll, afterEach, vi } from 'vitest';
import { cleanup } from '@testing-library/react';
import * as matchers from '@testing-library/jest-dom/matchers';
import { setupServer } from 'msw/node';
import { http, HttpResponse } from 'msw';

import bookData from '../src/book-data.json';

export const restHandlers = [
  http.get('http://localhost:8081/api/book/filter/en-US', () => {
    return HttpResponse.json(bookData);
  }),
  http.get('http://localhost:8081/api/genre/all/en-US', () => {
    return HttpResponse.json([
      { genre: 'biography' },
      { genre: 'children' },
      { genre: 'crime fiction' },
      { genre: 'fantasy' },
      { genre: 'history' },
      { genre: 'nonfiction' },
      { genre: 'science fiction' },
    ]);
  }),
  http.get('http://localhost:8081/api/language/all/en-US', () => {
    return HttpResponse.json([
      { language: 'english' },
      { language: 'finnish' },
    ]);
  }),
  http.get('http://localhost:8081/api/book/years', () => {
    return HttpResponse.json([2020, 2021, 2022, 2023, 2024, 2025, 2026]);
  }),
];

vi.mock('react-i18next', () => ({
  // this mock makes sure any components using the translate hook can use it without a warning being shown
  useTranslation: () => {
    return {
      t: (i18nKey) => i18nKey,
      i18n: {
        changeLanguage: () => new Promise(() => {}),
      },
    };
  },
  initReactI18next: {
    type: '3rdParty',
    init: () => {},
  },
}));

const server = setupServer(...restHandlers);
// Start server before all tests
beforeAll(() => server.listen({ onUnhandledRequest: 'error' }));
// Close server after all tests
afterAll(() => server.close());
// Reset handlers after each test for test isolation
afterEach(() => server.resetHandlers());

expect.extend(matchers);

afterEach(() => {
  cleanup();
});
