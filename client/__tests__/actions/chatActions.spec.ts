import {sendChat, receiveChat, connectingToChat, connectedToChat, disconnectedFromChat} from '../../src/app/actions/chatActions';
import ActionsConstants from '../../src/common/constants/actionsConstants';

describe('chat actions', () => {

    describe('if we create a sendChat action', () => {
        let message = {message: 'testing'};

        it('should generate action with payload', () => {
            expect(sendChat(message)).toEqual({
                type: ActionsConstants.SendChat,
                payload: message
            });
        });
    });

    describe('if we create a sendChat action without a ChatMessage', () => {
        const error = new TypeError('not a ChatMessage');

        it('should fail', () => {
            expect(sendChat(error)).toEqual({
                type: ActionsConstants.SendChat,
                payload: error,
                error: true
            });
        });
    });

    describe('if we create a receiveChat action', () => {
        let message = {message: 'testing'};

        it('should generate action with payload', () => {
            expect(receiveChat(message)).toEqual({
                type: ActionsConstants.ReceiveChat,
                payload: message
            });
        });
    });

    describe('if we create a receiveChat action without a ChatMessage', () => {
        const error = new TypeError('not a ChatMessage');

        it('should fail', () => {
            expect(receiveChat(error)).toEqual({
                type: ActionsConstants.ReceiveChat,
                payload: error,
                error: true
            });
        });
    });

    describe('if we create a connectingToChat action', () => {
        it('should generate action', () => {
            expect(connectingToChat()).toEqual({
                type: ActionsConstants.ConnectingToChat
            });
        });
    });

    describe('if we create a connectedToChat action', () => {
        let connectedModel = {userId: 'TestUser'};

        it('should generate action with payload', () => {
            expect(connectedToChat(connectedModel)).toEqual({
                type: ActionsConstants.ConnectedToChat,
                payload: connectedModel
            });
        });
    });

    describe('if we create a connectedToChat action without a userId', () => {
        const error = new TypeError('not a userId');

        it('should fail', () => {
            expect(connectedToChat(error)).toEqual({
                type: ActionsConstants.ConnectedToChat,
                payload: error,
                error: true
            });
        });
    });

    describe('if we create a disconnectedFromChat action', () => {
        it('should generate action', () => {
            expect(disconnectedFromChat()).toEqual({
                type: ActionsConstants.DisconnectedFromChat
            });
        });
    });
});