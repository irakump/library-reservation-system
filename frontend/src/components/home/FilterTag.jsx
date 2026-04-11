import XMark from '@heroicons/react/24/solid/XMarkIcon';
import PropTypes from "prop-types";

const FilterTag = ({ filterName, onRemove, closeButton = true }) => {
  return (
    <button
      onClick={onRemove}
      className="text-xs flex items-center rounded-md p-1 bg-tag hover:bg-filter border border-tagBorder cursor-pointer capitalize"
    >
      <span className="flex-1 ml-1 mr-1 capitalize">{filterName}</span>
      {closeButton && <XMark className="ml-1 size-4" />}
    </button>
  );
};

FilterTag.propTypes = {
  filterName: PropTypes.string.isRequired,
  onRemove: PropTypes.func.isRequired,
  closeButton: PropTypes.bool,
};

export default FilterTag;
