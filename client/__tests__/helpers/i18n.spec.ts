import I18N from '../../src/common/helpers/i18n';
import SystemConstants from '../../src/common/constants/systemConstants';
import TranslationConstants from '../../src/common/constants/translationConstants';

describe('helper >> i18n', () => {
    let translationConstant = TranslationConstants.welcomeToTheGame;
    let wrongConstant = 'this-translation-constant-does-not-exist';
    let translation = 'Bienvenido a... ¡TheGame!';

    it('should return a translation for the appropiate id and locale', () => {
        expect(I18N.translate(translationConstant, SystemConstants.Locales.esES)).toBe(translation);
    });

    it('should return id for a wrong locale', () => {
        expect(I18N.translate(translationConstant, SystemConstants.Locales.enGB)).toBe(translationConstant);
    });

    it('should return id for a wrong translation constant', () => {
        expect(I18N.translate(wrongConstant, SystemConstants.Locales.esES)).toBe(wrongConstant);
    });
});