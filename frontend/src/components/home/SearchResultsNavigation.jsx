const SearchResultsNavigation = () => {
  return (
    <>
      <div className="flex flex-row items-center">
        <button className="button rounded-md">{'<'}</button>

        {/* Links to search result pages */}
        <div className="mx-4 [&>a]:mx-1">
          <a href="#">1</a>
          <a href="#">2</a>
          <a href="#">3</a>
        </div>

        <button className="button rounded-md">{'>'}</button>
      </div>
    </>
  );
};

export default SearchResultsNavigation;
