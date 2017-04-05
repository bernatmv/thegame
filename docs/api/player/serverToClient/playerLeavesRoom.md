# PlayerLeavesRoomMessage

```
{
    "kind": string;
    "time": number;
    "player": string;
    "leavingRoom": string;
}
```

    `kind` => *required* => The message type
    `time` => *required* => The time when the message have been sent (in milliseconds from 1 Jan 1970)
    `player` => *required* => Id of the player leaving the room
    `leavingRoom` => *required* => Id of the room the player is leaving (should be the room the current player is, because he can only see people entering or leaving his/her room)