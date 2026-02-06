import { useState } from 'react';

import MagnifyingGlass from '@heroicons/react/16/solid/MagnifyingGlassIcon';

const SearchBar = () => {
  const allBooks = ['book1', 'the book2', 'book3', 'harry potter', 'the hobbit'];
  const [books, setBooks] = useState(allBooks);

  const [searchTerm, setSearchTerm] = useState('');

  const handleSearch = () => {
    if (searchTerm.trim() === '') {
      setBooks(allBooks);
      return;
    }

    const filteredBooks = allBooks.filter((book) =>
      book.toLowerCase().includes(searchTerm.toLowerCase()),
    );
    setBooks(filteredBooks);
  };

  return (
    <div className="flex flex-col w-full h-15 gap-1 items-stretch mt-8 mb-12">
      <label className="ml-1" htmlFor="search">
        Search
      </label>

      <form
        onSubmit={(e) => {
          e.preventDefault();
          handleSearch();
        }}
        className="flex flex-row flex-1 w-full h-full rounded-sm border border-black divide-x-2 divide-black"
      >
        <input
          type="text"
          id="search"
          onChange={(e) => setSearchTerm(e.target.value)}
          className="flex-1 h-full bg-white px-1"
        />

        <button
          type="submit"
          onClick={handleSearch}
          className="flex flex-row h-full px-4 bg-filter gap-1 cursor-pointer"
        >
          <MagnifyingGlass className="h-full size-4" />
          Search
        </button>
      </form>

      {/* mock data, delete */}
      <div>
        {books.map((book) => (
          <div key={book}>{book}</div>
        ))}
      </div>
    </div>
  );
};

export default SearchBar;
