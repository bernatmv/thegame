# ReceiveMessage

```
{
    kind: string;
    time: number;
    sender: string;
	type: string (SAY,YELL,WHISPER),
    message: string;
}
```

    `kind` => *required* => The message type
    `time` => *required* => The time when the message have been sent (in milliseconds from 1 Jan 1970)
    `type` => *required* => The type of message chat (SAY: sent all players into the same room as sender, YELL: sent everybody, WHISPER: sent recipient only)
    `sender` => *required* => Who is sending the message
    `message` => *required* => The message to send