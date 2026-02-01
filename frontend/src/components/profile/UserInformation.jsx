const UserInformation = () => {
  const userInfo = [
    { id: "nickname", label: "Nickname", value: "User123" },
    { id: "email", label: "Email", value: "user123@metropolia.fi" },
    { id: "created", label: "User Created", value: "1.1.2024" },
  ];

  return (
    <>
      <h1>User Information</h1>
      <div>
        {userInfo.map((info) => (
          <div key={info.id}>
            <p>{info.label}</p>
            <p>{info.value}</p>
          </div>
        ))}
      </div>
    </>
  );
};

export default UserInformation;
