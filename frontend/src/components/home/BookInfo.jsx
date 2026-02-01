const BookInfo = ({book}) => {
    return (
        <>
          <div className="bg-white rounded shadow-xl grid grid-cols-6 grid-rows-6 max-h-45 gap-1 ">
            <div>
                <img src="https://placehold.co/150x200/png" alt="placeholder" className="m-5 col-start-1 object-cover "/>
            </div>
            <div className="capitalize mt-1 items-start ml-7">
                <h3 className="font-bold text-xl" >{book.name}</h3>
                <p>{book.author}</p>
                <p>{book.year}</p>
                
            </div>
            <button className=" rounded-md ml-auto col-start-7 row-start-1 m-3 px-5 text-3xl">♡</button>
            <span className="col-start-2 row-start-5 m-5 bg-filter">Available</span>
            <button className="rounded-md bg-filter text-black px-5 py-2 text-sm justify-self-end self-start col-start-7 row-start-5 mr-3 mt-3    leading-none inline-flex items-center"
            >Loan</button>
            
            
          </div>
        </>
    )

}
export default BookInfo