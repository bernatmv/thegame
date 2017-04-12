import TranslationConstants from '../constants/translationConstants';
import SystemConstants from '../constants/systemConstants';

interface Translations {
    [key : string]: string;
}

export const esES: {locale: string, translations: Translations} = {
    locale: SystemConstants.Locales.esES,
    translations: {
        [TranslationConstants.loginInputPlaceholder]: '¿Cómo te llamas?',
        [TranslationConstants.saySomething]: 'Di algo...',
        [TranslationConstants.connecting]: 'Conectando...',
        [TranslationConstants.offline]: 'Desconectado',

    }
};
