function FavBtn({onClick}) {
    const [isFavourite, setIsFavourite] = useState(false);
  return (
    <button className="text-xl"
    onClick={e => {
      e.stopPropagation();
      onClick();
      ;
    }}>
      {isFavourite ? "♥︎" : "♡"}
    </button>
  );
} export default FavBtn;
