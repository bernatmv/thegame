import * as React from 'react';
import ChatItemProps from './chatItemProps';
import { List, Message, Icon } from 'semantic-ui-react';
import SystemConstants from '../../../../common/constants/systemConstants';
import TranslationConstants from '../../../../common/constants/translationConstants';
import i18nService from '../../../../common/service/i18nServiceImpl';
import * as style from './chatItem.css';

export default class ChatItem extends React.Component<ChatItemProps, {}> {
    static defaultProps = {
        prevMessage: null,
        index: 1
    };

    private _calculateHowLongAgo(received: number): string {
        let elapsed = Date.now() - received;
        if (elapsed < 10 * 1000) { //less than 10 seconds ago
            return i18nService.Instance.translate(TranslationConstants.aMomentAgo);
        } else if (elapsed < 60 * 1000) { //less than 1 minute ago
            let seconds = Math.floor(elapsed / (1000));
            return seconds + i18nService.Instance.translate(TranslationConstants.secondsAgo);
        } else if (elapsed < 60 * 60 * 1000) { //less than 1 hour ago
            let minutes = Math.floor(elapsed / (60 * 1000));
            return minutes + i18nService.Instance.translate(TranslationConstants.minute) + ((minutes > 1) ? 's' : '') + i18nService.Instance.translate(TranslationConstants.ago);
        } else { // everything in hours
            let hours = Math.floor(elapsed / (60 * 60 * 1000));
            return hours + i18nService.Instance.translate(TranslationConstants.hour) + ((hours > 1) ? 's' : '') + i18nService.Instance.translate(TranslationConstants.ago);
        }
    }

    render(): JSX.Element {
        let message = this.props.message;
        let key = this.props.index;
        let prevMessage = this.props.prevMessage;
        if (message.sender === SystemConstants.SystemUser) { //message from the system
            return <Message key={key.toString()} className={style.container__system__chat__messages__item_system}>
                    <Icon name='child' />
                    {message.message}
                </Message>;
        } else if (message.sender === this.props.userId) { //it's me
            let user = (prevMessage && message.sender !== prevMessage.sender)
                        ? <div>
                                <span className={style.container__system__chat__messages__item_me}>{message.sender}</span>
                                <span className={style.container__system__chat__messages__item_received}> - {this._calculateHowLongAgo(message.received)}</span>
                            </div>
                        : null;
            return <List.Item key={key.toString()}>
                        {user} {message.message}
                    </List.Item>;
        } else { //it's someone else
            let user = (prevMessage && message.sender !== prevMessage.sender)
                        ? <div>
                                <span>{message.sender}</span>
                                <span className={style.container__system__chat__messages__item_received}> - {this._calculateHowLongAgo(message.received)}</span>
                            </div>
                        : null;
            return <List.Item key={key.toString()}>{user} {message.message}</List.Item>;
        }
    }
}