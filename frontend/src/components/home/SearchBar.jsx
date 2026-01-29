const SearchBar = () => {
  return (
    <div className="w-1/3">
      <label htmlFor="search">Search:</label>
      <input className="co" type="text" id="search" />
      <button>Search</button>
    </div>
  );
};

export default SearchBar;
