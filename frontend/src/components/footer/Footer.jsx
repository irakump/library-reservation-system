import React from "react";
import SocialMedia from "./SocialMedia";
import Languages from "./Languages";
import Logo from "./Logo";

const Footer = () => {
  return (
    <div className="flex flex-col bg-navbar p-5 sm:py-10 items-center">
      <div className="flex flex-col sm:flex-row sm:gap-15 *:md:gap-15 lg:gap-50 *:lg:gap-50">
        <div className="flex flex-col md:flex-row">
          <SocialMedia />
          <Languages />
        </div>
        <Logo />
      </div>
    </div>
  );
};

export default Footer;
