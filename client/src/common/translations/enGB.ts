//tslint:disable
import TranslationConstants from '../constants/translationConstants';
import SystemConstants from '../constants/systemConstants';

interface Translations {
    [key : string]: string;
}

export const enGB: {locale: string, translations: Translations} = {
    locale: SystemConstants.Locales.enGB,
    translations: {
        /* LOGIN */
        [TranslationConstants.loginInputPlaceholder]: `What's your name?`,
        [TranslationConstants.loginDivider]: `If you don't own a name yet`,
        [TranslationConstants.signUpButton]: 'Create new character',
        /* SIGNUP */
        [TranslationConstants.signUpTitle]: 'You are about to born',
        [TranslationConstants.signUpRaceLabel]: 'In this iteration of your life you will be',
        [TranslationConstants.signUpNameLabel]: 'People will know you as',
        [TranslationConstants.signUpNamePlaceholder]: ' ',
        [TranslationConstants.signUpProfessionTitle]: 'The legendary',
        [TranslationConstants.signUpGenderTitle]: 'You will be much more than your genre',
        [TranslationConstants.signUpFinishTitle]: 'BREATH!',
        /* GENDER */
        [TranslationConstants.genderMale]: 'Man',
        [TranslationConstants.genderFemale]: 'Woman',
        [TranslationConstants.genderIntergender]: 'Intergender',
        [TranslationConstants.genderNeuter]: 'Neuter',
        [TranslationConstants.genderGenderless]: 'Genderless',
        [TranslationConstants.genderOther]: 'Other',
        /* RACE */
        [TranslationConstants.raceHuman]: 'Human',
        [TranslationConstants.raceGoblin]: 'Goblin',
        /* PROFESSION */
        [TranslationConstants.professionWarrior]: 'Warrior',
        [TranslationConstants.professionWizard]: 'Wizard',
        /* CONNECTION */
        [TranslationConstants.server]: 'Server',
        [TranslationConstants.online]: 'Connected',
        [TranslationConstants.connecting]: 'Connecting...',
        [TranslationConstants.offline]: 'Disconnected',
        /* CHAT */
        [TranslationConstants.welcomeToTheGame]: 'Welcome to... ¡TheGame!',
        [TranslationConstants.saySomething]: 'Say something...',
        [TranslationConstants.aMomentAgo]: 'a moment ago',
        [TranslationConstants.secondsAgo]: ' seconds ago',
        [TranslationConstants.minute]: ' minute',
        [TranslationConstants.hour]: ' hour',
        [TranslationConstants.ago]: ' ago',
        /* DIRECTION */
        [TranslationConstants.north]: 'North',
        [TranslationConstants.east]: 'East',
        [TranslationConstants.south]: 'South',
        [TranslationConstants.west]: 'West',
        [TranslationConstants.up]: 'Up',
        [TranslationConstants.down]: 'Down',
        [TranslationConstants.unvisitedLocation]: `You haven't visited this location yet`,
        [TranslationConstants.unvisitedDirection]: '? ? ?',
        /* ROOM FOOTER */
        [TranslationConstants.noObjectsCloseBy]: 'There are no objects close by',
        [TranslationConstants.noEnemiesCloseBy]: 'You see no enemies',
        [TranslationConstants.thereIs]: 'There is ',
        [TranslationConstants.youSee]: 'You see ',
        [TranslationConstants.isHere]: ' is here',
        /* INTRO */
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
