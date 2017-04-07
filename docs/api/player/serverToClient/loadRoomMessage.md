# LoadRoomMessage

```
{
    kind: string;
    time: number;
    room: {
        id: string,
        title: string,
        shortDescription: string,
        description: string,
        exits: {
            north?: string,
            east?: string,
            south?: string,
            west?: string,
            up?: string,
            down?: string
        },
        players: Array<PlayerDto>,
        enemies: Array<EnemyDto>,
        npc: Array<NPCDto>,
        items: Array<ItemDto>
    }
}
```

    `kind` => *required* => The message type
    `time` => *required* => The time when the message have been sent (in milliseconds from 1 Jan 1970)
    `room` => *required* => A full definition of the room
        `room.id` => *required* => The id of the room
        `room.title` => *required* => The title of the room
        `room.shortDescription` => *required* => The short description of the room
        `room.description` => *required* => The description of the room
        `room.exits` => *required* => All the available exits from this room
            `room.exits.north` => _optional_ => The *id* of the room connecting to this one from the north
            `room.exits.east` => _optional_ => The *id* of the room connecting to this one from the east
            `room.exits.south` => _optional_ => The *id* of the room connecting to this one from the south
            `room.exits.west` => _optional_ => The *id* of the room connecting to this one from the west
            `room.exits.up` => _optional_ => The *id* of the room connecting to this one from the up
            `room.exits.down` => _optional_ => The *id* of the room connecting to this one from the down
        `room.players` => _optional_ => An array with the other online players in this room (this can change if players enter/leave the room, but that notification will use another message)
        `room.enemies` => _optional_ => An array with the enemies in the room
        `room.npc` => _optional_ => An array with the npc in the room
        `room.items` => _optional_ => An array with the items in the room