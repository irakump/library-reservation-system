import React from "react";
import { useMenu } from "../../contexts/MenuContext";
import { useTranslation } from "react-i18next";

const LanguageMenu = () => {
  const { t } = useTranslation(["navigation"]);

  return (
    <div className="absolute max-sm:w-full sm:right-32 text-center z-50">
      <ul className="flex flex-col w-full sm:w-50 bg-navbar border border-t-0 *:border-t *:p-2 text-xl font-bold *:hover:cursor-pointer">

        <button>
            <li>{t("footer.language.english")}</li>
        </button>

        <button>
            <li>{t("footer.language.finnish")}</li>
        </button>

        <button>
            <li>{t("footer.language.arabic")}</li>
        </button>

        <button>
            <li>{t("footer.language.japanese")}</li>
        </button>
        
      </ul>
    </div>
  );
};

export default LanguageMenu;
