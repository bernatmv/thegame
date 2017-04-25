# PlayerEntersRoomMessage

```
{
    "kind": string;
    "time": number;
    "player": string;
    "from": string;
}
```

    `kind` => *required* => The message type
    `time` => *required* => The time when the message have been sent (in milliseconds from 1 Jan 1970)
    `player` => *required* => Id of the player entering the room
    `from` => *required* => Id from the room the player is entering 