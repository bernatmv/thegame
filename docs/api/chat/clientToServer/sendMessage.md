# SendMessage

```
{
    kind: string;
    sender: string;
    message: string;
}
```

    `kind` => *required* => The message type
    `sender` => *required* => Who is sending the message (should be removed, the server knows who the socket belongs to)
    `message` => *required* => The message to send