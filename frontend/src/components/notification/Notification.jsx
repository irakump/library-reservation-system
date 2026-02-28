import React, { useEffect } from "react";
import { useNotification } from "../../contexts/NotificationContext";

const Notification = () => {
  const {
    notification,
    setNotification,
    isNotificationOpen,
    setIsNotificationOpen,
  } = useNotification();

  // For development. To display notification, set open = true. TODO: remove after backend is connected
  useEffect(() => {
    setIsNotificationOpen(false);
  }, [setIsNotificationOpen]);

  useEffect(() => {
    // TODO: connect to backend, add API-call to get notification (status not-read)
    // TODO: when user closes notification, set status = read (after that the notification never shows up again)
    setNotification("Book returned. Longer notification jeejee message moi 1.2.2025");  // Mock notification message
  }, [setNotification]);

  

  return (
    <div>
      {isNotificationOpen && (
        <div className="flex flex-row justify-between items-center bg-red-300 py-1 pl-2">
          <div>
            <p className="text-md">{notification}</p>
          </div>

          <div>
            <button
              onClick={() => {
                setIsNotificationOpen(false);
              }}
              className="text-5xl font-light pb-2 mr-2 w-8"
            >
              &times;
            </button>
          </div>
        </div>
      )}
    </div>
  );
};

export default Notification;
