import SearchResultsNavigation from './SearchResultsNavigation';

const SearchResults = () => {
  return (
    <>
      {/* Search result header */}
      <div className="flex flex-row justify-between mb-2">
        <h2>Search Results</h2>
        <h2>1 - 20 / 53</h2>
      </div>

      <div className="bg-blue-200">
        {/* TODO: replace with actual search results component */}
        <p>
          Lorem ipsum dolor sit, amet consectetur adipisicing elit. Placeat, at.
          Libero, quis accusantium unde molestias nobis corrupti minus error,
          dignissimos, recusandae esse et neque earum eum quam! Sunt, amet unde?
        </p>
      </div>

      {/* Search result navigation */}
      <div className="flex justify-center mt-2">
        <SearchResultsNavigation />
      </div>
    </>
  );
};

export default SearchResults;
