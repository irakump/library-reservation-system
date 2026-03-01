
//this components is rendered inside BookButton component which gets rendered inside BookModal and BookCard

function ReserveButton({pageType, book, children}) {
    return (
        <button className="bg-filter font-semibold rounded-xl px-6 py-2 hover:bg-sky-500 float-right cursor-pointer"
                onClick={e => {
                    e.stopPropagation();
                    {/*here reserve function call in context or something*/}
                }}>
            {children}
        </button>
    );
} export default ReserveButton;