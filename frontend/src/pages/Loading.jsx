import { useTranslation } from 'react-i18next';

export default function Loading() {
  const { t } = useTranslation('common');

  return (
    <div className="min-h-screen text-center p-10 bg-background">
      {t('loading')}
    </div>
  );
}
