export default {
    id: 'beta-room-003',
    title: 'roomBeta003Title',
    description: 'roomBeta003Description',
    exits: {
        south: 'beta-room-002'
    },
    players: [],
    enemies: [
        {
            id: 'goblin-001',
            profile: {
                level: 2,
                hp: { current: 25, max: 25 }
            }
        },
        {
            id: 'goblin-001',
            profile: {
                level: 1,
                hp: { current: 20, max: 20 }
            }
        }
    ],
    npc: [],
    items: [
        {
            id: 'chicken-001',
            name: 'Pascual'
        }
    ]
};