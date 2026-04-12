import React, { createContext, useContext, useState } from "react";
import PropTypes from "prop-types";

const NotificationContext = createContext();

export const NotificationProvider = ({ children }) => {
  const [notification, setNotification] = useState();
  const [isNotificationOpen, setIsNotificationOpen] = useState(false);

  return (
    <NotificationContext.Provider
      value={{
        notification,
        setNotification,
        isNotificationOpen,
        setIsNotificationOpen,
      }}
    >
      {children}
    </NotificationContext.Provider>
  );
};
NotificationProvider.propTypes = {
  children: PropTypes.node.isRequired,
};

export const useNotification = () => useContext(NotificationContext);
