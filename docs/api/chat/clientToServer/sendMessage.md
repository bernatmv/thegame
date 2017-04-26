# SendMessage

```
{
    kind: string;
    recipient: string;
    type: string (SAY,YELL,WHISPER);
    message: string;
}
```

    `kind` => *required* => The message type
    `recipient` => *optional* => Message recipient (mandatory if WHISPER type)
    `type` => *optional* => The type of message chat (SAY: send to all players into the same room as sender, YELL: send to everybody, WHISPER: send to recipient only)
    `message` => *required* => The message to send