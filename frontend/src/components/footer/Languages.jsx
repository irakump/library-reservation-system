import { useTranslation } from 'react-i18next';

const Languages = () => {
  const { t } = useTranslation('navigation');

  return (
    <div className="flex flex-col">
      <h2 className="text-2xl py-5 md:pt-0">{t('footer.language_header')}</h2>
      <div className="flex flex-col gap-2 items-start">
        <button>{t("footer.language.finnish")}</button>
        <button>{t("footer.language.english")}</button>
      </div>
    </div>
  );
};

export default Languages;
