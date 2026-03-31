import { defineConfig } from 'i18next-cli'

/** @type {import('i18next-cli').I18nextToolkitConfig} */
export default defineConfig({
  locales: [
    "en-US",
    "ar-u-nu-arab",
    "ja-JP"
  ],
  extract: {
    input: "src/**/*.{js,jsx,ts,tsx}",
    output: "public/locales/{{language}}/{{namespace}}.json"
  }
})