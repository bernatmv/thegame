//tslint:disable
import TranslationConstants from '../constants/translationConstants';
import SystemConstants from '../constants/systemConstants';

interface Translations {
    [key : string]: string;
}

export const esES: {locale: string, translations: Translations} = {
    locale: SystemConstants.Locales.esES,
    translations: {
        // SYSTEM
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
        [TranslationConstants.welcomeToTheGame]: 'Bienvenido a... ¡TheGame!',

        /********************** ASSETS **********************/

        // ROOMS
        ['roomBeta001Title']: 'Un claro en el bosque',
        ['roomBeta001ShortDescription']: 'Un tranquilo claro en medio de un frondoso bosque',
        ['roomBeta001Description']: 'Te encuentras en un claro en medio de un frondoso bosque, un pequeño arrollo murmulla en un rincón mientras una suave brisa hace ondular varios tallos de lavanda llenando el aire de un suave aroma.\nAl norte los arboles se abren ligeramente a un sendero que parece poco transitado.',
        ['roomBeta002Title']: 'Las afueras del campamento',
        ['roomBeta002ShortDescription']: 'Entre el bosque y la pared de una colina, hay un pequeño campamento',
        ['roomBeta002Description']: 'El camino lleva hasta un espacio abierto entre el bosque y la pared de una colina.\nEn este lugar hay un pequeño asentamiento rodeado por una empalizada de algo menos de dos metros. Los troncos de la empalizada son pequeños e irregulares, y están atados con primitivas cuerdas de cáñamo.',
        ['roomBeta003Title']: 'La plaza del campamento',
        ['roomBeta003ShortDescription']: 'La plaza central del campamento empalizado',
        ['roomBeta003Description']: 'El campamento consiste en cuatro chozas grandes de paja que podrían alojar a cinco o seis personas cada una.\nTodas las chozas son de construcción basta y hechas con materiales de poca calidad. La plaza ocupa una extensión considerable donde cabrían sin problemas cien personas, pero en vez de eso sólo hay varios montones de suciedad y unas cuantas gallinas picoteando en ellos.',
        // RACES
        ['raceHuman']: 'Humano',
        ['raceHumanSingular']: 'humano',
        ['raceHumanPlural']: 'humanos',
        ['raceGoblin']: 'Goblin',
        ['raceGoblinSingular']: 'goblin',
        ['raceGoblinPlural']: 'goblins',
        // ITEMS
        ['itemChickenDescription']: 'Un pollo de campo común.\nTiene plumas, pico y ¡una mirada maliciosa en los ojos!',
        ['itemChickenSingular']: 'pollo',
        ['itemChickenPlural']: 'pollos',
    }
};
