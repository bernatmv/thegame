import * as React from 'react';
import * as ReactDOM from 'react-dom';
import * as style from './style.css';
import Connection from './common/stream/connection/connection';

ReactDOM.render(<div className={style.header}>
                    <h1 classID={'message'}>Hi!</h1>
                    <br />
                    <h2 classID={'status'}>status</h2>
                </div>, document.getElementById('root'));

let connection = new Connection();
connection.connect();
connection.sendMessage();