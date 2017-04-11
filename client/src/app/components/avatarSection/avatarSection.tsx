import * as React from 'react';
import Profile from '../common/profile/profile';
import AvatarSectionProps from './avatarSectionProps';
//import * as avatarImage from '../../../../assets/goblin_512x512_desaturated.png';
import * as style from './avatarSection.css';

export default class AvatarSection extends React.Component<AvatarSectionProps, {}> {
    render(): JSX.Element {
        let players = this.props.room.players;
        return (
            <div className={style.avatar}>
                <Profile name={'Morgobor'} race={'Human'} profession={'Ranger'} level={1}
                        hp={{current: 100, max: 100, hide: false}}
                        mp={{current: 70, max: 100, hide: false}}
                        sp={{current: 30, max: 100, hide: false}}/>

                {players.map((player, i) => <Profile key={i}
                                                name={player.profile.name} 
                                                race={player.profile.race} 
                                                profession={player.profile.profession} 
                                                level={player.profile.level} 
                                                hp={player.profile.hp}
                                                mp={player.profile.mp}
                                                sp={player.profile.sp} />)}
            </div>
        );
    }
}