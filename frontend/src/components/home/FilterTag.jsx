const FilterTag = ({ filterName, onRemove, closeButton = true }) => {
  const closeButtonChar = '✕';

  return (
    <button onClick={onRemove} className="button text-xs flex items-center">
      <span className="flex-1">{filterName}</span>
      {closeButton && <span className="ml-2">{closeButtonChar}</span>}
    </button>
  );
};

export default FilterTag;
