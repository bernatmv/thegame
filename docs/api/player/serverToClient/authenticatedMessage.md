# AuthenticatedMessage

```
{
    kind: string;
    time: number;
    userId: string,
    profile: ProfileModel
}
```

    `kind` => *required* => The message type
    `time` => *required* => The time when the message have been sent (in milliseconds from 1 Jan 1970)
    `userId` => *required* => Id of the authenticated user
    `profile` => *required* => The authenticated user profile