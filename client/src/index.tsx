import * as React from 'react';
import * as ReactDOM from 'react-dom';
import * as style from './style.css';

ReactDOM.render(<div className={style.header}>
                    <h1 classID={'message'}>Hi!</h1>
                    <br />
                    <h2 classID={'status'}>status</h2>
                </div>, document.getElementById('root'));

var websocket = null;

function displayMessage(data, style?) {
    var message = document.getElementById('message');
    message.setAttribute("class", style);
    message.value = data;
}

function displayStatus(status) {
    var currentStatus = document.getElementById('status');
    currentStatus.value = status;
}

function connect() {
    var wsURI = 'ws://' + window.location.host + '/jboss-websocket-hello/websocket/helloName';
    websocket = new WebSocket(wsURI);
    websocket.onopen = function() {
        displayStatus('Connected');
        document.getElementById('sayHello').disabled = false;
        displayMessage('Connection is now open. Type a name and click Say Hello to send a message.');
    };
    websocket.onmessage = function(event) {
        // log the event
        displayMessage('The response was received! ' + event.data, 'success');
    };
    websocket.onerror = function(event) {
        // log the event
        displayMessage('Error! ' + event.data, 'error');
    };
    websocket.onclose = function() {
        displayStatus('Closed');
        displayMessage('The connection was closed or timed out. Please click the Open Connection button to reconnect.');
        document.getElementById('sayHello').disabled = true;
    };
}

function disconnect() {
    if (websocket !== null) {
        websocket.close();
        websocket = null;
    }
    displayStatus('Disconnected');
}

function sendMessage() {
    if (websocket !== null) {
        var content = document.getElementById('name').value;
        websocket.send(content);
    } else {
        displayMessage('WebSocket connection is not established. Please click the Open Connection button.', 'error');
    }
}