//TODO: convert to streams using Rx

export default class Connection {
    private _websocket;

    public connect() {
        var wsURI = 'ws://' + window.location.host + '/jboss-websocket-hello/websocket/helloName';
        this._websocket = new WebSocket(wsURI);
        this._websocket.onopen = function() {
            this._displayStatus('Connected');
            this._displayMessage('Connection is now open. Type a name and click Say Hello to send a message.');
        };
        this._websocket.onmessage = function(event) {
            // log the event
            this._displayMessage('The response was received! ' + event.data, 'success');
        };
        this._websocket.onerror = function(event) {
            // log the event
            this._displayMessage('Error! ' + event.data, 'error');
        };
        this._websocket.onclose = function() {
            this._displayStatus('Closed');
            this._displayMessage('The connection was closed or timed out. Please click the Open Connection button to reconnect.');
        };
    }

    public disconnect() {
        if (this._websocket !== null) {
            this._websocket.close();
            this._websocket = null;
        }
        this._displayStatus('Disconnected');
    }

    public sendMessage() {
        if (this._websocket !== null) {
            this._websocket.send('PLACEHOLDER');
        } else {
            this._displayMessage('WebSocket connection is not established. Please click the Open Connection button.');
        }
    }

    private _displayMessage(message: string) {
        console.log(message);//tslint:disable-line
    }

    private _displayStatus(message: string) {
        console.log(message);//tslint:disable-line
    }
}