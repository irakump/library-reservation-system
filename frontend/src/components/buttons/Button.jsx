import PropTypes from "prop-types";
function Button({ onClick, children }) {
  return (
    <button
      className="bg-actionButton font-semibold rounded-xl px-6 py-2 hover:bg-actionButtonHover float-right cursor-pointer"
      onClick={(e) => {
        e.stopPropagation();
        onClick();
      }}
    >
      {children}
    </button>
  );
}

Button.PropTypes = {
  onClick: PropTypes.func.isRequired,
  children: PropTypes.node.isRequired,
};

export default Button;
