import React from "react";

const Languages = () => {
  return (
  <div className="flex flex-col">
      <h2 className="text-2xl py-5 md:pt-0">Language</h2>
      <div className="flex flex-col gap-2 items-start">
        <button>Finnish</button>
        <button>English</button>
      </div>
    </div>
  );
};

export default Languages;
