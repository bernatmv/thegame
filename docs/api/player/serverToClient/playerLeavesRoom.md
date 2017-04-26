# PlayerLeavesRoomMessage

```
{
    "kind": string;
    "time": number;
    "player": string;
    "exit": string;
}
```

    `kind` => *required* => The message type
    `time` => *required* => The time when the message have been sent (in milliseconds from 1 Jan 1970)
    `player` => *required* => Id of the player leaving the room
    `exit` => *required* => the exit player has taken