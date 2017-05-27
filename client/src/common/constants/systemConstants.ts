const SystemUser = 'SystemUser';
const ChatMessage = 'ChatMessage';
const Locales = {
    esES: 'esES',
    enGB: 'enGB'
};

interface ILocales {
    [key : string]: string;
}

export default class SystemConstants {
    public static SystemUser: string = SystemUser;
    public static ChatMessage: string = ChatMessage;
    public static Locales: ILocales = Locales;
}