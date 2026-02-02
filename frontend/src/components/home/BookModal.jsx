const BookModal = ({setOpen}) => {
    return(
        <>
            <div className="fixed inset-0 flex items-center justify-center bg-black/40" onClick={() => setOpen(null)}>
            <div className="relative w-full max-w-md bg-white rounded-xl p-9 border-20 border-filter">
                <button onClick={() => setOpen(null)} className="absolute top-0 right-4 text-xl">✕</button>
                <button className="absolute top-10 right-4 text-4xl">♡</button>

                <div className="flex gap-4">
                <img src="https://placehold.co/100x150" alt="Book cover" className="w-24 h-36 object-cover"
                />
                <div className="flex-1">
                    <h1 className="bold">Book Title</h1>
                    <p >Author</p>
                    <p >2004</p>
                    <p>
                    Description
                    </p>
                </div>
                </div>
                <div className="mt-6 text-sm text-gray-700 space-y-1">
                <p><span className="font-medium">Language:</span> Finnish</p>
                <p><span className="font-medium">Genre:</span> History</p>
                </div>
                <div className="mt-3 flex items-center gap-2">
                <span className="px-3 py-1 text-xs rounded-full bg-gray-100">Finnish</span>
                <span className="px-3 py-1 text-xs rounded-full bg-gray-100">History</span>
                
                </div>
                <div className="mt-6 flex items-end justify-between">
                <div className="text-sm">
                    <p className="flex items-center gap-2">
                    🔴 Not available
                    </p>
                    <p className="mt-1">2 people in queue</p>
                    <p className="text-gray-500">Estimated loan date x.x.2026</p>
                </div>
                <button className="px-5 py-2 rounded-xl bg-filter font-medium">
                    Loan</button>
                </div>
            </div>
            </div>

        </>
    )
}
export default BookModal