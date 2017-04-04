# ReceiveMessage

```
{
    kind: string;
    time: number;
    sender: string;
    message: string;
}
```

    `kind` => *required* => The message type
    `time` => *required* => The time when the message have been sent (in milliseconds from 1 Jan 1970)
    `sender` => *required* => Who is sending the message
    `message` => *required* => The message to send