import SystemConstants from '../constants/systemConstants';
import i18nService from './i18nService';
import I18N from '../helpers/i18n';
import { debug } from './models/appLogger';

export default class i18nServiceImpl implements i18nService {    
    private static _instance: i18nServiceImpl;
    private _locale: string;

    constructor() {
        this._locale = SystemConstants.Locales.esES;
    }

    setLocale(locale: string): void {
        this._locale = locale;
    }

    translate(id: string): string {
        return I18N.translate(id, this._locale);
    }

    public static get Instance(): i18nServiceImpl {
        if (!i18nServiceImpl._instance) {
            i18nServiceImpl._instance = new i18nServiceImpl();
        }
        return i18nServiceImpl._instance;
    }
}