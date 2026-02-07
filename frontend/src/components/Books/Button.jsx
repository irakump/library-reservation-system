function Button({ onClick, children }) {
  return (
    <button className="bg-filter font-semibold rounded-xl px-6 py-2 hover:bg-sky-500 float-right cursor-pointer"
    onClick={e => {
      e.stopPropagation();
      onClick();
      ;
    }}>
      {children}
    </button>
  );
}

export default Button;