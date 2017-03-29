import * as React from 'react';
import * as ReactDOM from 'react-dom';
import * as Rx from 'rx';
import * as style from './style.css';
import uuid from 'uuid';
import Connection from './common/stream/connection/connection';
import WebsocketMessage from './common/stream/connection/websocketMessage';

let me = uuid.v4();
let chatConnection: Connection = new Connection('ws://localhost:8080/chat/world', () => {
    chatConnection.sendMessage({sender: me, message: 'works!'});
});
let chatStream: Rx.Observable<WebsocketMessage> = chatConnection.getStream();
chatStream.subscribe(
    (message: WebsocketMessage) => {
        //new message
    },
    (error) => {
        //error
    },
    () => {
        //completed
    }
);

ReactDOM.render(<div className={style.header}>
                    <h1 classID={'status'}>{chatConnection ? chatConnection.status.label : null}</h1>
                </div>, document.getElementById('root'));

//TODO: flexbox for the chat layout
//TODO: UI components
//TODO: Redux