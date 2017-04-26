# ReceiveMessage

```
{
    kind: string;
    time: number;
    code: string (TYPIFIED);
    message: string;
    stacktrace: string;
}
```

    `kind` => *required* => The message type
    `time` => *required* => The time when the message have been sent (in milliseconds from 1 Jan 1970)
    `code` => *required* => Type of error, the list is quite extense, should be extracted from server
    `message` => *required* => The error message
    `stacktrace` => *optional* => Error stacktrace