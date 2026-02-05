import React from "react";

const SocialMedia = () => {
  return (
    <div className="flex flex-col">
      <h2 className="text-2xl">Social Media</h2>
      <div className="flex flex-row items-center bg-navbar gap-5 pt-5">
        <a href="https://www.instagram.com/" target="_blank">
          <img
            src="/social-media/instagram.png"
            alt="Instagram"
            className="w-10 h-10"
          />
        </a>
        <a href="https://www.facebook.com/" target="_blank">
          <img
            src="/social-media/facebook.png"
            alt="Facebook"
            className="h-15 w-auto"
          />
        </a>
        <a href="https://www.linkedin.com" target="_blank">
          <img
            src="/social-media/linkedin.png"
            alt="LinkedIn"
            className="h-10 w-auto"
          />
        </a>
      </div>
    </div>
  );
};

export default SocialMedia;
