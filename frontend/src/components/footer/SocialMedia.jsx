import { useTranslation } from "react-i18next";

const SocialMedia = () => {
  const { t } = useTranslation('navigation');

  return (
    <div className="flex flex-col">
      <h2 className="text-2xl">{t("footer.social_media_header")}</h2>
      <div className="flex flex-row max-[250px]:flex-col items-center bg-navbar gap-5 max-[250px]:gap-3 pt-5">
        <a href="https://www.instagram.com/" target="_blank">
          <img
            src="/social-media/instagram.png"
            alt={t("footer.instagram_logo_alt")}
            className="w-10 h-10"
          />
        </a>
        <a href="https://www.facebook.com/" target="_blank">
          <img
            src="/social-media/facebook.png"
            alt={t("footer.facebook_logo_alt")}
            className="h-15 w-auto"
          />
        </a>
        <a href="https://www.linkedin.com" target="_blank">
          <img
            src="/social-media/linkedin.png"
            alt={t("footer.linkedin_logo_alt")}
            className="h-10 w-auto"
          />
        </a>
      </div>
    </div>
  );
};

export default SocialMedia;
