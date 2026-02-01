const BookInfo = ({book}) => {
    return (
        <>
          <div className="bg-white rounded shadow-xl grid grid-cols-3 grid-rows-6 max-h-45 gap-1 ">
            <div>
                <img src="https://placehold.co/150x200/png" alt="placeholder" className="m-5 min-w-26 min-h-35 col-end-2"/>
            </div>
            <div className="capitalize m-5 col-start-2 row-start-1 mr-auto">
                <h3 className="font-bold text-xl" >{book.name}</h3>
                <p>{book.author}</p>
                <p>{book.year}</p>
                
            </div>
            <div className="col-end-7" >O</div>
            <span className="col-start-2 row-start-5 m-5">Available</span>
            <button className=" rounded-md bg-filter ml-auto col-start-7 row-start-5 m-3 px-5">Loan</button>
            
            
          </div>
        </>
    )

}
export default BookInfo