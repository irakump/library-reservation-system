import React, { createContext, useContext, useState } from "react";

const NotificationContext = createContext();

export const NotificationProvider = ({ children }) => {
  const [notification, setNotification] = useState();
  const [isNotificationOpen, setIsNotificationOpen] = useState(false);

  return (
    <NotificationContext.Provider value={{ notification, setNotification, isNotificationOpen, setIsNotificationOpen }}>
      {children}
    </NotificationContext.Provider>
  );
};

export const useNotification = () => useContext(NotificationContext);
