interface i18nService {
    setLocale(locale: string): void;
    translate(id: string): string;
}
export default i18nService;