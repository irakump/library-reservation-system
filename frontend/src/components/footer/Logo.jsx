import React from "react";
import { useTranslation } from "react-i18next";

const Logo = () => {
  const { t } = useTranslation("navigation");

  return (
    <div className="mt-5 sm:mt-0 text-center w-55 max-[235px]:w-40 max-[180px]:w-30">
      <a href="/" className=" ">
        <img src="/logo.png" alt={t("footer.logo_img_alt")} data-testid="footer-logo-img" />
      </a>
    </div>
  );
};

export default Logo;
