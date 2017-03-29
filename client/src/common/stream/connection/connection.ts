import * as Rx from 'rx';
import ConnectionStatus from './connectionStatus';
import WebsocketMessage from './websocketMessage';

export default class Connection {
    private _url: string;
    private _websocket: WebSocket;
    private _observable: Rx.Observable<WebsocketMessage>;

    constructor(
        url: string,
        onOpen: () => void
        ) {
        this._url = url;
        this._websocket = new WebSocket(this._url);
        this._observable = Rx.Observable.create<WebsocketMessage>(observer => {
            this._websocket.onmessage = (event) => this._onMessage(event, observer);
            this._websocket.onclose = (event) => this._onClose(event, observer);
            this._websocket.onerror = (event) => this._onError(event, observer);
        });
        this._websocket.onopen = (event) => this._connect(event, onOpen);
    }

    public get url(): string {
        return this._url;
    }

    public get status(): ConnectionStatus {        
        if (this._websocket.readyState === WebSocket.CONNECTING) {
            return ConnectionStatus.Connecting;
        } else if (this._websocket.readyState === WebSocket.OPEN) {
            return ConnectionStatus.Connected;
        } else {
            return ConnectionStatus.Disconnected;
        }
    }

    public getStream(): Rx.Observable<WebsocketMessage> {
        return this._observable;
    }

    private _connect(event, onOpen: () => void): void {
        console.log(`Connected to ${this._url} with websocket`, event);//tslint:disable-lint
        if (onOpen) {
            onOpen();
        }
    }

    private _onMessage(event, observer: Rx.Observer<WebsocketMessage>): void {
        console.log(`New message from ${this._url} with data`, event.data);//tslint:disable-lint
        observer.onNext(JSON.parse(event.data));
    }

    private _onError(event, observer: Rx.Observer<WebsocketMessage>): void {
        console.log(`ERROR! on websocket from ${this._url} with data`, event.data);//tslint:disable-lint
        observer.onError(event.data);
    }

    private _onClose(event, observer: Rx.Observer<{}>): void {
        // The connection was closed or timed out. Please click the Open Connection button to reconnect
        console.log(`Disconnected from ${this._url} with websocket`, event);//tslint:disable-lint
        observer.onCompleted();
    }

    public disconnect(): void {
        if (this._websocket !== null) {
            this._websocket.close();
            this._websocket = null;
        }
    }

    public sendMessage(message: WebsocketMessage): void {
        if (this._websocket !== null) {
            this._websocket.send(JSON.stringify(message));
        } else {
            console.log(`Websocket disconnected, can't send any message`, this._websocket);//tslint:disable-lint
        }
    }
}