import { useTranslation } from 'react-i18next';
import { locales } from '../../utils/locales';

const Languages = () => {
  const { t, i18n } = useTranslation('navigation');

  return (
    <div className="flex flex-col">
      <h2 className="text-2xl py-5 md:pt-0" data-testid="footer-languages-header">{t('footer.language_header')}</h2>
      <div className="flex flex-col gap-2 items-start *:hover:cursor-pointer">
          {Object.keys(locales).map((lng) => (
              <button
                  type="submit"
                  key={lng}
                  onClick={() => i18n.changeLanguage(lng)} disabled={i18n.resolvedLanguage === lng}>
                  {locales[lng].nativeName}
              </button>
          ))}
      </div>
    </div>
  );
};

export default Languages;
