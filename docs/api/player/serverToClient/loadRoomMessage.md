# LoadRoomMessage

```
{
    kind: string;
    time: number;
	id: string;
	exits: {
		north?: string;
		east?: string;
		south?: string;
		west?: string;
		up?: string;
		down?: string;
	},
	players: Array<PlayerDto>;
	enemies: Array<EnemyDto>;
	npc: Array<NPCDto>;
	items: Array<ItemDto>;
}
```

    `kind` => *required* => The message type
    `time` => *required* => The time when the message have been sent (in milliseconds from 1 Jan 1970)
	`id` => *required* => The id of the room
	`exits` => *required* => All the available exits from this room
		`exits.north` => _optional_ => The *id* of the room connecting to this one from the north
		`exits.east` => _optional_ => The *id* of the room connecting to this one from the east
		`exits.south` => _optional_ => The *id* of the room connecting to this one from the south
		`exits.west` => _optional_ => The *id* of the room connecting to this one from the west
		`exits.up` => _optional_ => The *id* of the room connecting to this one from the up
		`exits.down` => _optional_ => The *id* of the room connecting to this one from the down
	`players` => _optional_ => An array with the other online players in this room (this can change if players enter/leave the room, but that notification will use another message)
	`enemies` => _optional_ => An array with the enemies in the room
	`npc` => _optional_ => An array with the npc in the room
	`items` => _optional_ => An array with the items in the room