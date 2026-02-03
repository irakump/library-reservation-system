const SearchResultsNavigation = () => {
  return (
    <>
      <div className="flex flex-row items-center mt-4 mb-18 [&>button]:bg-filter [&>button]:p-1.5 [&>button]:rounded-md">
        <button>{'<'}</button>

        {/* Links to search result pages */}
        <div className="mx-4 [&>a]:mx-1">
          <a href="#">1</a>
          <a href="#">2</a>
          <a href="#">3</a>
        </div>

        <button>{'>'}</button>
      </div>
    </>
  );
};

export default SearchResultsNavigation;
