//tslint:disable
import TranslationConstants from '../constants/translationConstants';
import SystemConstants from '../constants/systemConstants';

interface Translations {
    [key : string]: string;
}

export const esES: {locale: string, translations: Translations} = {
    locale: SystemConstants.Locales.esES,
    translations: {
        /* LOGIN */
        [TranslationConstants.loginInputPlaceholder]: '¿Cómo te llamas?',
        [TranslationConstants.loginDivider]: 'Si no tienes nombre',
        [TranslationConstants.signUpButton]: 'Crear nuevo personaje',
        /* SIGNUP */
        [TranslationConstants.signUpTitle]: 'Estás a punto de renacer',
        [TranslationConstants.signUpRaceLabel]: 'En esta iteración de tu vida serás un',
        [TranslationConstants.signUpNameLabel]: 'Te conocerán cómo',
        [TranslationConstants.signUpNamePlaceholder]: '',
        [TranslationConstants.signUpProfessionTitle]: 'El legendario',
        [TranslationConstants.signUpGenderTitle]: 'Serás mucho más que tu género',
        [TranslationConstants.signUpFinishTitle]: '¡RESPIRA!',
        /* GENDER */
        [TranslationConstants.genderMale]: 'Hombre',
        [TranslationConstants.genderFemale]: 'Mujer',
        [TranslationConstants.genderIntergender]: 'Hermafrodita',
        [TranslationConstants.genderNeuter]: 'Neutro',
        [TranslationConstants.genderGenderless]: 'Sin género',
        [TranslationConstants.genderOther]: 'Otro',
        /* RACE */
        [TranslationConstants.raceHuman]: 'Humano',
        [TranslationConstants.raceGoblin]: 'Goblin',
        /* PROFESSION */
        [TranslationConstants.professionWarrior]: 'Guerrero',
        [TranslationConstants.professionWizard]: 'Mago',
        /* CONNECTION */
        [TranslationConstants.server]: 'Servidor',
        [TranslationConstants.online]: 'Conectado',
        [TranslationConstants.connecting]: 'Conectando...',
        [TranslationConstants.offline]: 'Desconectado',
        /* CHAT */
        [TranslationConstants.saySomething]: 'Di algo...',
        [TranslationConstants.welcomeToTheGame]: 'Bienvenido a... ¡TheGame!',
        [TranslationConstants.aMomentAgo]: 'hace un momento',
        [TranslationConstants.secondsAgo]: ' segundos antes',
        [TranslationConstants.minute]: ' minuto',
        [TranslationConstants.hour]: ' hora',
        [TranslationConstants.ago]: ' antes',
        /* DIRECTION */
        [TranslationConstants.north]: 'Norte',
        [TranslationConstants.east]: 'Este',
        [TranslationConstants.south]: 'Sur',
        [TranslationConstants.west]: 'Oeste',
        [TranslationConstants.up]: 'Arriba',
        [TranslationConstants.down]: 'Abajo',
        [TranslationConstants.unvisitedLocation]: 'Aún no has visitado esta ubicación',
        [TranslationConstants.unvisitedDirection]: '? ? ?',
        /* ROOM FOOTER */
        [TranslationConstants.noObjectsCloseBy]: 'No hay objetos cerca',
        [TranslationConstants.noEnemiesCloseBy]: 'No ves enemigos',
        [TranslationConstants.thereIs]: 'Hay ',
        [TranslationConstants.youSee]: 'Ves ',
        [TranslationConstants.isHere]: ' está aquí',
        /* INTRO */
        [TranslationConstants.introLine1]: 'En un tiempo remoto, cuando los altos hombres edificaban ciudadelas en los páramos de Svart Hal,',
        [TranslationConstants.introLine2]: 'El mundo ya era viejo y los hombres apenas unos niños vagando en busca de un destino',
        [TranslationConstants.introLine3]: 'Eran una raza despreciada, pocos en número y sin aliados o amigos...',

        /********************** ASSETS **********************/

        // ROOMS
        ['roomBeta001Title']: 'Un claro en el bosque',
        ['roomBeta001Description']: 'Te encuentras en un claro en medio de un frondoso bosque, un pequeño arrollo murmulla en un rincón mientras una suave brisa hace ondular varios tallos de lavanda llenando el aire de un suave aroma.\nAl norte los arboles se abren ligeramente a un sendero que parece poco transitado.',
        ['roomBeta002Title']: 'Las afueras del campamento',
        ['roomBeta002Description']: 'El camino lleva hasta un espacio abierto entre el bosque y la pared de una colina.\nEn este lugar hay un pequeño asentamiento rodeado por una empalizada de algo menos de dos metros. Los troncos de la empalizada son pequeños e irregulares, y están atados con primitivas cuerdas de cáñamo.',
        ['roomBeta003Title']: 'La plaza del campamento',
        ['roomBeta003Description']: 'El campamento consiste en cuatro chozas grandes de paja que podrían alojar a cinco o seis personas cada una.\nTodas las chozas son de construcción basta y hechas con materiales de poca calidad. La plaza ocupa una extensión considerable donde cabrían sin problemas cien personas, pero en vez de eso sólo hay varios montones de suciedad y unas cuantas gallinas picoteando en ellos.',

        ['sistaHemLastInn001Title']: 'Los establos',
        ['sistaHemLastInn001Description']: 'Los establos de la Última Posada son suficientemente grandes para albergar a nueve pesebres para caballos u otros animales de tiro, un granero y una pequeña forja.',
        ['sistaHemLastInn002Title']: 'El patio',
        ['sistaHemLastInn002Description']: 'La Última Posada está construida alrededor de un gran patio donde hay un pozo que abastece de agua a la posada.\nEn el lado norte se yerguen los establos, al este el almacen y al sur la propia posada. La posada es grande para los estándares locales, un edificio de dos plantas con las paredes de piedra y el tejado de pizarra.',
        ['sistaHemLastInn003Title']: 'El almacén',
        ['sistaHemLastInn003Description']: 'Gran parte del almacén ha sido acondicionado para que sirva como taller y aquí es donde el posadero y sus hijos realizan la mayor parte de las reparaciones.\nHay un taller de carpintería, un banco de zapatero, una mesa para trabajar el cuero y un almacén de herramientas.',
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