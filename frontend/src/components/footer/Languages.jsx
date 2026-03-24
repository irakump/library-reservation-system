import { useTranslation } from 'react-i18next';

const Languages = () => {
  const { t, i18n } = useTranslation('navigation');

  const lngs = {
        en: {nativeName: 'English'},
        ja: {nativeName: '日本語'},
        ar: {nativeName: 'العربية'},
    };

  return (
    <div className="flex flex-col">
      <h2 className="text-2xl py-5 md:pt-0" data-testid="footer-languages-header">{t('footer.language_header')}</h2>
      <div className="flex flex-col gap-2 items-start">
          {Object.keys(lngs).map((lng) => (
              <button
                  type="submit"
                  key={lng}
                  onClick={() => i18n.changeLanguage(lng)} disabled={i18n.resolvedLanguage === lng}>
                  {lngs[lng].nativeName}
              </button>
          ))}
      </div>
    </div>
  );
};

export default Languages;
