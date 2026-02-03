const SearchBar = () => {
  return (
    <div className="w-1/3 flex flex-col [&>div]:gap-1 items-start mt-8 mb-12">
      <label className="" htmlFor="search">
        Search
      </label>
      <div className="flex flex-row w-full">
        <input
          className="rounded-sm border border-black"
          type="text"
          id="search"
        />
        <button className="button">Search</button>
      </div>
    </div>
  );
};

export default SearchBar;
