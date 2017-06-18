# SignUpResponseMessage

```
{
    kind: string;
    valid: boolean;
    error?: string;
}
```

    `kind` => *required* => The message type
    `valid` => *required* => True if the signup is valid, false otherwise
    `error` => optional => If the signup is invalid, specify the error