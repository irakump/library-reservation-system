const ProfileStats = () => {
  const stats = [
    { id: "loans", label: "Current loans:", value: "4" },
    {
      id: "reservations",
      label: "Reservations:",
      value: "11",
    },
    { id: "favourites", label: "Favourites:", value: "22" },
  ];

  return (
    <>
      <div className="bg-white rounded-lg shadow-sm">
        <div className=" divide-y-2 divide-blue-200">
          {stats.map((info) => (
            <div key={info.id} className="flex gap-4 py-3 items-center">
              <span className="w-36 text-sm font-semibold text-left ml-4">
                {info.label}
              </span>
              <span className="text-sm font-medium">{info.value}</span>
            </div>
          ))}
        </div>
      </div>
    </>
  );
};

export default ProfileStats;
