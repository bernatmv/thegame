import SystemConstants from '../constants/systemConstants';
import { esES } from '../translations/esES';

export default class I18N {
    public static translate(id: string, locale: string): string {
        let translation = id;
        if (locale === SystemConstants.Locales.esES && esES.translations[id]) {
            translation = esES.translations[id];
        }
        return translation;
    }
}