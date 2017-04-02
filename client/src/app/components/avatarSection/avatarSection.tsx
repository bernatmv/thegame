import * as React from 'react';
import Profile from '../common/profile/profile';
import AvatarSectionProps from './avatarSectionProps';
//import * as avatarImage from '../../../../assets/goblin_512x512_desaturated.png';
import * as style from './avatarSection.css';

export default class AvatarSection extends React.Component<AvatarSectionProps, {}> {
    render(): JSX.Element {
        return (
            <div className={style.avatar}>
                <Profile name={'Morgobor'} race={'Human'} profession={'Ranger'} level={1}
                        hp={{current: 100, total: 100}}
                        mp={{current: 70, total: 100}}
                        sp={{current: 30, total: 100}}/>
                <Profile name={'Watarhu'} race={'Human'} profession={'Wizard'} level={1} size='small'
                        hp={{current: 100, total: 100}}
                        mp={{current: 100, total: 100}}
                        sp={{current: 10, total: 100}}/>
                <Profile name={'Saldoran'} race={'Human'} profession={'Paladin'} level={1} size='small'
                        hp={{current: 80, total: 100}}
                        mp={{current: 90, total: 100}}
                        sp={{current: 90, total: 100}}/>
            </div>
        );
    }
}