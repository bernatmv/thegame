import SystemConstants from '../constants/systemConstants';
import { esES } from '../translations/esES';
import { enGB } from '../translations/enGB';

export default class I18N {
    public static translate(id: string, locale: string): string {
        let translation = id;
        if (locale === SystemConstants.Locales.esES && esES.translations[id]) {
            translation = esES.translations[id];
        }
        if (locale === SystemConstants.Locales.enGB && enGB.translations[id]) {
            translation = enGB.translations[id];
        }
        return translation;
    }
}