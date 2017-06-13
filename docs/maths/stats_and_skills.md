# Stats and skills

## Race

One character can only have one race at the same time. A race:

    - Unlocks skills (this skills are binded to the race)
    
    - Have a level (this level is increased earning experience points [XP] which are awarded for killing enemies, completing quests and achieving notorious tasks)
    
    - Provides a base bonus to the main stats
    
    - The main stats increases every time the race levels goes up and are:

        - Strength: effects the physical damage (melee)

        - Agility: effects the physical damage (ranged) and the reaction time

        - Constitution: effects the health points [HP]

        - Intelligence: effects the magical damage/power

        - Wisdom: effects the mana points [MP]

## Profession

One character can only have one profession but can swap them fairly easily. A profession:

    - Unlocks skills (this skills are binded to the profession)

    - Have a level (this level is computed based on the level of all the skills belonging to this profession)

    - When the level goes up, skill points are earned to unlock new skills related to this profession

## Skills

On character can have multiple skills, those skills can be binded to the character, the race or the profession. The skills:

    - Have a level (this level is increased using the skill in meaningful situations)

    - Can have multiple pre-requisites (like having a profession, having completed some quest, achieving a certain stat number, etc)

    - Skills almost never fail but improve on each level 

    - Each skill can have a different difficulty, that means a different price in skill points to buy and a different number of skill points needed to level up


## Combat

### Rules

    - Turn based

    - Order of action based on reaction time (line on top specifies order)

### IA

    - If enemy in front: attack

    - If enemy on side: attack

    - If enemy on back: attack

    - If enemy in LOS, move to closer (front preferred if equal)

    - If empty in front and no enemy in LOS on side/back: move front

### Alternate

    - More like hearthstone mix: to attack second line melee you have to first clear first line

    - Second line can only attack distance