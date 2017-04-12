export const keyPrefix = 'thegame.';

export default class TranslationConstants {
    static get loginInputPlaceholder() {
        return `${keyPrefix}loginInputPlaceholder`;
    }

    static get saySomething() {
        return `${keyPrefix}saySomething`;
    }

    static get connecting() {
        return `${keyPrefix}connecting`;
    }

    static get offline() {
        return `${keyPrefix}offline`;
    }
}