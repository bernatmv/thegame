# PlayerEntersRoomMessage

```
{
    "kind": string;
    "time": number;
    "player": string;
    "enteringRoom": string;
}
```

    `kind` => *required* => The message type
    `time` => *required* => The time when the message have been sent (in milliseconds from 1 Jan 1970)
    `player` => *required* => Id of the player entering the room
    `enteringRoom` => *required* => Id of the room the player is entering (should be the room the current player is, because he can only see people entering or leaving his/her room)