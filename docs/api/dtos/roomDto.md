# RoomDto

```
interface RoomDto {
    id: string;
    title: string;
    shortDescription: string;
    description: string;
    exits: {
        north?: string;
        east?: string;
        south?: string;
        west?: string;
        up?: string;
        down?: string
    };
    players: Array<PlayerDto>;
    enemies: Array<EnemyDto>;
    npc: Array<NPCDto>;
    items: Array<ItemDto>;
}
```

Examples at `<Root_Folder>/assets`