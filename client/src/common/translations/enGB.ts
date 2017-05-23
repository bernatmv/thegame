//tslint:disable
import TranslationConstants from '../constants/translationConstants';
import SystemConstants from '../constants/systemConstants';

interface Translations {
    [key : string]: string;
}

export const enGB: {locale: string, translations: Translations} = {
    locale: SystemConstants.Locales.enGB,
    translations: {
        // SYSTEM
        [TranslationConstants.loginInputPlaceholder]: `What's your name?`,
        [TranslationConstants.saySomething]: 'Say something...',
        [TranslationConstants.online]: 'Connected',
        [TranslationConstants.connecting]: 'Connecting...',
        [TranslationConstants.offline]: 'Disconnected',
        [TranslationConstants.aMomentAgo]: 'a moment ago',
        [TranslationConstants.secondsAgo]: ' seconds ago',
        [TranslationConstants.minute]: ' minute',
        [TranslationConstants.hour]: ' hour',
        [TranslationConstants.ago]: ' ago',
        [TranslationConstants.server]: 'Server',
        [TranslationConstants.north]: 'North',
        [TranslationConstants.east]: 'East',
        [TranslationConstants.south]: 'South',
        [TranslationConstants.west]: 'West',
        [TranslationConstants.up]: 'Up',
        [TranslationConstants.down]: 'Down',
        [TranslationConstants.unvisitedLocation]: `You haven't visited this location yet`,
        [TranslationConstants.unvisitedDirection]: '? ? ?',
        [TranslationConstants.noObjectsCloseBy]: 'There are no objects close by',
        [TranslationConstants.noEnemiesCloseBy]: 'You see no enemies',
        [TranslationConstants.thereIs]: 'There is ',
        [TranslationConstants.youSee]: 'You see ',
        [TranslationConstants.isHere]: ' is here',
        [TranslationConstants.welcomeToTheGame]: 'Welcome to... ¡TheGame!',
        [TranslationConstants.introLine1]: `In an ancient time, when the high men built citadels in the plains of Svart Hal.`,
        [TranslationConstants.introLine2]: `The world was already old, and men just some younglings in desperate look of a destiny.`,
        [TranslationConstants.introLine3]: `They were despised, few in number, and without allies or friends...`,

        /********************** ASSETS **********************/

        // ROOMS
        ['roomBeta001Title']: '',
        ['roomBeta001Description']: '',
        ['roomBeta002Title']: '',
        ['roomBeta002Description']: '',
        ['roomBeta003Title']: '',
        ['roomBeta003Description']: '',

        ['sistaHemLastInn001Title']: '',
        ['sistaHemLastInn001Description']: '',
        ['sistaHemLastInn002Title']: '',
        ['sistaHemLastInn002Description']: '',
        ['sistaHemLastInn003Title']: '',
        ['sistaHemLastInn003Description']: '',
        // RACES
        ['raceHuman']: '',
        ['raceHumanSingular']: '',
        ['raceHumanPlural']: '',
        ['raceGoblin']: '',
        ['raceGoblinSingular']: '',
        ['raceGoblinPlural']: '',
        // ITEMS
        ['itemChickenDescription']: '',
        ['itemChickenSingular']: '',
        ['itemChickenPlural']: '',
    }
};
