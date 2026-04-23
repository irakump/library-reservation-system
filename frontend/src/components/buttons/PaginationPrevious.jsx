import { useLayoutDirection } from '../../contexts/LayoutDirectionContext.jsx';
import ChevronLeft from '@heroicons/react/24/solid/ChevronLeftIcon';
import ChevronRight from '@heroicons/react/24/solid/ChevronRightIcon';
import PropTypes from 'prop-types';

export default function PaginationPrevious({
  setCurrentPage,
  scrollToSearchResultsHeader,
}) {
  const { isRTL } = useLayoutDirection();

  return (
    <button
      aria-label="previous page"
      className=":bg-actionButton p-1.5 rounded-md cursor-pointer hover:bg-actionButtonHover"
      key="previous-page"
      data-testid="previous-page"
      onClick={(e) => {
        e.preventDefault();
        setCurrentPage((previous) => Math.max(1, previous - 1));
        scrollToSearchResultsHeader();
      }}
    >
      {isRTL ? (
        <ChevronRight className="h-full size-5" />
      ) : (
        <ChevronLeft className="h-full size-5" />
      )}
    </button>
  );
}

PaginationPrevious.propTypes = {
  setCurrentPage: PropTypes.func.isRequired,
  scrollToSearchResultsHeader: PropTypes.func.isRequired,
}
