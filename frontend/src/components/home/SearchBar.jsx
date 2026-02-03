import MagnifyingGlass from '@heroicons/react/16/solid/MagnifyingGlassIcon';

const SearchBar = () => {
  return (
    <div className="flex flex-col w-full h-15 gap-1 items-stretch mt-8 mb-12">
      <label className="ml-1" htmlFor="search">
        Search
      </label>

      <div className="flex flex-row flex-1 w-full h-full rounded-sm border border-black divide-x-2 divide-black">
        <input
          className="flex-1 h-full bg-white px-1"
          type="text"
          id="search"
        />
        <div className="flex flex-row h-full px-4 bg-filter gap-1">
          <MagnifyingGlass className="h-full size-4" />
          <button>Search</button>
        </div>
      </div>
    </div>
  );
};

export default SearchBar;
