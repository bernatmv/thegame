import I18nServiceImpl from '../../src/common/service/i18nServiceImpl';
import SystemConstants from '../../src/common/constants/systemConstants';
import TranslationConstants from '../../src/common/constants/translationConstants';

describe('service >> I18nServiceImpl', () => {
    let translationConstant = TranslationConstants.welcomeToTheGame;
    let wrongConstant = 'this-translation-constant-does-not-exist';
    let translation = 'Bienvenido a... ¡TheGame!';
    let translationEN = 'Welcome to... ¡TheGame!';
    
    describe('if we create a new service instance', () => {
        const i18nServiceImpl = new I18nServiceImpl();

        it('should return a translation for a valid id', () => {
            expect(i18nServiceImpl.translate(translationConstant)).toBe(translation);
        });

        it('should return the id for a bad translation', () => {
            expect(i18nServiceImpl.translate(wrongConstant)).toBe(wrongConstant);
        });
    });

    describe('if we change the locale to a valid one', () => {
        const i18nServiceImpl = new I18nServiceImpl();
        i18nServiceImpl.setLocale(SystemConstants.Locales.enGB);

        it('should return a translation for a valid id', () => {
            expect(i18nServiceImpl.translate(translationConstant)).toBe(translationEN);
        });

        it('should return the id for a bad translation', () => {
            expect(i18nServiceImpl.translate(wrongConstant)).toBe(wrongConstant);
        });
    });

    describe('if we change the locale to an invalid one', () => {
        const i18nServiceImpl = new I18nServiceImpl();
        i18nServiceImpl.setLocale(SystemConstants.Locales.caCA);

        it('should return id for a valid id', () => {
            expect(i18nServiceImpl.translate(translationConstant)).not.toBe(translation);
            expect(i18nServiceImpl.translate(translationConstant)).toBe(translationConstant);
        });

        it('should return the id for a bad translation', () => {
            expect(i18nServiceImpl.translate(wrongConstant)).toBe(wrongConstant);
        });
    });
});