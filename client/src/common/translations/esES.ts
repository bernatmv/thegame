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
        [TranslationConstants.online]: 'Conectado',
        [TranslationConstants.connecting]: 'Conectando...',
        [TranslationConstants.offline]: 'Desconectado',
        [TranslationConstants.aMomentAgo]: 'hace un momento',
        [TranslationConstants.secondsAgo]: ' segundos antes',
        [TranslationConstants.minute]: ' minuto',
        [TranslationConstants.hour]: ' hora',
        [TranslationConstants.ago]: ' antes',
        [TranslationConstants.server]: 'Servidor',
        [TranslationConstants.north]: 'Norte',
        [TranslationConstants.east]: 'Este',
        [TranslationConstants.south]: 'Sur',
        [TranslationConstants.west]: 'Oeste',
        [TranslationConstants.up]: 'Arriba',
        [TranslationConstants.down]: 'Abajo',
        [TranslationConstants.unvisitedLocation]: 'Aún no has visitado esta ubicación',
        [TranslationConstants.unvisitedDirection]: '? ? ?',
        [TranslationConstants.noObjectsCloseBy]: 'No hay objetos cerca',
        [TranslationConstants.noEnemiesCloseBy]: 'No ves enemigos',
        [TranslationConstants.thereIs]: 'Hay ',
        [TranslationConstants.youSee]: 'Ves ',
        [TranslationConstants.isHere]: ' está aquí',
        [TranslationConstants.welcomeToTheGame]: 'Bienvenido a... ¡TheGame!'
    }
};
